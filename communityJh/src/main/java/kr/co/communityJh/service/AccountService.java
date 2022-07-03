package kr.co.communityJh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.communityJh.repository.AccountRepository;
import kr.co.communityJh.repository.RoleRepository;
import kr.co.communityJh.vo.AccountType;
import kr.co.communityJh.vo.Role;
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
	
	@Autowired private AccountRepository userRepository;
	@Autowired private RoleRepository roleRepository;
	
	@Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public void registerUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		// 로직 추가 해야함. 
//		roleRepository.save(Role.builder()
//				.role(AccountType.ROLE_USER)
//				.user(user)
//				.build());
		user.getRoles().add(Role.builder()
				.role(AccountType.ROLE_USER)
				.user(user)
				.build());
		userRepository.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println(email);
		User user = userRepository.findByEmail(email);
		Collection<GrantedAuthority> authorities = new ArrayList<>();
//		user.getRoles().forEach(it -> {
//			System.out.println(it.toString());
//			authorities.add(new SimpleGrantedAuthority(it.toString()));
//		});
//		authorities.add(new GrantedAuthority() {
//			@Override
//			public String getAuthority() {
//				// 권한 check 로직 추가 가능.
//				return user.getRoles().ea;
//			}
//		});
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
	
	public void createRoles(Role role) {
		roleRepository.save(role);
	}
}
