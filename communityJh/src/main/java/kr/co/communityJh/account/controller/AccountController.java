package kr.co.communityJh.account.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

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
import kr.co.communityJh.exception.ErrorCode;
import kr.co.communityJh.account.dto.AccountRequestDto;
import kr.co.communityJh.account.dto.JoinRequestDto;
import kr.co.communityJh.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author "jhlee"
 * 회원가입, 로그인 컨트롤러 
 */
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class AccountController {

	private final AccountService accountService;
	
	
	/**
	 * @return login boardPageListWithSearch
	 */
	@GetMapping("/login")
	public String loginForm(HttpServletRequest request,
							@AuthUser AccountRequestDto accountRequestDTO,
							@RequestParam(value = "error", required = false, defaultValue = "false") Boolean isError,
							@RequestParam(required = false, defaultValue = "0") int code,
							Model model) {
		// login boardPageListWithSearch 오기 전 pageUri session에 저장
		String uri = request.getHeader("Referer");
		log.info("login boardPageListWithSearch?");
//		// login 정보가 남아있으면 '/' 페이지로 이동
		if(accountRequestDTO != null) {
			return "redirect:/";
		}
//		이전 boardPageListWithSearch 세션에 저장
		if(uri != null && !uri.contains("/login")) {
			request.getSession().setAttribute("prevPage", uri);
		}
		
		// login 실패시 error msg
		if(isError) {
			ErrorCode errorCode = Arrays.stream(ErrorCode.values())
					.filter(e -> e.getCode() == code)
					.findFirst().get();
			model.addAttribute("errorMsg", errorCode.getMessage());
		}
		
		return "user/loginForm";
		
	}
	
	/**
	 * @return joinForm boardPageListWithSearch
	 */
	@GetMapping("/join")
	public String joinForm(@ModelAttribute("account") JoinRequestDto joinRequestDto) {
		return "user/join";
		
	}
	
	
	/**
	 * 회원 가입 완료 후 login boardPageListWithSearch 리다이렉트
	 * @param joinRequestDto
	 * @param bindingResult
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