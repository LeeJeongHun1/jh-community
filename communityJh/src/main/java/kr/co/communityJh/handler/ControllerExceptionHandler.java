package kr.co.communityJh.handler;

import java.util.NoSuchElementException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author "jhlee" global exception handler @Controller 의 전역
 * 컨트롤러에서 발생하는 Exception 처리 Class
 * error page handler 
 */
@ControllerAdvice
@Controller
public class ControllerExceptionHandler implements ErrorController {

	/**
	 * 컨트롤러에서 NullPointException 발생시 해당 메소드 호출
	 * 
	 * @param e
	 * @return error page
	 */
	@ExceptionHandler(value = NullPointerException.class)
	public String nullPointException(NullPointerException e) {
		return "error/404";
	}
//	
//	@ExceptionHandler(value = NoSuchElementException.class)
//	public String noSuchElemenException(NullPointerException e) {
////		return "<h1>잘못된 입력 입니다.</h1>";
//		return "/error?msg=" + e.getMessage();
//	}
//	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public String illegalArgumentException(IllegalArgumentException e) {
//		return "/error?msg=" + e.getMessage();
		return e.getMessage();
	}

	@GetMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		return "error/404";
	}

}