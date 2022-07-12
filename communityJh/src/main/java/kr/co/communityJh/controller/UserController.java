package kr.co.communityJh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.communityJh.dto.AccountRequestDTO;
import kr.co.communityJh.service.AccountService;

/**
 * @author "jhlee"
 * 회원가입, 로그인 컨트롤러 
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	AccountService accountService;
	
	
	/**
	 * @return login page
	 */
	@GetMapping("/loginForm")
	public String loginForm(HttpServletRequest request) {
		// login page 오기 전 pageUrl session에 저장
		String uri = request.getHeader("Referer");
		if(!uri.contains("/loginForm")) {
			request.getSession().setAttribute("prevPage", uri);
		}
		return "user/login";
		
	}
	
	/**
	 * @return joinForm page
	 */
	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/join";
		
	}
	
	
	/**
	 * 회원 가입 완료 후 login page 리다이렉트
	 * @param accountDTO login 정보
	 * @return 
	 */
	@PostMapping("/register")
	public String userRegister(AccountRequestDTO accountDTO) {
		accountService.registerAccount(accountDTO);
		return "redirect:/user/loginForm";
		
	}
	
	
}