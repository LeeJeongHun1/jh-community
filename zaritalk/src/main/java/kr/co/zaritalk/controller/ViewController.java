package kr.co.zaritalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import kr.co.zaritalk.service.BoardService;

/**
 * @author "jhlee"
 * 커뮤니티 게시판 컨트롤러 
 * 게시글 목록, 조회, 수정, 삭제 RestAPI
 */
@Controller
@RequestMapping("/")
//@Api(tags = "커뮤니티_API문서")
public class ViewController {

	@Autowired
	BoardService service;
	
	/**
	 * @return 게시글 목록
	 */
	@GetMapping()
	public String boards() {
		return "test";
		
	}
	
}