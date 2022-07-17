package kr.co.communityJh.repository;

import static kr.co.communityJh.entity.QAccount.account;
import static kr.co.communityJh.entity.QBoard.board;
import static kr.co.communityJh.entity.QRole.role1;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.communityJh.entity.Board;
import lombok.RequiredArgsConstructor;

/**
 * @author jhlee
 * Board querydsl Repository
 */
@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {
	
	private final JPAQueryFactory jpaQueryFactory;
	
	public Optional<Board> findById(Long id) {
		return Optional.ofNullable(
					jpaQueryFactory
						.selectFrom(board)
						.innerJoin(board.account, account).fetchJoin()
						.innerJoin(account.roles, role1).fetchJoin()
						.where(eqId(id))
						.fetchOne()); // null이 반환될 수 있다.
	}
	
	public Long updateViewCount(Board boardEntity) {
		return jpaQueryFactory.update(board)
				.set(board.viewCount, boardEntity.getViewCount() + 1)
				.where(board.id.eq(boardEntity.getId()))
				.execute();
	}
	
	public Long deleteById(Long id) {
		return jpaQueryFactory.delete(board)
			.where(board.id.eq(id))
			.execute();
	}
	
	/**
	 * 동적 쿼리를 사용하기 위한 조건절 메소드
	 * !!!!! null을 반환 할 경우 조건절이 제거됨.
	 * @param id
	 * @return BooleanExpression
	 */
	private BooleanExpression eqId(Long id) {
//		BooleanBuilder a = new BooleanBuilder();
		if(id == 0) {
			return null;
		}
		return board.id.eq(id);
	}
	
}
