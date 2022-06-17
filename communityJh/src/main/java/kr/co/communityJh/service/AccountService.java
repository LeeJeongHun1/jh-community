package kr.co.communityJh.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.communityJh.repository.AccountRepository;
import kr.co.communityJh.vo.AccountType;
import kr.co.communityJh.vo.User;

/**
 * @author jhlee
 * 스프링 시큐리티에서 설정한 login 프로세스가 실행되면 시큐리티 session을 만들어 준다. (Security ContextHolder)
 * authentication 타입 객체 안에 User 정보가 포함되야 한다.
 * User 객체 타입 -> UserDetails 
 * UserDetailsService interface 구현하여 UserDetails 생성
 * Security Session
 * 		-> Authentication
 * 			-> UserDetails
 */
@Service
public class AccountService implements UserDetailsService{
	
	@Autowired
	private AccountRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void registerUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole(AccountType.ROLE_USER); // 로직 추가 해야함.
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		User user = userRepository.findById(id);
		UserDetails userDetails = new UserDetails() {
			
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
			public boolean isEnabled() {
				return true;
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}
			
			@Override
			public boolean isAccountNonLocked() {
				return true;
			}
			
			@Override
			public boolean isAccountNonExpired() {
				return true;
			}
			
			@Override
			public String getUsername() {
				return user.getId();
			}
			
			@Override
			public String getPassword() {
				return user.getPassword();
			}
		};
		
		return userDetails;
	}
}
