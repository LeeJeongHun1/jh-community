package kr.co.communityJh.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.communityJh.entity.Board;
import kr.co.communityJh.repository.BoardQueryRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@ActiveProfiles("local")
//@DataJpaTest
class QueryDslTest {

	@Autowired JPAQueryFactory jpaQueryFactory;
	@Autowired BoardQueryRepository boardQueryRepository;
	
	@Test
	void queryDsl_조회테스트() {
		// given
		// 검색 board.id
		int id = 4;
		
		// when
		Board board = boardQueryRepository.findById(id);
		
		// then
		assertNotNull(board);
		assertEquals(board.getId(), id);
	}

}
