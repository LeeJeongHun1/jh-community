package kr.co.communityJh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.communityJh.vo.Board;
import kr.co.communityJh.vo.User;

public interface AccountRepository extends JpaRepository<User, Integer>{
	
	public User findById(String id);

}
