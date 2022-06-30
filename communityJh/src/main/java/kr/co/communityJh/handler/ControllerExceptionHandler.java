package kr.co.communityJh.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author "jhlee"
 * global exception handler
 * @RestController, @Controller 의 전역 컨트롤러에서 발생하는 Exception 처리 Class
 */
@ControllerAdvice
@Controller
public class ControllerExceptionHandler {


	/**
	 * 컨트롤러에서 NullPointException 발생시 해당 메소드 호출
	 * @param e
	 * @return error page
	 */
	@ExceptionHandler(value = NullPointerException.class)
	public String nullPointException(NullPointerException e) {
		return "<h1>없는 사용자</h1>";
	}
}