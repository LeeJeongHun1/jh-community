package kr.co.communityJh.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.communityJh.account.domain.Account;
import kr.co.communityJh.board.domain.Board;
import kr.co.communityJh.board.dto.BoardDto;
import kr.co.communityJh.board.dto.BoardInfoDto;
import kr.co.communityJh.board.dto.BoardPageWithSearchDto;
import kr.co.communityJh.board.dto.PageInfo;
import kr.co.communityJh.board.repository.BoardQueryRepository;
import kr.co.communityJh.comment.dto.CommentResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.co.communityJh.account.domain.QAccount.account;
import static kr.co.communityJh.board.domain.QBoard.board;
import static kr.co.communityJh.comment.domain.QComment.comment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Slf4j
class QueryDslTest {


	@Autowired private BoardQueryRepository boardQueryRepository;
	@Autowired private JPAQueryFactory jpaQueryFactory;

	@Test
	void queryDsl_조회테스트() {
		// given
		// 검색 board.id
		Long id = 3L;
		// when
				
		BoardDto boardDTO = jpaQueryFactory
				.select(Projections.fields(
						BoardDto.class,
						board.id,
						board.title,
						board.body,
						board.account))
				.from(board)
				.innerJoin(board.account, account)
				.where(board.id.eq(id))
				.fetchOne();
				
		// then
		boardDTO.setViewCount(boardDTO.getViewCount()+1);
		assertNotNull(boardDTO);

	}

	/**
	 * board join comment join account
	 */
	@Test
	void queryDsl_조인테스트() {
		Long id = 13L;
		// 불필요 컬럼까지 조회됨...
		Board board1 = jpaQueryFactory
				.selectDistinct(board)
				.from(board)
				.innerJoin(board.account, account).fetchJoin()
//				.leftJoin(role1).on(board.account.eq(role1.account))
				.leftJoin(board.comments).fetchJoin()
				.where(board.id.eq(id))
				.fetchOne();

//		board1.getComments().forEach(it -> {
//			System.out.println(it);
//			System.out.println(it.getAccount().getNickname());
//
//		});


	}


	@Test
	void queryDsl_페이징테스트() {
		// given
		BoardPageWithSearchDto boardPageWithSearchDto = new BoardPageWithSearchDto();
//		boardPageWithSearchDto.setPage(0);

		// when
		Page<BoardInfoDto> list = boardQueryRepository.boardPageListWithSearch(boardPageWithSearchDto);
//		List<BoardPageRequestDto> boardDTO = jpaQueryFactory
//				.select(Projections.fields(
//						BoardPageRequestDto.class,
//						board.id,
//						board.title,
//						board.body,
//						board.account.email.as("accountEmail")))
//				.from(board)
//				.innerJoin(board.account, account)
////				.where(board.id.eq(id))
//				.offset(pageRequest.getOffset())
//				.limit(pageRequest.getPageSize())
//				.orderBy(board.createDate.desc())
//				.fetch();

		// then
		log.info("object: {}", PageInfo.builder().boardPageRequestDto(list).build());
		log.info("object: {}", list.getTotalElements());
		log.info("object: {}", list.getTotalPages());
		log.info("object: {}", list.getPageable().getPageNumber());
		log.info("object: {}", list.getNumber());

		list.forEach(it -> System.out.println(it));
		assertNotNull(list);
//		assertEquals(1, boardDTO.size());
	}


	@Test
	void queryDsl_게시글업데이트() {
		// given
		// 검색 board.id
		Long id = 4L;
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
		Long id = 2L;
//		QBoard qBoard = QBoard.board;
		// when
		List<Account> accountList = jpaQueryFactory
				.selectFrom(account)
//				.join(account.roles, role1).fetchJoin()
				.where(account.id.eq(id))
				.fetch();
		// then
		accountList.forEach(System.out::println);
		assertNotNull(accountList);
	}
	
	@Test
	void queryDsl_existsByEmail() {
		// given
		String email = "a20828237@gmaissssl.com";
		// when
		Integer exist = jpaQueryFactory.selectOne()
			.from(account)
			.where(account.email.eq(email))
			.fetchFirst();
		// then
		log.info("exist: " + exist);
		assertNotNull(exist);
	}
	
	@Test
	void queryDsl_PageableTest() {
		// given
		String email = "a20828237@gmaissssl.com";
		// when
		Integer exist = jpaQueryFactory.selectOne()
				.from(account)
				.where(account.email.eq(email))
				.fetchFirst();
		// then
		log.info("exist: " + exist);
		assertNotNull(exist);
	}

	@Test
	void querydsl_commentCount(){
		// given
		Long id = 13L;
		Long count = jpaQueryFactory.select(comment.count())
				.from(comment)
				.where(board.id.eq(id))
				.fetchOne();

		assertNotNull(count);
		assertEquals(count, 6);
	}

	@Test
	void querydsl_댓글조회() {
		// given
		Long id = 13L;
		List<CommentResponseDto> list = jpaQueryFactory
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
				.fetch();



		assertNotNull(list);
		assertEquals(list.size(), 6);
	}

}
