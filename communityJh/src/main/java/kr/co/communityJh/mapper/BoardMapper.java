package kr.co.communityJh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.communityJh.vo.Board;

@Mapper
public interface BoardMapper {
	
	List<Board> findAll();
	
	Board selectOne();
	
}
