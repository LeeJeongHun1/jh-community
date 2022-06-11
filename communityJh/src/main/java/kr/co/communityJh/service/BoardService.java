package kr.co.communityJh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.communityJh.mapper.BoardMapper;
import kr.co.communityJh.vo.Board;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	public List<Board> findAll() {
		return mapper.findAll();
	}
}
