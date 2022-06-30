package kr.co.communityJh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.communityJh.vo.User;

/**
 * @author jhlee
 *
 * EAGER 전략
 * OneToOne
 * ManyToOne
 * 
 * LAZY 전략
 * OneToMany
 * ManyToMany
 */
public interface AccountRepository extends JpaRepository<User, Integer>{
	
	public User findById(String id);

}
