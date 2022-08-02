package kr.co.communityJh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

/**
 * @author "jhlee"
 * index 컨트롤러 
 */
@Controller
@RequestMapping("/")
public class IndexController {
	
	/**
	 * @return index boardPageListWithSearch
	 */
	@GetMapping()
	public String index() {
		return "index";
	}

}