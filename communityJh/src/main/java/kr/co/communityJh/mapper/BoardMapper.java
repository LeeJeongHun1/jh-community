package kr.co.communityJh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.communityJh.entity.Board;

@Mapper
public interface BoardMapper {
	
	List<Board> findAll();
	
	Board selectOne();
	
}
