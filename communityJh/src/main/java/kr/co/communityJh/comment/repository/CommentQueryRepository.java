package kr.co.communityJh.comment.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.communityJh.comment.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static kr.co.communityJh.account.domain.QAccount.account;
import static kr.co.communityJh.board.domain.QBoard.board;
import static kr.co.communityJh.comment.domain.QComment.comment;

/**
 * @author jhlee
 * Comment querydsl Repository
 */
@RequiredArgsConstructor
@Slf4j
@Repository
public class CommentQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Optional<List<CommentResponseDto>> findById(Long id) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.fields(CommentResponseDto.class,
                        comment.id,
                        comment.body,
                        comment.account.email.as("accountEmail"),
                        comment.account.nickname.as("accountNickname"),
                        comment.createDate)
                )
                .from(comment)
                .innerJoin(comment.account, account)
                .where(comment.board.id.eq(id))
                .orderBy(comment.createDate.desc())
                .fetch());
    }

}
