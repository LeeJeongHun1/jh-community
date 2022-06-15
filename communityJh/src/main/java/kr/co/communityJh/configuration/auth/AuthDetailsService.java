package kr.co.communityJh.configuration.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.communityJh.repository.UserRepository;
import kr.co.communityJh.vo.User;

/**
 * @author jhlee
 * 스프링 시큐리티에서 설정한 login 프로세스가 실행되면 시큐리티 session을 만들어 준다. (Security ContextHolder)
 */
@Service
public class AuthDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		User user = userRepository.findById(id);
		if(user != null) {
			return new AuthDetails(user);
		}else {
			return null;
		}
	}


}
