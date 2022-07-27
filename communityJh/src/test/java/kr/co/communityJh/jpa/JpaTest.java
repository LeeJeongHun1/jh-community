package kr.co.communityJh.jpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import kr.co.communityJh.CommunityJhApplication;
import kr.co.communityJh.account.repository.AccountRepository;
import kr.co.communityJh.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CommunityJhApplication.class)
@RequiredArgsConstructor
@Transactional
class JpaTest {

	@Autowired AccountRepository accountRepository;
	@Autowired BoardRepository boardRepository;
	
	@Test
	void findByIdTest() {
		// given
		Long id = 4L;
		
		// when
//		Account u = accountRepository.findEntityById(1L).orElseThrow();
		
		System.out.println(id);
//		// then
//		assertNotNull(u);
	}
	
	@Test
	void 게시글조회() {
		// given
		Long id = 4L;
		
		// when
//		Board b = boardRepository.findEntityById(id).orElseThrow();
//		
//		System.out.println(b);
//		// then
//		assertNotNull(b);
	}

}
