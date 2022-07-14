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

import kr.co.communityJh.dto.account.AccountAuthDto;
import kr.co.communityJh.dto.account.AccountRequestDto;
import kr.co.communityJh.dto.account.JoinRequestDto;
import kr.co.communityJh.entity.Account;
import kr.co.communityJh.entity.Role;
import kr.co.communityJh.enumType.AccountType;
import kr.co.communityJh.repository.AccountQueryRepository;
import kr.co.communityJh.repository.AccountRepository;

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
	
	@Autowired private AccountRepository accountRepository;
	@Autowired private AccountQueryRepository accountQueryRepository;
	
	@Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public void join(JoinRequestDto joinRequestDto) {
		// dto password를 bCrypt 암호화 적용
		joinRequestDto.encodePassword(bCryptPasswordEncoder.encode(joinRequestDto.getPassword()));
		Account account = joinRequestDto.toEntityAccount();
		// 신규 사용자 role 추가
		account.addRoles(Role
				.builder()
				.role(AccountType.ROLE_USER)
				.build());
		accountRepository.save(account);
	}
	
	
	/**
	 * 해당 email의 존재 여부 확인
	 * @param email
	 * @return
	 */
	public boolean existByEmail(String email) {
		return accountQueryRepository.existsByEmail(email);
	}
	
	/**
	 * 해당 nickname의 존재 여부 확인
	 * @param nickname
	 * @return
	 */
	public boolean existByNickname(String nickname) {
		return accountQueryRepository.existsByNickname(nickname);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		accountQueryRepository.findByEmail(email).get();
		Account account = accountQueryRepository.findByEmail(email).orElseThrow(() -> {
			return new IllegalArgumentException("<h1>해당 사용자는 존재하지 않습니다!</h1>");
		});
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		account.getRoles().forEach(it -> {
				authorities.add(new SimpleGrantedAuthority(it.getRole().toString()));
		});
		return new AccountAuthDto(account, authorities);
	}
	
}
