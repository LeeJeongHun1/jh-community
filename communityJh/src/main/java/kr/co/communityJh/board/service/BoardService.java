package kr.co.communityJh.board.service;

import java.time.LocalDateTime;

import kr.co.communityJh.account.repository.AccountQueryRepository;
import kr.co.communityJh.board.dto.*;
import kr.co.communityJh.comment.repository.CommentQueryRepository;
import kr.co.communityJh.exception.CustomException;
import kr.co.communityJh.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.communityJh.account.dto.AccountRequestDto;
import kr.co.communityJh.board.domain.Board;
import kr.co.communityJh.board.repository.BoardQueryRepository;
import kr.co.communityJh.board.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	private final BoardQueryRepository boardQueryRepository;
	private final AccountQueryRepository accountQueryRepository;
	private final CommentQueryRepository commentQueryRepository;

	/**
	 * paging, 검색 관련 리스트 조회
	 * @param boardPageWithSearchDto 
	 * @return
	 */
	@Transactional(readOnly = true)
	public BoardPageResponseDto<Object> boardPageListWithSearch(BoardPageWithSearchDto boardPageWithSearchDto) {
		Page<BoardInfoDto> boardPageRequestDto = boardQueryRepository.boardPageListWithSearch(boardPageWithSearchDto);
//		commentQueryRepository.countByBoardId()
		return BoardPageResponseDto.toResponse().boardPageRequestDto(boardPageRequestDto).build();
	}

	/**
	 * 게시글 등록
	 * @param boardWriteDTO
	 * @param accountRequestDTO
	 * @return
	 */
	@Transactional
	public BoardWriteDto save(BoardWriteDto boardWriteDTO, AccountRequestDto accountRequestDTO) {
		Board board = boardWriteDTO.toBoardEntity();
		board.setAccount(accountRequestDTO.toEntityAccount());
		return boardRepository.save(board).toBoardDtd();
	}

	/**
	 * @param id 게시글 id
	 * @return 1==성공, 0==실패
	 */
	@Transactional
	public Long deleteBoardById(Long id, AccountRequestDto accountRequestDto) {
		accountQueryRepository.findByEmail(accountRequestDto.getEmail())
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		return boardQueryRepository.deleteById(id);
	}
	
	/**
	 * Board.id를 통해 Board Table 단건 조회
	 * 해당 Board.viewCount 수정 // 더티체킹
	 * @param id 
	 * @return Board
	 */
	@Transactional(readOnly = true)
	public BoardInfoDto findById(Long id) {
		BoardInfoDto boardInfoDto = boardQueryRepository.findDtoById(id).orElseThrow(() -> {
			return new CustomException(ErrorCode.NOT_FOUND);
		});
		return boardInfoDto;
	}

	/**
	 * 게시글 정보와 해당 게시글에 대한 댓글 List 
	 * @param id 
	 * @return
	 */
	@Transactional
	public BoardWithCommentDto readBoardWithComment(Long id) {
		Board board = boardQueryRepository.findById(id).orElseThrow(() -> {
			return new CustomException(ErrorCode.NOT_FOUND);
		});
		updateViewCount(board);
		return new BoardWithCommentDto(board);
	}

	/**
	 * 게시글 방문시 조회수 증가 (추후 쿠키 적용하여 중복방지)
	 * @param board 
	 */
	public void updateViewCount(Board board) {
		board.setViewCount(board.getViewCount() +1);
	}

	/**
	 * 게시글 update
	 * @param boardWriteDTO
	 * @return
	 */
	@Transactional
	public BoardWriteDto updateBoardById(BoardWriteDto boardWriteDTO) {
		Board board = boardQueryRepository.findEntityById(boardWriteDTO.getId()).orElseThrow(() -> {
			return new CustomException(ErrorCode.BOARD_NOT_FOUND);
		});
		board.setTitle(boardWriteDTO.getTitle());
		board.setBody(boardWriteDTO.getBody());
		board.setLastUpdateDate(LocalDateTime.now());
		BoardWriteDto dto = board.toBoardDtd();
		return dto;
	}

}
