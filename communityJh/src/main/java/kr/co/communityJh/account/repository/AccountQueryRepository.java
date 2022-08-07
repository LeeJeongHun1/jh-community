package kr.co.communityJh.account.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.communityJh.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static kr.co.communityJh.account.domain.QAccount.account;

/**
 * @author jhlee
 * account querydsl Repository
 */
@RequiredArgsConstructor
@Repository
public class AccountQueryRepository {
	
	private final JPAQueryFactory jpaQueryFactory;
	
	public Optional<Account> findByEmail(String email) {
		return Optional.ofNullable(
				jpaQueryFactory.select(account)
					.from(account)
					.where(account.email.eq(email))
					.fetchOne());
	}
	
	public boolean existsByEmail(String email) {
		Integer exist = jpaQueryFactory.selectOne()
				.from(account)
				.where(eqEamil(email))
				.fetchFirst();
		return exist != null;
	}
	
	public boolean existsByNickname(String nickname) {
		Integer exist = jpaQueryFactory.selectOne()
				.from(account)
				.where(eqNickname(nickname))
				.fetchFirst();
		return exist != null;
	}
	
	
	private BooleanExpression eqEamil(String email) {
		if (StringUtils.isEmpty(email)) {
			return null;
		}
		return account.email.eq(email);
	}
	
	private BooleanExpression eqNickname(String nickname) {
		if (StringUtils.isEmpty(nickname)) {
			return null;
		}
		return account.nickname.eq(nickname);
	}
}
