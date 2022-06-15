package kr.co.communityJh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.communityJh.repository.UserRepository;
import kr.co.communityJh.vo.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void registerUser(User user) {
		userRepository.save(user);
	}
}
