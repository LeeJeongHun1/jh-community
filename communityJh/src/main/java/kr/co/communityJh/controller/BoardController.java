package kr.co.communityJh.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.communityJh.service.BoardService;
import kr.co.communityJh.vo.Board;

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
	public String boards(Model model,
			@PageableDefault(
					page = 0,
					size = 1,
					sort = "createDate",
					direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Board> boards = BoardService.findAll(pageable);
 		int startPageNumber = BoardService.getStartPageNumber(boards.getPageable());
		int endPageNumber = BoardService.getEndPageNumber(
				boards.getPageable().getPageNumber(), boards.getTotalPages());
		model.addAttribute("startPageNumber", startPageNumber);
		model.addAttribute("endPageNumber", endPageNumber);
		model.addAttribute("boards", boards);
		return "board/list";
		
	}
	/**
	 * 게시글 seq에 해당하는 게시글 상세 페이지
	 * db에 조회된 모든 게시글을 보여준다. 추후 페이징, auth 설정 필요
	 * seq 값이 없을 경우 list 페이지로 리턴
	 * @param seq
	 * @return 게시판 상세 페이지
	 * @throws Exception 
	 */
	@GetMapping("/{seq}")
	public String detail(@PathVariable Optional<Integer> seq, Model model) throws IllegalArgumentException {
		if(!seq.isPresent()) {
			return "redirect:/boards";
		}
		model.addAttribute("board", BoardService.findById(seq.get())
				.orElseThrow(() -> {
					return new IllegalArgumentException("<h1>해당 게시글은 존재하지 않습니다!</h1>");
				}));
		return "board/detail";
		
	}
	
	/**
	 * 게시글 작성 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/write")
	public String write(Model model) {
		model.addAttribute("board", new Board());
		return "board/write";
		
	}
	
	/**
	 * 게시글 등록
	 * @param board
	 * @return
	 */
	@PostMapping("/write")
	public String write(@Valid Board board, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "board/write";
		}
		BoardService.save(board);
		return "redirect:/boards/" + board.getId();
		
	}
	
	/**
	 * 게시글 수정
	 * @param board
	 * @return
	 */
	@PutMapping("/write")
	public String update(@Valid @RequestBody Board board,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "board/write";
		}
		BoardService.save(board);
		return "redirect:/boards/detail/" + board.getId();
		
	}
	
}