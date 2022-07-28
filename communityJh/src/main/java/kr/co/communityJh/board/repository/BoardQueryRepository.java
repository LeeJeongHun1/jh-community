package kr.co.communityJh.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.communityJh.board.domain.Board;
import kr.co.communityJh.board.dto.BoardInfoDto;
import kr.co.communityJh.board.dto.BoardPageWithSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static kr.co.communityJh.account.domain.QAccount.account;
import static kr.co.communityJh.board.domain.QBoard.board;
import static kr.co.communityJh.comment.domain.QComment.comment;

/**
 * @author jhlee
 * Board querydsl Repository
 */
@RequiredArgsConstructor
@Slf4j
@Repository
public class BoardQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 게시글 id를 통한 단건 조회
     * @param id
     * @return
     */
    public Optional<Board> findEntityById(Long id) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .select(board)
                        .from(board)
                        .innerJoin(board.account, account)
                        .where(eqId(id))
                        .fetchOne()
            ); // null이 반환될 수 있다.
    }
    public Optional<BoardInfoDto> findDtoById(Long id) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .select(Projections.fields(BoardInfoDto.class,
                                board.id,
                                board.body,
                                board.title,
                                board.createDate,
                                board.account.nickname.as("accountNickname"),
                                board.viewCount
                        ))
                        .from(board)
                        .innerJoin(board.account, account)
                        .where(eqId(id))
                        .fetchOne()
        ); // null이 반환될 수 있다.
    }

    public Optional<Board> findById(Long id) {
        return Optional.ofNullable(jpaQueryFactory
                .selectDistinct(board)
                .from(board)
                .innerJoin(board.account, account).fetchJoin()
//                .innerJoin(board.account.roles, role1)
                .leftJoin(board.comments).fetchJoin()
                .where(board.id.eq(id))
                .fetchOne());
    }


    /**
     * 전체 게시글을 대상으로 검색 조건에 해당하는 게시글을 페이징 처리하여 반환
     * @param boardPageWithSearchDto 검색, 페이징 dto
     * @return 페이징 후 BoardPageRequestDto list
     */
    public Page<BoardInfoDto> boardPageListWithSearch(BoardPageWithSearchDto boardPageWithSearchDto) {
        String searchText = boardPageWithSearchDto.getSearchText(); // 검색 문자열
        String[] option = boardPageWithSearchDto.getOption(); // 검색 조건 ex. 본문검색 or 제목검색
        Pageable pageable = PageRequest.of(boardPageWithSearchDto.getPage(), boardPageWithSearchDto.getSize(), boardPageWithSearchDto.getSort());
        BooleanBuilder builder = new BooleanBuilder();

        // custom select
        JPAQuery<BoardInfoDto> query = jpaQueryFactory
                .select(Projections.fields(BoardInfoDto.class,
                        board.id,
                        board.body,
                        board.title,
                        board.createDate,
                        board.account.nickname.as("accountNickname"),
                        board.viewCount
                ))
                .from(board)
                .innerJoin(board.account, account);

        // 검색 조건 생성
        if (!StringUtils.isEmpty(searchText) && (option != null && option.length > 0)) {
            for (String type : option) {
                switch (type) {
                    case "t" : builder.or(containsTitle(searchText)); break;
                    case "b" : builder.or(containsBody(searchText)); break;
                }
            }
            query.where(builder);
        }

        query.offset(pageable.getOffset()) // 현재 페이지의 첫번째 게시글 위치
                .limit(pageable.getPageSize()); // 한 페이지에 보여줄 글 수
        // 정렬
        if(!pageable.getSort().isEmpty()) {
            for (Sort.Order s : pageable.getSort()){
                PathBuilder pathBuilder = new PathBuilder(Board.class, "board");
                query.orderBy(
                        new OrderSpecifier(s.getDirection().isDescending() ? Order.DESC : Order.ASC,
                                pathBuilder.get(s.getProperty()))
                );
            }
        }
        List<BoardInfoDto> boardList = query.fetch();

        boardList.forEach(it -> {
            Long count = jpaQueryFactory.select(comment.count())
                    .from(comment)
                    .where(comment.board.id.eq(it.getId()))
                    .fetchOne();
            it.setCommentCount(count != null ? count : 0L);
        });


        // count query
        JPAQuery<Long> count = jpaQueryFactory
                .select(board.count())
                .from(board)
                .innerJoin(board.account, account)
                .where(builder);

        return PageableExecutionUtils.getPage(boardList, pageable, () -> count.fetchOne());
    }


    /**
     * @param boardEntity
     * @return
     */
    public Long updateViewCount(Board boardEntity) {
        return jpaQueryFactory.update(board)
                .set(board.viewCount, boardEntity.getViewCount() + 1)
                .where(eqId(boardEntity.getId()))
                .execute();
    }

    public Long deleteById(Long id) {
        return jpaQueryFactory.delete(board)
                .where(eqId(id))
                .execute();
    }

    /**
     * 동적 쿼리를 사용하기 위한 조건절 메소드
     * !!!!! null을 반환 할 경우 조건절이 제거됨.
     *
     * @param id
     * @return BooleanExpression
     */
    private BooleanExpression eqId(Long id) {
        if (id == 0) {
            return null;
        }
        return board.id.eq(id);
    }

    private BooleanExpression containsTitle(String title) {
        if (title.equals("")) {
            return null;
        }
        return board.title.contains(title);
    }

    private BooleanExpression containsBody(String body) {
        if (body.equals("")) {
            return null;
        }
        return board.body.contains(body);
    }

}
