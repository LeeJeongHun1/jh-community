package kr.co.communityJh.jpa;

import static kr.co.communityJh.entity.QAccount.account;
import static kr.co.communityJh.entity.QBoard.board;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.communityJh.entity.Board;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class QueryDslTest {

	@Autowired private JPAQueryFactory jpaQueryFactory;
	
	@Test
	void queryDsl_조회테스트() {
		// given
		// 검색 board.id
		int id = 4;
//		QBoard qBoard = QBoard.board;
		// when
		
		Board boardEntity = jpaQueryFactory.selectFrom(board)
//		BoardDTO boardDTO = jpaQueryFactory
//				.select(Projections.fields(
//						BoardDTO.class,
//						board.id,
//						board.title,
//						board.body))
//				.from(board)
				.join(board.account, account).fetchJoin()
				.where(
						board.id.eq(id)
						)
				.fetchOne();
		// then
//		System.out.println(boardDTO.getAccount().getEmail());
//		System.out.println(boardDTO.getAccount().getNickname());
		System.out.println(boardEntity.getAccount().getEmail());
		System.out.println(boardEntity.getAccount().getNickname());
//		assertNotNull(boardentity);
//		assertEquals(boardentity.getId(), id);
	}
	
	@Test
	void queryDsl_게시글업데이트() {
		// given
		// 검색 board.id
		int id = 4;
//		QBoard qBoard = QBoard.board;
		// when
		Long cnt = jpaQueryFactory.update(board)
				.where(board.id.eq(id))
				.set(board.viewCount, 2)
				.execute();
		// then
		assertNotNull(cnt);
		assertEquals(cnt, 1);
	}
	

}
