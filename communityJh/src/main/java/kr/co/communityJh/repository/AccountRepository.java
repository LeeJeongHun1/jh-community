package kr.co.communityJh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.communityJh.vo.User;

/**
 * @author jhlee
 *
 */
public interface AccountRepository extends JpaRepository<User, Integer>{
	
	public User findByEmail(String email);

}
