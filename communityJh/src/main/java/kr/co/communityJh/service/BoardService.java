package kr.co.communityJh.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.communityJh.dto.AccountAuthDTO;
import kr.co.communityJh.dto.AccountRequestDTO;
import kr.co.communityJh.dto.BoardDTO;
import kr.co.communityJh.entity.Board;
import kr.co.communityJh.repository.BoardQueryRepository;
import kr.co.communityJh.repository.BoardRepository;

@Service
public class BoardService {
	// 한번에 보여줄 페이지 수
	private static final int PAGEABLE_SIZE = 5;

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired private BoardQueryRepository boardQueryRepository;
	
	// Slice 객체로 return 할 경우 다음 slice 존재 유무만 알고 있다.
	@Transactional(readOnly = true)
	public Page<Board> findAll(String searchText, String option, Pageable pageable) {
		if(option.equals("t")) {
			return boardRepository.findByTitleContainingOrBodyContaining(searchText, searchText, pageable);
		}else if(option.equals("c")) {
			return boardRepository.findAll(pageable);
		}
		return boardRepository.findAll(pageable);
	}
	
	@Transactional
	public BoardDTO save(BoardDTO boardDTO, AccountRequestDTO accountRequestDTO) {
		Board board = boardDTO.toBoardEntity();
		board.setAccount(accountRequestDTO.toEntityAccount());
		return boardRepository.save(board).toBoardDtd();
	}
	
	@Transactional
	public Long deleteBoardById(int id) {
		return boardQueryRepository.deleteById(id);
	}
	
	
	/**
	 * Board.id를 통해 Board Table 단건 조회
	 * 해당 Board.viewCount 수정 // 더티체킹
	 * @param id
	 * @return Board
	 */
	@Transactional
	public BoardDTO findById(int id) {
		Board board = boardQueryRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("<h1>해당 게시글은 존재하지 않습니다!</h1>");
		});
		board.setViewCount(board.getViewCount()+1);
		BoardDTO dto = board.toBoardDtd();
		return dto;
	}
	
	@Transactional
	public BoardDTO updateBoardById(BoardDTO boardDTO) {
		Board board = boardQueryRepository.findById(boardDTO.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("<h1>해당 게시글은 존재하지 않습니다!</h1>");
		});
		board.setTitle(boardDTO.getTitle());
		board.setBody(boardDTO.getBody());
		board.setLastUpdateDate(LocalDateTime.now());
		BoardDTO dto = board.toBoardDtd();
		return dto;
	}
	
	/**
	 * 현재 PageNumber를 통해 page range의 startPageNumber 반환
	 * @param pageable
	 * @return startPageNumber
	 */
	public int getStartPageNumber(Pageable pageable) {
		int startPageNumber = pageable.getPageNumber() - (PAGEABLE_SIZE-1) <= 0 ?
				0 : (pageable.getPageNumber() / PAGEABLE_SIZE) * PAGEABLE_SIZE;
		return startPageNumber;
	}
	
	/**
	 * page range의 startPageNumber 반환
	 * @param pageNumber
	 * @param totalPage
	 * @return EndPageNumber
	 */
	public int getEndPageNumber(int pageNumber, int totalPage) {
		int EndPageNumber = ((((pageNumber) / PAGEABLE_SIZE) +1) * PAGEABLE_SIZE) -1;
		if(totalPage <= EndPageNumber) {
			EndPageNumber = totalPage-1;
		}
		return Math.max(0, EndPageNumber);
	}
	
}
