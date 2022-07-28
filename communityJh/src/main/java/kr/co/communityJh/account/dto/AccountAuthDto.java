package kr.co.communityJh.account.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.co.communityJh.account.domain.Account;
import lombok.Getter;

/**
 * @author jhlee
 * 
 * Account Entity에 매핑되는 Login AcountDTO
 * 
 */
@Getter
public class AccountAuthDto extends User {
	
	private AccountRequestDto accountRequestDTO;
	
	public AccountAuthDto(Account account, Collection<GrantedAuthority> authorities) {
		super(account.getEmail(), account.getPassword(), authorities);
		this.accountRequestDTO = account.toAccountDTO();
	}


	
}
