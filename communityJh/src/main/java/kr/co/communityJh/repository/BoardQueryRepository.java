package kr.co.communityJh.repository;

import static kr.co.communityJh.entity.QAccount.account;
import static kr.co.communityJh.entity.QBoard.board;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.communityJh.entity.Board;
import kr.co.communityJh.entity.QRole;
import lombok.RequiredArgsConstructor;

/**
 * @author jhlee
 * Board querydsl Repository
 */
@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {
	
	private final JPAQueryFactory jpaQueryFactory;
	
	public Optional<Board> findById(int id) {
		return  Optional.ofNullable(
					jpaQueryFactory
						.selectFrom(board)
						.innerJoin(board.account, account).fetchJoin()
						.where(eqId(id))
						.fetchOne()
				);
	}
	
	public Long updateViewCount(Board boardEntity) {
		return jpaQueryFactory.update(board)
				.set(board.viewCount, boardEntity.getViewCount() + 1)
				.where(board.id.eq(boardEntity.getId()))
				.execute();
	}
	
	public Long deleteById(int id) {
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
	private BooleanExpression eqId(int id) {
//		BooleanBuilder a = new BooleanBuilder();
		if(id == 0) {
			return null;
		}
		return board.id.eq(id);
	}
	
}
