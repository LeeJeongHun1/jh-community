package kr.co.communityJh.jpa;

import static kr.co.communityJh.entity.QAccount.account;
import static kr.co.communityJh.entity.QBoard.board;
import static kr.co.communityJh.entity.QComment.comment;
import static kr.co.communityJh.entity.QRole.role1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import kr.co.communityJh.board.dto.*;
import kr.co.communityJh.board.repository.BoardQueryRepository;
import kr.co.communityJh.entity.Board;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.communityJh.entity.Role;
import lombok.extern.slf4j.Slf4j;

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
//		List<BoardWithCommentDto> list = jpaQueryFactory
//				.select(Projections.fields(BoardWithCommentDto.class,
//						board.id,
//						board.body,
//						board.title,
//						board.createDate,
//						board.account.nickname.as("accountNickname"),
//						board.viewCount,
//						board.comments.as("commentList")
//				))
//				.from(board)
//				.innerJoin(board.account, account)
//				.leftJoin(board.comments, comment)
//				.where(board.id.eq(id).and(comment.board.id.eq(id)))
//				.fetch();
		Board board1 = jpaQueryFactory
				.selectDistinct(board)
				.from(board)
//				.innerJoin(board.account, account)
				.innerJoin(board.account.roles, role1)
				.leftJoin(board.comments).fetchJoin()
				.where(board.id.eq(id))
				.fetchOne();


		System.out.println(board1.getComments().stream().map(comment1 -> comment1.getBody()));

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
		Long id = 4L;
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

}
