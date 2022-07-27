package kr.co.communityJh.board.controller;

import java.util.Optional;

import javax.validation.Valid;

import kr.co.communityJh.board.dto.*;
import kr.co.communityJh.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import kr.co.communityJh.auth.AuthUser;
import kr.co.communityJh.account.dto.AccountRequestDto;
import kr.co.communityJh.board.service.BoardService;

/**
 * @author "jhlee"
 * 커뮤니티 게시판 컨트롤러
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

	private final CommentService commentService;
	@Autowired BoardService boardService;

	/**
	 * @param model
	 * @param boardPageWithSearchDto
	 * @return 게시판 list boardPageListWithSearch
	 * db에 조회된 모든 게시글을 보여준다. pagination
	 */
	@GetMapping
	public String boards(Model model, BoardPageWithSearchDto boardPageWithSearchDto) {
//		Page<Board> boards = boardService.findAll(searchText, option, pageable);
		log.info("page: {}", boardPageWithSearchDto);
		BoardPageResponseDto<Object> boardPageResponseDto = boardService.boardPageListWithSearch(boardPageWithSearchDto);

		model.addAttribute("boards", boardPageResponseDto);
		return "board/list";

	}

	/**
	 * 게시글 seq에 해당하는 게시글 상세 페이지
	 * db에 조회된 모든 게시글을 보여준다. 추후 페이징, auth 설정 필요
	 * seq 값이 없을 경우 list 페이지로 리턴
	 * @param id
	 * @return 게시판 상세 페이지
	 */
	@GetMapping("/{id}")
	public String detail(@PathVariable Optional<Long> id, Model model) {
		if(!id.isPresent()) {
			return "redirect:/board";
		}
		model.addAttribute("boardDto", boardService.findEntityById(id.get()));
		return "board/detail";
	}


	/**
	 * 수정 form
	 * @param id board id
	 * @param boardPageWithSearchDto page, search
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 */
	@GetMapping("/{id}/modify")
	public String modify(@PathVariable Long id,
			BoardPageWithSearchDto boardPageWithSearchDto,
			Model model) throws IllegalArgumentException {
		model.addAttribute("boardDto", boardService.findById(id));
		return "board/modify";
	}

	@PostMapping("/{id}/modify")
	public String boardModify(@PathVariable Long id,
			BoardPageWithSearchDto boardPageWithSearchDto, BoardDto boardDto) {
		boardService.updateBoardById(boardDto);
		return "redirect:/board/" + boardDto.getId();
	}


	/**
	 * 게시글 작성 페이지
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