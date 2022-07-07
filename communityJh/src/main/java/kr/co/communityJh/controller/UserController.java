package kr.co.communityJh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.communityJh.dto.AccountDTO;
import kr.co.communityJh.entity.Account;
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
	 * 회원가입
	 */
//	@PostMapping("/login")
//	public String userJoin(User user) {
//		System.out.println(user);
//		return "user/login";
//	}
	
	@GetMapping("/loginForm")
	public String userSignIn() {
		return "user/login";
		
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/join";
		
	}
	
	@PostMapping("/register")
	public String userRegister(AccountDTO accountDTO) {
		accountService.registerAccount(accountDTO);
		return "redirect:/user/loginForm";
		
	}
	
	
}