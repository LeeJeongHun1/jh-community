package kr.co.communityJh.board.service;

import java.time.LocalDateTime;
import java.util.List;

import kr.co.communityJh.board.domain.Like;
import kr.co.communityJh.board.dto.*;
import kr.co.communityJh.board.repository.LikeQueryRepository;
import kr.co.communityJh.board.repository.LikeRepository;
import kr.co.communityJh.exception.CustomException;
import kr.co.communityJh.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.communityJh.account.dto.AccountRequestDto;
import kr.co.communityJh.board.domain.Board;
import kr.co.communityJh.board.repository.BoardQueryRepository;
import kr.co.communityJh.board.repository.BoardRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
	private final BoardRepository boardRepository;
	private final BoardQueryRepository boardQueryRepository;
	private final LikeRepository likeRepository;
	private final LikeQueryRepository likeQueryRepository;

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
		boardRepository.deleteById(id);
		return 1L;
	}

	/**
	 * Board.id를 통해 Board Table 단건 조회
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

//	@Transactional
//	public BoardDetailResponseDto readBoardWithComment(Long id) {
//		Board board = boardQueryRepository.findById(id).orElseThrow(() -> {
//			return new CustomException(ErrorCode.NOT_FOUND);
//		});
//		updateViewCount(board);
//		return BoardDetailResponseDto.builder().board(board).build();
//	}

	/**
	 * 게시글 정보와 해당 게시글에 대한 댓글 List
	 * @param id
	 * @return
	 */
	@Transactional
	public BoardDetailResponseDto readBoardWithCommentWithLike(Long id, AccountRequestDto accountRequestDto) {
		List<BoardDetailResponseDto> list = boardQueryRepository.findByIdWithCommentWithLike(id);
		// 게시글이 존재하지 않는 Id일 경우
		if (list.isEmpty()) {
			throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
		}
		BoardDetailResponseDto boardDetailResponseDto = list.get(0);
		boardDetailResponseDto.sortCommentCollection();
		boardDetailResponseDto.distinctLikeList();

		if (accountRequestDto != null) {
			boardDetailResponseDto.findUserLike(accountRequestDto);
		}else {
			boardDetailResponseDto.setUserLike(false);
		}

		return boardDetailResponseDto;
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

	@Transactional
	public BoardDetailResponseDto changeLike(LikeRequestDto likeRequestDto, AccountRequestDto accountRequestDto) {
		// 현재 좋아요를 누른 상태 -> Like Table 해당 좋아요 제거
		if(likeRequestDto.isLike()){
			Long likeId = likeQueryRepository.findByBoardIdAndAccountId(likeRequestDto);
			likeRepository.deleteById(likeId);
		}else { // 좋아요를 안 누른 상태 -> Like Table 좋아요 추가
			likeRepository.save(likeRequestDto.toEntity());
		}
		List<LikeResponseDto> list = likeQueryRepository.findAllByBoardId(likeRequestDto);
		String userName = accountRequestDto.getNickname();
		boolean userLike = list.stream().filter(l -> userName.equals(l.getAccountNickname())).count() == 1 ? true : false;

		return BoardDetailResponseDto.builder()
				.userLike(userLike)
				.likeList(list)
				.build();
	}
}
