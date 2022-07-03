package kr.co.communityJh.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // spring security filter 체인 등록
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder encoderPassword() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
			.authorizeRequests()
				.antMatchers("/css/**", "/", "/**").permitAll()
//				.antMatchers("/boards/**").hasRole("USER")
				.anyRequest().authenticated()
//				.anyRequest().permitAll()
				.and()
			.formLogin()
				.permitAll()
				.loginPage("/user/loginForm")
				.loginProcessingUrl("/user/login")
				.usernameParameter("email")
				.defaultSuccessUrl("/");
//			.logout()
//				.logoutUrl("/logout");
//				.logoutSuccessUrl("/user/loginForm");
	}
	
}
