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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.communityJh.auth.AuthUser;
import kr.co.communityJh.dto.account.AccountRequestDto;
import kr.co.communityJh.dto.board.BoardDto;
import kr.co.communityJh.entity.Board;
import kr.co.communityJh.service.BoardService;

/**
 * @author "jhlee"
 * 커뮤니티 게시판 컨트롤러 
 */
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	/**
	 * @return 게시판 list page
	 * db에 조회된 모든 게시글을 보여준다. 추후 페이징, auth 설정 필요
	 */
	@GetMapping
	public String boards(Model model,
						@PageableDefault(
						page = 0,
						size = 5,
						sort = "createDate",
						direction = Sort.Direction.DESC) Pageable pageable,
						@RequestParam(required = false, defaultValue = "") String searchText,
						@RequestParam(required = false, defaultValue = "t") String option) {
		
		Page<Board> boards = boardService.findAll(searchText, option, pageable);
		
		// page 관련 pageNumber 설정
 		int startPageNumber = boardService.getStartPageNumber(boards.getPageable());
		int endPageNumber = boardService.getEndPageNumber(
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
	 * @param id
	 * @return 게시판 상세 페이지
	 * @throws Exception 
	 */
	@GetMapping("/{id}")
	public String detail(@PathVariable Optional<Long> id,
					@RequestParam(required = false, defaultValue = "0") int page,
					@RequestParam(required = false, defaultValue = "") String searchText,
					@RequestParam(required = false, defaultValue = "t") String option,
					Model model) throws IllegalArgumentException {
		if(!id.isPresent()) {
			return "redirect:/board";
		}
		model.addAttribute("boardDTO", boardService.findById(id.get()));
		return "board/detail";
	}
	
	@GetMapping("/{id}/modify")
	public String modify(@PathVariable Long id,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "") String searchText,
			@RequestParam(required = false, defaultValue = "t") String option,
			Model model) throws IllegalArgumentException {
		model.addAttribute("boardDto", boardService.findById(id));
		return "board/modify";
	}
	
	/**
	 * 게시글 작성 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/write")
	public String writeForm(@ModelAttribute("boardDto") BoardDto boardDto) {
		return "board/write";
	}
	
	/**
	 * 게시글 등록
	 * @param boardDto
	 * @return
	 */
	@PostMapping("/write")
	public String boardWrite(@Valid BoardDto boardDto,
			BindingResult bindingResult,
			@AuthUser AccountRequestDto accountRequestDTO) {
		if(bindingResult.hasErrors()) {
			return "board/write";
		}
		BoardDto boardResponseDto =  boardService.save(boardDto, accountRequestDTO);
		return "redirect:/board/" + boardResponseDto.getId();
		
	}

}