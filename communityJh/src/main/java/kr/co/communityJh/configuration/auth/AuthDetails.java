package kr.co.communityJh.configuration.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.co.communityJh.vo.User;

/**
 * @author jhlee
 * 스프링 시큐리티에서 설정한 login 프로세스가 실행되면 시큐리티 session을 만들어 준다. (Security ContextHolder)
 * authentication 타입 객체 안에 User 정보가 포함되야 한다.
 * User 객체 타입 -> UserDetails 
 * 
 * Security Session
 * 		-> Authentication
 * 			-> UserDetails
 */
public class AuthDetails implements UserDetails {

	private User user;
	
	public AuthDetails(User user) {
		this.user = user;
	}

	
	/**
	 * user 권한을 return
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				// 권한 check 로직 추가 가능.
				return user.getRole().toString();
			}
		});
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getId();
	}

	// 계정 만료 여부
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	// 계정 잠김 여부
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 계정 만료일 지났는지?
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 
	@Override
	public boolean isEnabled() {
		return true;
	}

}
