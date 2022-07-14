package kr.co.communityJh.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * login 실패 후 처리 handler
 * @author jhlee
 * @since 2022. 07. 14
 */
@Component
public class CustomLoginFailHandler implements AuthenticationFailureHandler {
	private final String DEFAULT_FAILURE_URL = "/user/login?error=true";

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		
		// exception에 따른 error msg 추출
		String errorMessage = "";
		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
			errorMessage = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해 주십시오.";
		}
		//< account is disabled
		else if(exception instanceof DisabledException) {
			errorMessage = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
		}
		//< expired the credential
		else if(exception instanceof CredentialsExpiredException) {
			errorMessage = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요.";
		}
		else {
			errorMessage = "알수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요.";
		}
		
		
		// error msg 세션에 저장 후 login page 포워드.
		request.setAttribute("errorMsg", errorMessage);
		RequestDispatcher rd = request.getRequestDispatcher(DEFAULT_FAILURE_URL);
		rd.forward(request, response);
	}


}
