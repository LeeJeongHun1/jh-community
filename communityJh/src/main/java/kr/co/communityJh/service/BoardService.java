package kr.co.communityJh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.co.communityJh.repository.BoardRepository;
import kr.co.communityJh.vo.Board;

@Service
public class BoardService {
	// 한번에 보여줄 페이지 수
	private static final int PAGEABLE_SIZE = 5;
	
	@Autowired
	private BoardRepository boardRepository;
	
	// Slice 객체로 return 할 경우 다음 slice 존재 유무만 알고 있다.
	public Page<Board> findAll(Pageable pageable) {
//		boardRepository.findAll(PageRequest.of(0, 5, Sort.Direction.DESC, "createDate"));
		return boardRepository.findAll(pageable);
	}
	
	public void save(Board board) {
		boardRepository.save(board);
	}
	
	public Optional<Board> findById(int id) {
		return boardRepository.findById(id);
	}
	
	
	// 0~4, 5~9, 10~14 -> 10
	public int getStartPageNumber(Pageable pageable) {
		int startPageNumber = pageable.getPageNumber() - (PAGEABLE_SIZE-1) <= 0 ?
				0 : (pageable.getPageNumber() / PAGEABLE_SIZE) * PAGEABLE_SIZE;
		return startPageNumber;
	}
	
	// 0~4, 5~9, 10~14 
	// total: 12, pageNumber: 9 -> 9
	public int getEndPageNumber(int pageNumber, int totalPage) {
		int EndPageNumber = (((pageNumber) / PAGEABLE_SIZE) +1) * PAGEABLE_SIZE;
		if(totalPage < EndPageNumber) {
			EndPageNumber = totalPage;
		}
		return EndPageNumber-1;
	}
}
