package kr.co.zaritalk.account;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
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
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.communityJh.CommunityJhApplication;
import kr.co.communityJh.service.AccountService;
import kr.co.communityJh.vo.AccountType;
import kr.co.communityJh.vo.Role;
import kr.co.communityJh.vo.User;
import springfox.documentation.annotations.ApiIgnore;

@SpringBootTest(classes = CommunityJhApplication.class)
@Transactional
@AutoConfigureMockMvc
//@WebMvcTest(UserController.class)
class AccountTest {

	@Autowired MockMvc mockMvc;
	
	@MockBean AccountService accountService;
	
	
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
		
		
		User user = User.builder()
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
		User user = User.builder()
				.email("aa@naver.com")
				.password("1234")
				.nickname("test")
				.build();
		mockMvc.perform(post("/register")
				.content(new ObjectMapper().writeValueAsString(user))
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				
//				.user(user.getEmail())
//				.password(user.getPassword())
//				.userParameter("email")
//				.loginProcessingUrl("/user/login"))
		.andExpect(status().isOk());
	}

}
