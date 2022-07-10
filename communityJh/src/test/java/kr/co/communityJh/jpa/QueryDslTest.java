package kr.co.communityJh.jpa;

import static kr.co.communityJh.entity.QAccount.account;
import static kr.co.communityJh.entity.QBoard.board;
import static kr.co.communityJh.entity.QRole.role1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.communityJh.dto.BoardDTO;
import kr.co.communityJh.entity.Account;
import kr.co.communityJh.entity.Board;
import kr.co.communityJh.entity.QComment;
import kr.co.communityJh.entity.QLikes;
import kr.co.communityJh.entity.Role;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class QueryDslTest {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	@Test
	void queryDsl_조회테스트() {
		// given
		// 검색 board.id
		int id = 2;
		// when
		List<Board> boardEntity = jpaQueryFactory.selectFrom(board)
				.join(board.account, account)
				.fetch();
				
				
				
				
//		List<BoardDTO> boardDTO = jpaQueryFactory
//				.select(Projections.fields(
//						BoardDTO.class,
//						board.id,
//						board.title,
//						board.body,
//						board.account))
//				List<Board> boardEntity = jpaQueryFactory.selectFrom(board)
//				.from(board)
				// 성공
//				.innerJoin(account).fetchJoin()
//				.on(board.account.eq(account))
//				.innerJoin(role1).fetchJoin()
//				.on(role1.account.eq(account))
				
//				.where(account.id.eq(id)).fetch();
		// then
		boardEntity.forEach(System.out::println);
		assertNotNull(boardEntity);
	}

	@Test
	void queryDsl_게시글업데이트() {
		// given
		// 검색 board.id
		int id = 4;
//		QBoard qBoard = QBoard.board;
		// when
		Long cnt = jpaQueryFactory.update(board).where(board.id.eq(id)).set(board.viewCount, 2).execute();
		// then
		assertNotNull(cnt);
		assertEquals(cnt, 1);
	}

	@Test
	void queryDsl_유저조회() {
		// given
		// 검색 account.id
		int id = 4;
//		QBoard qBoard = QBoard.board;
		// when
		List<Role> accountList = jpaQueryFactory
				.selectFrom(role1)
				.join(role1.account, account).fetchJoin()
				.where(account.id.eq(id))
				.fetch();
		// then
		accountList.forEach(System.out::println);
		assertNotNull(accountList);
		
	}

}
