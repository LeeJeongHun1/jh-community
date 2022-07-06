package kr.co.communityJh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.communityJh.entity.Board;
import kr.co.communityJh.repository.BoardRepository;

@Service
public class BoardService {
	// 한번에 보여줄 페이지 수
	private static final int PAGEABLE_SIZE = 5;
	
	@Autowired
	private BoardRepository boardRepository;
	
	// Slice 객체로 return 할 경우 다음 slice 존재 유무만 알고 있다.
	public Page<Board> findAll(String searchText, int option, Pageable pageable) {
		if(option == 1) {
			return boardRepository.findByTitleContainingOrBodyContaining(searchText, searchText, pageable);
		}else if(option == 2) {
			return boardRepository.findAll(pageable);
		}
		return boardRepository.findAll(pageable);
	}
	
	public void save(Board board) {
		boardRepository.save(board);
	}
	
	
	/**
	 * Board.id를 통해 Board Table 단건 조회
	 * 해당 Board.viewCount 수정 // 더티체킹
	 * @param id
	 * @return Board
	 */
	@Transactional
	public Board findById(int id) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("<h1>해당 게시글은 존재하지 않습니다!</h1>");
		});
		board.setViewCount(board.getViewCount()+1);
		return board;
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
		if(totalPage < EndPageNumber) {
			EndPageNumber = totalPage-1;
		}
		return Math.max(0, EndPageNumber);
	}
}
