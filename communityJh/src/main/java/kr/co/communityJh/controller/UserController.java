package kr.co.communityJh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.communityJh.service.UserService;
import kr.co.communityJh.vo.User;

/**
 * @author "jhlee"
 * 회원가입, 로그인 컨트롤러 
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService service;
	
	/**
	 * @return login page
	 * 회원가입
	 */
	@PostMapping("/join")
	public String userJoin(@RequestBody User user) {
		service.selectUser(null);
		return "board/list";
		
	}
	
	
}