package kr.co.communityJh.repository;

import static kr.co.communityJh.entity.QAccount.account;
import static kr.co.communityJh.entity.QRole.role1;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.communityJh.entity.Account;
import kr.co.communityJh.entity.QAccount;
import lombok.RequiredArgsConstructor;

/**
 * @author jhlee
 * account querydsl Repository
 */
@RequiredArgsConstructor
@Repository
public class AccountQueryRepository {
	
	private final JPAQueryFactory jpaQueryFactory;
	
	public Long save(Account account) {
		QAccount qAccount = QAccount.account;
//		qRole.role = 
		
//		qAccount.Roles = List.of(QRole.role1
//				.role(AccountType.ROLE_USER)
//				.user(account)
//				.build());
		return jpaQueryFactory.insert(qAccount)
				.values(account)
				.execute();
	}
	
	public Optional<Account> findByEmail(String email) {
		return Optional.ofNullable(
				jpaQueryFactory.select(account)
					.from(account)
					.innerJoin(account.roles, role1).fetchJoin()
					.where(account.email.eq(email))
					.fetchOne());
	}
}
