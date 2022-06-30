package kr.co.communityJh.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.communityJh.service.BoardService;
import kr.co.communityJh.vo.Board;
import kr.co.communityJh.vo.User;

/**
 * @author "jhlee"
 * 커뮤니티 게시판 컨트롤러 
 */
@Controller
@RequestMapping("/boards")
public class BoardController {

	@Autowired
	BoardService BoardService;
	
	/**
	 * @return 게시판 list page
	 * db에 조회된 모든 게시글을 보여준다. 추후 페이징, auth 설정 필요
	 */
	@GetMapping
	public String boards(Model model) {
		System.out.println("/ 접속");
		model.addAttribute("boards", BoardService.findAll());
		return "board/list";
		
	}
	/**
	 * @return 게시판 list page
	 * db에 조회된 모든 게시글을 보여준다. 추후 페이징, auth 설정 필요
	 */
	@GetMapping({"/detail/{seq}", "/detail"})
	public String detail(@PathVariable Optional<Integer> seq, Model model, @AuthenticationPrincipal User a) {
//		System.out.println("detail 접속" + seq.get());
//		System.out.println(seq.orElseThrow(() -> {
//			return new BoardIdNotFoundException("해당 게시글을 찾을 수 없습니다." );
//		}));
//		model.addAttribute("boards", BoardService.findById());
		if(seq.isPresent()) {
			model.addAttribute("boards", BoardService.findById(seq.get()));
		}else {
			
		}
		return "board/detail";
		
	}
	
	@GetMapping("/write")
	public String write(Model model) {
		System.out.println("write 접속");
		model.addAttribute("board", new Board());
		return "board/write";
		
	}
	
	/**
	 * @param board
	 * @return
	 */
	@PostMapping("/write")
	public String write(@Valid Board board, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "board/write";
		}
		BoardService.save(board);
		return "redirect:/boards/detail/" + board.getSeq();
		
	}
	
}