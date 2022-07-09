package kr.co.communityJh.dto;

import java.util.HashSet;
import java.util.Set;

import kr.co.communityJh.entity.Account;
import kr.co.communityJh.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author jhlee
 * EAGER 전략
 * OneToOne
 * ManyToOne
 * 
 * LAZY 전략
 * OneToMany
 * ManyToMany
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {

	private int id;
	private String email;
	private String nickname;
	private String password;
	private Set<Role> roles = new HashSet<>();
	private String isEnabledYn;
	
	
	public Account toEntityAccount() {
		return Account.builder()
				.id(this.id)
				.email(this.email)
				.nickname(this.nickname)
				.roles(this.roles)
				.isEnabledYn(this.isEnabledYn)
				.build();
	}
}
