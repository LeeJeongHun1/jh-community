package kr.co.communityJh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.co.communityJh.mapper.BoardMapper;
import kr.co.communityJh.repository.BoardRepository;
import kr.co.communityJh.vo.Board;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	public List<Board> findAll() {
		return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
	}
	
	public void save(Board board) {
		boardRepository.save(board);
	}
}
