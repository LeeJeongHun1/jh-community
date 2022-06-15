package kr.co.communityJh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.communityJh.service.UserService;
import kr.co.communityJh.vo.AccountType;
import kr.co.communityJh.vo.User;

/**
 * @author "jhlee"
 * 회원가입, 로그인 컨트롤러 
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
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
	public String userRegister(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(AccountType.USER);
//		System.out.println(user.getRole().toString());
		service.registerUser(user);
		return "redirect:/user/loginForm";
		
	}
	
	
}