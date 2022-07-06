package kr.co.communityJh.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.communityJh.dto.AccountDTO;
import kr.co.communityJh.entity.Account;
import kr.co.communityJh.entity.Role;
import kr.co.communityJh.enumType.AccountType;
import kr.co.communityJh.repository.AccountRepository;
import kr.co.communityJh.repository.RoleRepository;

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
	
	@Autowired private AccountRepository userRepository;
	@Autowired private RoleRepository roleRepository;
	
	@Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public void registerUser(Account user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		// 로직 추가 해야함. 
//		roleRepository.save(Role.builder()
//				.role(AccountType.ROLE_USER)
//				.user(user)
//				.build());
		user.addRoles(Role.builder()
				.role(AccountType.ROLE_USER)
				.user(user)
				.build());
		userRepository.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = userRepository.findByEmail(email);
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		account.getRoles().forEach(it -> {
			authorities.add(new SimpleGrantedAuthority(it.getRole().toString()));
		});
//		authorities.add(new GrantedAuthority() {
//			@Override
//			public String getAuthority() {
//				// 권한 check 로직 추가 가능.
//				return user.getRoles().ea;
//			}
//		});
//		return new User(user.getEmail(), user.getPassword(), authorities);
		return new AccountDTO(account, authorities);
	}
	
	public void createRoles(Role role) {
		roleRepository.save(role);
	}
}
