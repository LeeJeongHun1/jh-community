package kr.co.communityJh.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.communityJh.board.domain.Like;
import kr.co.communityJh.board.dto.LikeRequestDto;
import kr.co.communityJh.board.dto.LikeResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.communityJh.board.domain.QLike.like;

/**
 * @author jhlee
 * Like JpaQueryRepository
 */
@RequiredArgsConstructor
@Slf4j
@Repository
public class LikeQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Long findByBoardIdAndAccountId(LikeRequestDto likeRequestDto) {
        return jpaQueryFactory.select(like.id)
                .from(like)
                .where(like.board.id.eq(likeRequestDto.getBoardId())
                        .and(like.account.id.eq(likeRequestDto.getAccountId())))
                .fetchOne();
    }

    public List<LikeResponseDto> findAllByBoardId(LikeRequestDto likeRequestDto) {
        return jpaQueryFactory.select(Projections.fields(LikeResponseDto.class,
                        like.id,
                        like.board.id.as("boardId"),
                        like.account.nickname.as("accountNickname")))
                .from(like)
                .innerJoin(like.account)
                .where(like.board.id.eq(likeRequestDto.getBoardId()))
                .fetch();
    }
}
