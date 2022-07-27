package kr.co.communityJh.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomAuthenticationFilter {
//		extends UsernamePasswordAuthenticationFilter {

//	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
//		super(authenticationManager);
//	}
//
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//			throws AuthenticationException {
//		String username = request.getParameter("email");
//		String password = request.getParameter("password");
//		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//
//		return this.getAuthenticationManager().authenticate(token);
//	}
}
