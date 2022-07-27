/**
 * @author jhlee
 * Created By jhlee on 2022. 7. 18.
 */
package kr.co.communityJh.auth;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.communityJh.exception.ErrorResponse;

//@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(SC_FORBIDDEN);
		ErrorResponse errorResponse = new ErrorResponse(403, "해당 자원에 대한 접근 권한이 없습니다.");
		response.setContentType(APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("utf-8");
		new ObjectMapper().writeValue(response.getWriter(), errorResponse);
	}

}
