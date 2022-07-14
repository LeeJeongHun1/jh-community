package kr.co.communityJh.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

/**
 * login 성공 후 처리 handler
 * @author jhlee
 * @since 2022. 07. 13
 */
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	/**
	 *
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String uri = "/";
		
		// security가 인터셉터 후 사용자 요청 uri 정보 객체
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		
		// 로그인 버튼 눌러 접속했을 경우 이전 페이지
		String prevPage = (String) request.getSession().getAttribute("prevPage");
		
		if (prevPage != null) {
			request.getSession().removeAttribute("prevPage");
		}

		// null이 아니라면 강제 인터셉트 당했다는 것
		if (savedRequest != null) {
			uri = savedRequest.getRedirectUrl();

		// ""가 아니라면 직접 로그인 페이지로 접속한 것
		} else if (prevPage != null && !prevPage.equals("")) {
			uri = prevPage;
			
			// 회원가입 후 login 경우
			if(prevPage.contains("user/joinForm")) {
				uri = "/";
			}
		}
		
		// user role 에 따른 리다이렉트 uri 추가 설정 해야 함.
		// ~~
		

		// 세 가지 케이스에 따른 URI 주소로 리다이렉트
		response.sendRedirect(uri);
	}

}
