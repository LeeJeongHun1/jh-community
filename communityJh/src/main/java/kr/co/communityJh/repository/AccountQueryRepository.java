package kr.co.communityJh.repository;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.communityJh.entity.Account;
import kr.co.communityJh.entity.QAccount;
import kr.co.communityJh.entity.QRole;
import lombok.RequiredArgsConstructor;

/**
 * @author jhlee
 * Board JpaRepository
 */
@RequiredArgsConstructor
@Repository
public class AccountQueryRepository {
	
	private final JPAQueryFactory jpaQueryFactory;
;
	
	
	
	public Long save(Account account) {
		QAccount qAccount = QAccount.account;
		QRole qRole = QRole.role1;
//		qRole.role = 
		
//		qAccount.Roles = List.of(QRole.role1
//				.role(AccountType.ROLE_USER)
//				.user(account)
//				.build());
		return jpaQueryFactory.insert(qAccount)
				.values(account)
				.execute();
	}
	
}
