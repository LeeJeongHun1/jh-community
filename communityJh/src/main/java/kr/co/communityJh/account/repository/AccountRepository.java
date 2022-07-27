package kr.co.communityJh.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.communityJh.entity.Account;

/**
 * @author jhlee
 *
 * Account JpaRepository
 */
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
}
