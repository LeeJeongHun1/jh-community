package kr.co.zaritalk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.zaritalk.service.BoardService;
import kr.co.zaritalk.vo.Board;

@RestController
@RequestMapping("/board")
public class ZaritalkController {

	@Autowired
	BoardService service;
	
	@GetMapping()
	public List<Board> getList() {
//		service.getList();
//		System.out.println(service.getList());
		return service.selectList();
	}
}
