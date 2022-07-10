package kr.co.communityJh.jpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import kr.co.communityJh.CommunityJhApplication;
import kr.co.communityJh.entity.Account;
import kr.co.communityJh.entity.Board;
import kr.co.communityJh.repository.AccountRepository;
import kr.co.communityJh.repository.BoardRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CommunityJhApplication.class)
@Transactional
class JpaTest {

	@Autowired AccountRepository accountRepository;
	@Autowired BoardRepository boardRepository;
	
	@Test
	void findByIdTest() {
		// given
		int id = 4;
		
		// when
		Account u = accountRepository.findById(1).orElseThrow();
		
		System.out.println(u);
		// then
		assertNotNull(u);
	}
	
	@Test
	void 게시글조회() {
		// given
		int id = 4;
		
		// when
		Board b = boardRepository.findById(id).orElseThrow();
		
		System.out.println(b);
		// then
		assertNotNull(b);
	}

}
