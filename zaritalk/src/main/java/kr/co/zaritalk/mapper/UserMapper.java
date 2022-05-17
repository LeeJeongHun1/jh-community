package kr.co.zaritalk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.zaritalk.vo.User;

@Mapper
public interface UserMapper {
	
	List<User> selectList();
}
