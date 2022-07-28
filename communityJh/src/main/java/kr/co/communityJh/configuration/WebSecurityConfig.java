package kr.co.communityJh.configuration;

import kr.co.communityJh.auth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity // spring security filter 체인 등록
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class    WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomLoginFailHandler customLoginFailHandler;
    private final CustomLoginSuccessHandler customLoginSuccessHandler;
//    private final CustomAuthorizationFilter authorizationFilter;
//    private final CustomAccessDeniedHandler accessDeniedHandler;

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
//        CustomAuthenticationFilter customAuthenticationFilter =
//                new CustomAuthenticationFilter(authenticationManagerBean());
//        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
//        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler);
//        customAuthenticationFilter.setAuthenticationFailureHandler(customLoginFailHandler);

        http.csrf().disable();
        // spring security session 사용 안함.
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/", "/user/**", "/api/join/**", "/api/login/**").permitAll();
        http.authorizeRequests().antMatchers("/boards/**").hasRole("USER");
        http.authorizeRequests().anyRequest().authenticated();

//        http.addFilter(customAuthenticationFilter);
//        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
//        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        http
            .formLogin()
                .loginPage("/user/login") // loginform 이동
                .loginProcessingUrl("/user/loginProc") // login 처리
                .usernameParameter("email") // loadUserByUsername 메소드 parameter 설정
                .failureHandler(customLoginFailHandler)
                .successHandler(customLoginSuccessHandler) // login 성공 후 handler
			.and()
				.logout();
    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManager();
//    }

}
