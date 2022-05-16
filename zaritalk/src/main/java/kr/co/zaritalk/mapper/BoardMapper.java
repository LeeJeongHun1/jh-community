package kr.co.zaritalk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.zaritalk.vo.Board;

@Mapper
public interface BoardMapper {
	
	String selectList();
}
