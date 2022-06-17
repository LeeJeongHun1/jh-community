package kr.co.zaritalk.account;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import kr.co.communityJh.CommunityJhApplication;

@SpringBootTest(classes = CommunityJhApplication.class)
@Transactional
@AutoConfigureMockMvc
class AccountTests {

	@Autowired MockMvc mockMvc;
	
	/**
	 * login test case
	 * @throws Exception
	 */
	@Test
	@DisplayName("login sucesss")
	void loginTest() throws Exception {
		String id = "asdd";
		String pw = "asd";
		mockMvc.perform(formLogin()
				.user(id)
				.password(pw)
				.userParameter("id")
				.loginProcessingUrl("/user/login"))
			.andExpect(SecurityMockMvcResultMatchers.authenticated());
	}

}
