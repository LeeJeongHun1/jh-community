package kr.co.zaritalk.jpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.co.communityJh.CommunityJhApplication;
import kr.co.communityJh.repository.AccountRepository;
import kr.co.communityJh.vo.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CommunityJhApplication.class)

//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@ActiveProfiles("local")
//@DataJpaTest
class JpaTest {

	@Autowired AccountRepository accountRepository;
	
	@Test
	void findByIdTest() {
		// given
		int id = 2;
		
		// when
		User u = accountRepository.findById(1).orElseThrow();
		
		System.out.println(u);
		// then
		assertNotNull(u);
	}

}
