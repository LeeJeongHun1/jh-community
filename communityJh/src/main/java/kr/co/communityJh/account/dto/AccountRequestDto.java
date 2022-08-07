package kr.co.communityJh.account.dto;

import java.util.HashSet;
import java.util.Set;

import kr.co.communityJh.account.domain.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jhlee
 * account request dto
 */
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AccountRequestDto {
	
	private Long id;
	private String email;
	private String nickname;
	private String password;
//	private Set<Role> roles = new HashSet<>();
	private String isEnabledYn;
	
	public Account toEntityAccount() {
		return Account.builder()
				.id(this.id)
				.email(this.email)
				.nickname(this.nickname)
				.isEnabledYn(this.isEnabledYn)
				.build();
	}
}
