package kr.co.communityJh.comment.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.communityJh.board.dto.BoardInfoDto;
import kr.co.communityJh.board.dto.BoardPageWithSearchDto;
import kr.co.communityJh.entity.Board;
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

import static kr.co.communityJh.entity.QAccount.account;
import static kr.co.communityJh.entity.QBoard.board;
import static kr.co.communityJh.entity.QComment.comment;
import static kr.co.communityJh.entity.QRole.role1;

/**
 * @author jhlee
 * Comment querydsl Repository
 */
@RequiredArgsConstructor
@Slf4j
@Repository
public class CommentQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;


    public Long countByBoardId(Long id){
        return jpaQueryFactory.select(comment.count())
                .from(comment)
                .where(board.id.eq(id))
                .fetchOne();
    }

}
