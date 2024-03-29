package kr.co.communityJh.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kr.co.communityJh.account.service.AccountService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//@Component
public class CustomAuthenticationProvider {
//		implements AuthenticationProvider {
//	private final AccountService accountService;
//	private final BCryptPasswordEncoder passwordEncoder;
//
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		String username = authentication.getName();
//		String password = (String) authentication.getCredentials();
//
//		UserDetails userDetails = accountService.loadUserByUsername(username);
//
//		// PW 검사
//		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//			throw new BadCredentialsException("Provider - authenticate() : 비밀번호가 일치하지 않습니다.");
//		}
//		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//		return true;
//	}
}
