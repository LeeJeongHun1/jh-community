package kr.co.communityJh.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @author "jhlee" global exception handler @Controller 의 전역
 * 컨트롤러에서 발생하는 Exception 처리 Class
 * error boardPageListWithSearch handler
 */
@ControllerAdvice
@Controller
@Slf4j
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
		model.addAttribute("msg", e.getMessage());
		return "error/404";
	}
	
	@ExceptionHandler(value = UsernameNotFoundException.class)
	public String usernameNotFoundException(UsernameNotFoundException e) {
		return "/user/login?error=true&";
	}

	@ExceptionHandler(value = ConversionFailedException.class)
	public String conversionFailedException(Model model) {
		model.addAttribute("msg", ErrorCode.BOARD_NOT_FOUND.getMessage());
		return "error/404";
	}


}