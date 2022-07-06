package kr.co.communityJh.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.communityJh.entity.Account;

@Mapper
public interface UserMapper {
	
	Account selectUser(Account user);
}
