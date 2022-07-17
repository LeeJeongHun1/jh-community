package kr.co.communityJh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.communityJh.entity.Account;

/**
 * @author jhlee
 *
 * Account JpaRepository
 */
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	public Account findByEmail(String email);

}
