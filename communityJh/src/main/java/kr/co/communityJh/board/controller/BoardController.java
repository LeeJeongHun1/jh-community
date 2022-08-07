package kr.co.communityJh.board.controller;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import kr.co.communityJh.board.dto.*;
import kr.co.communityJh.comment.dto.CommentResponseDto;
import kr.co.communityJh.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
	private final BoardService boardService;

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
	 * 게시글 id에 해당하는 게시글 상세 페이지
	 * 좋아요 수, 댓글 수, 게시글 정보
	 * id 값이 Long이 아니거나 없는 페이지일 경우 error page
	 *
	 * @param id
	 * @param model
	 * @return 게시판 상세 페이지
	 */
	@GetMapping("/{id}")
	public String detail(@PathVariable Long id,
						 Model model,
						 @AuthUser AccountRequestDto accountRequestDto) {

		model.addAttribute("boardDto", boardService.readBoardWithCommentWithLike(id, accountRequestDto));
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

	/**
	 * @param id boardId
	 * @param boardPageWithSearchDto
	 * @param boardWriteDto
	 * @return
	 */
	@PostMapping("/{id}/modify")
	public String boardModify(@PathVariable Long id,
			BoardPageWithSearchDto boardPageWithSearchDto,
							  BoardWriteDto boardWriteDto) {
		boardService.updateBoardById(boardWriteDto);
		return "redirect:/board/" + boardWriteDto.getId();
	}


	/**
	 * 게시글 작성 페이지
	 * @return
	 */
	@GetMapping("/write")
	public String writeForm(@ModelAttribute("BoardWriteDto") BoardWriteDto boardWriteDto) {
		return "board/write";
	}
	
	/**
	 * 게시글 등록
	 * @param boardWriteDto
	 * @return
	 */
	@PostMapping("/write")
	public String boardWrite(@Valid BoardWriteDto boardWriteDto,
			BindingResult bindingResult,
			@AuthUser AccountRequestDto accountRequestDTO) {
		if(bindingResult.hasErrors()) {
			return "board/write";
		}
		BoardWriteDto boardResponseDto =  boardService.save(boardWriteDto, accountRequestDTO);
		return "redirect:/board/" + boardResponseDto.getId();
		
	}

}