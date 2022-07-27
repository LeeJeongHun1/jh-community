package kr.co.communityJh.board.service;

import java.time.LocalDateTime;
import java.util.List;

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
import kr.co.communityJh.entity.Board;
import kr.co.communityJh.board.repository.BoardQueryRepository;
import kr.co.communityJh.board.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	private final BoardQueryRepository boardQueryRepository;
	private final AccountQueryRepository accountQueryRepository;
	private final CommentQueryRepository commentQueryRepository;
	@Transactional(readOnly = true)
	public BoardPageResponseDto<Object> boardPageListWithSearch(BoardPageWithSearchDto boardPageWithSearchDto) {
		Page<BoardInfoDto> boardPageRequestDto = boardQueryRepository.boardPageListWithSearch(boardPageWithSearchDto);
//		commentQueryRepository.countByBoardId()
		return BoardPageResponseDto.toResponse().boardPageRequestDto(boardPageRequestDto).build();
	}
	@Transactional
	public BoardDto save(BoardDto boardDTO, AccountRequestDto accountRequestDTO) {
		Board board = boardDTO.toBoardEntity();
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
	@Transactional
	public BoardDto findEntityById(Long id) {
		Board board = boardQueryRepository.findEntityById(id).orElseThrow(() -> {
			return new CustomException(ErrorCode.NOT_FOUND);
		});
		updateViewCount(board);
		BoardDto dto = board.toBoardDtd();
		return dto;
	}

	@Transactional
	public BoardInfoDto findById(Long id) {
		BoardInfoDto boardInfoDto = boardQueryRepository.findDtoById(id).orElseThrow(() -> {
			return new CustomException(ErrorCode.NOT_FOUND);
		});
		return boardInfoDto;
	}

	public void readBoardWithComment(Long id) {
		List<Board> list = boardQueryRepository.commet(id);
	}

	public void updateViewCount(Board board) {
		board.setViewCount(board.getViewCount() +1);
	}

	@Transactional
	public BoardDto updateBoardById(BoardDto boardDTO) {
		Board board = boardQueryRepository.findEntityById(boardDTO.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("<h1>해당 게시글은 존재하지 않습니다!</h1>");
		});
		board.setTitle(boardDTO.getTitle());
		board.setBody(boardDTO.getBody());
		board.setLastUpdateDate(LocalDateTime.now());
		BoardDto dto = board.toBoardDtd();
		return dto;
	}

}
