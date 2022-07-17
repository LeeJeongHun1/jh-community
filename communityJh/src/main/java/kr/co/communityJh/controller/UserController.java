package kr.co.communityJh.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.communityJh.auth.AuthUser;
import kr.co.communityJh.auth.LoginErrorCode;
import kr.co.communityJh.dto.account.AccountRequestDto;
import kr.co.communityJh.dto.account.JoinRequestDto;
import kr.co.communityJh.service.AccountService;
import lombok.extern.log4j.Log4j2;

/**
 * @author "jhlee"
 * 회원가입, 로그인 컨트롤러 
 */
@Controller
@RequestMapping("/user")
@Log4j2
public class UserController {

	@Autowired
	AccountService accountService;
	
	
	/**
	 * @return login page
	 */
	@GetMapping("/login")
	public String loginForm(HttpServletRequest request, @AuthUser AccountRequestDto accountRequestDTO,
			@RequestParam(value = "error", required = false, defaultValue = "false") Boolean isError,
			@RequestParam(required = false, defaultValue = "0") int code,
			Model model) {
		// login page 오기 전 pageUri session에 저장
		String uri = request.getHeader("Referer");
//		// login 정보가 남아있으면 '/' 페이지로 이동
		if(accountRequestDTO != null) {
			return "redirect:/";
		}
//		이전 page 세션에 저장
		if(uri != null && !uri.contains("/login")) {
			request.getSession().setAttribute("prevPage", uri);
		}
		
		// login 실패시 error msg
		if(isError) {
			LoginErrorCode loginErrorCode = Arrays.stream(LoginErrorCode.values())
					.filter(e -> e.getCode() == code)
					.findFirst().get();
			model.addAttribute("errorMsg", loginErrorCode.getMessage());
		}
		
		return "user/loginForm";
		
	}
	
	/**
	 * @return joinForm page
	 */
	@GetMapping("/join")
	public String joinForm(@ModelAttribute("account") JoinRequestDto joinRequestDto) {
		return "user/join";
		
	}
	
	
	/**
	 * 회원 가입 완료 후 login page 리다이렉트
	 * @param accountDTO login 정보
	 * @return 
	 */
	@PostMapping("/join")
	public String userRegister(@Validated @ModelAttribute("account") JoinRequestDto joinRequestDto,
			BindingResult bindingResult) {
		boolean isError = false;
		if(bindingResult.hasErrors()) {
			return "user/join";
		}
		// email 중복
		if(accountService.existByEmail(joinRequestDto.getEmail())) {
			bindingResult.reject("duplicateEmail", "이미 존재하는 이메일입니다.");
			isError = true;
		}
		
		// nickname 중복
		if(accountService.existByNickname(joinRequestDto.getNickname())) {
			bindingResult.reject("duplicateNickname", "이미 존재하는 닉네임입니다.");
			isError = true;
		}
		
		if(isError) {
			return "user/join";
		}
		
		accountService.join(joinRequestDto);
		return "redirect:/";
		
	}
	
	
}