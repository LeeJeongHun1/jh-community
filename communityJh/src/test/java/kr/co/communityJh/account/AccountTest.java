package kr.co.communityJh.account;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.communityJh.CommunityJhApplication;
import kr.co.communityJh.entity.Account;
import kr.co.communityJh.service.AccountService;

@SpringBootTest(classes = CommunityJhApplication.class)
@Transactional
@AutoConfigureMockMvc
//@WebMvcTest(UserController.class)
class AccountTest {

	@Autowired MockMvc mockMvc;
	
	@MockBean AccountService accountService;
	@Autowired ObjectMapper objectMapper;

	
	@BeforeEach
	void beforeEach() {
//		Role role = Role.builder()
//				.Id(1)
//				.role(AccountType.ROLE_USER)
//				.build();
//		Role role2 = Role.builder()
//				.Id(2)
//				.role(AccountType.ROLE_ADMIN)
//				.build();
//		accountService.createRoles(role);
//		accountService.createRoles(role2);
	}
	
	/**
	 * login test case
	 * @throws Exception
	 */
	@Test
	@Disabled
	@DisplayName("로그인 테스트")
	void loginTest() throws Exception {
		Account user = Account.builder()
				.email("aa@naver.com")
				.password("1234")
				.build();
		mockMvc.perform(formLogin()
				.user(user.getEmail())
				.password(user.getPassword())
				.userParameter("email")
				.loginProcessingUrl("/user/login"))
			.andExpect(SecurityMockMvcResultMatchers.authenticated());
	}
	
	@Test
	@DisplayName("회원가입 테스트")
	void joinTest() throws Exception {
		Account account = Account.builder()
				.email("acc@naver.com")
				.password("1234")
				.nickname("acc")
				.build();
		mockMvc.perform(post("/register")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(objectMapper.writeValueAsString(account)))
		.andExpect(status().isOk())
		.andDo(print());
	}

}
