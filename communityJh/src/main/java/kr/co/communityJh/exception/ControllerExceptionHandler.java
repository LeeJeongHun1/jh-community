package kr.co.communityJh.exception;

import java.util.NoSuchElementException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author "jhlee" global exception handler @Controller 의 전역
 * 컨트롤러에서 발생하는 Exception 처리 Class
 * error boardPageListWithSearch handler
 */
@ControllerAdvice
@Controller
@Log4j2
public class ControllerExceptionHandler {
	/**
	 * 컨트롤러에서 NullPointException 발생시 해당 메소드 호출
	 * 
	 * @param e
	 * @return error boardPageListWithSearch
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
	@ExceptionHandler(value = CustomException.class)
	public String illegalArgumentException(CustomException e, Model model) {
		log.info(e.getMessage());
		model.addAttribute("msg", e.getMessage());
		return "error/404";
	}
	
	@ExceptionHandler(value = UsernameNotFoundException.class)
	public String usernameNotFoundException(UsernameNotFoundException e) {
		return "/user/login?error=true&";
	}

	@GetMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		return "error/404";
	}

}