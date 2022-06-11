package kr.co.communityJh.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.communityJh.vo.User;

@Mapper
public interface UserMapper {
	
	User selectUser(User user);
}
