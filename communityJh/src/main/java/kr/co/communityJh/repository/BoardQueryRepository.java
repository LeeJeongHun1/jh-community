package kr.co.communityJh.repository;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.communityJh.entity.Board;
import kr.co.communityJh.entity.QBoard;
import lombok.RequiredArgsConstructor;

/**
 * @author jhlee
 * Board JpaRepository
 */
@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {
	
	private final JPAQueryFactory jpaQueryFactory;
	
	public Board findById(int id) {
		QBoard qBoard = QBoard.board;
		return jpaQueryFactory.selectFrom(qBoard)
				.where(qBoard.id.eq(id))
				.fetchOne();
	}
	
}
