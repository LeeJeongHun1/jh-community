package kr.co.communityJh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.communityJh.mapper.UserMapper;
import kr.co.communityJh.vo.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	public User selectUser(User user) {
		return mapper.selectUser(user);
	}
}
