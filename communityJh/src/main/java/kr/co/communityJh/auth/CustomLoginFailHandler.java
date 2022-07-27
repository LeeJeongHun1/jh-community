package kr.co.communityJh.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.communityJh.exception.ErrorCode;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * login 실패 후 처리 handler
 * @author jhlee
 * @since 2022. 07. 14
 */
@Component
public class CustomLoginFailHandler implements AuthenticationFailureHandler {
	private final String DEFAULT_FAILURE_URL = "/user/login?error=true&code=";

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		int code = 0;
		
		// exception에 따른 error msg 추출
		if(exception instanceof BadCredentialsException) { // email or password 불일치
			code = ErrorCode.INVALID_ID_PASSWORD.getCode();
		} else if (exception instanceof UsernameNotFoundException) { // user가 존재하지 않음
			code = ErrorCode.USER_NOT_FOUND.getCode();
		} else if(exception instanceof InternalAuthenticationServiceException) { // server error
			code = ErrorCode.INTERNAL_ERROR.getCode();
		} else if(exception instanceof DisabledException) { // account is disabled
			code = ErrorCode.ACCOUNT_DISABLED_ERROR.getCode();
		} else if(exception instanceof CredentialsExpiredException) { // expired the credential
			code = ErrorCode.CREDENTIALS_EXPIRED_ERROR.getCode();
		} else {
			code = ErrorCode.ELSE_ERROR.getCode();
		}
		
		response.sendRedirect(DEFAULT_FAILURE_URL + code);
		// error msg 세션에 저장 후 login boardPageListWithSearch 포워드.
//		request.setAttribute("msg", code);
//		request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
	}


}
