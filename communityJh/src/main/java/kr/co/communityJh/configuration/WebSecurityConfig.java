package kr.co.communityJh.configuration;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kr.co.communityJh.auth.CustomLoginFailHandler;
import kr.co.communityJh.auth.CustomLoginSuccessHandler;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // spring security filter 체인 등록
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final CustomLoginSuccessHandler loginSuccessHandler;
	private final CustomLoginFailHandler customLoginFailHandler;
	
	@Bean
	public BCryptPasswordEncoder encoderPassword() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
			.authorizeRequests()
				.antMatchers("/", "/user/**").permitAll()
//				.antMatchers("/boards/**").hasRole("USER")
				.anyRequest().authenticated()
//				.anyRequest().permitAll()
				.and()
			.formLogin()
				.loginPage("/user/login") // loginform 이동
				.loginProcessingUrl("/user/loginProc") // login 처리
				.usernameParameter("email") // loadUserByUsername 메소드 parameter 설정
				.failureHandler(customLoginFailHandler)
				.successHandler(loginSuccessHandler) // login 성공 후 handler
				.and()
			.logout();
//			.logout()
//				.logoutUrl("/logout");
//			.logoutSuccessUrl("/");
	}
	
}
