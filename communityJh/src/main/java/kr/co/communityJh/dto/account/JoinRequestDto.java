package kr.co.communityJh.dto.account;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import kr.co.communityJh.entity.Account;
import kr.co.communityJh.entity.Role;
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
public class JoinRequestDto {
	
	private Long id;

	@NotBlank(message = "이메일을 입력하세요")
	@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "올바르지 않은 이메일 형식입니다.")
	private String email;
	
	@NotBlank(message = "닉네임을 입력하세요")
	@Pattern(regexp = "^[a-z\\d]{2,12}$", message = "닉네임은 소문자, 숫자 포함 2~12자리 입니다.")
	private String nickname;
	
	@NotBlank(message = "비밀번호를 입력하세요")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@!%*#?&])[A-Za-z\\d$@!%*#?&]{4,15}$",
			message = "비밀번호는 대소문자, 숫자, 특수문자 포함 4~15자리 입니다.")
	private String password;
	
	private Set<Role> roles = new HashSet<>();
	
	
	public Account toEntityAccount() {
		return Account.builder()
				.email(this.email)
				.nickname(this.nickname)
				.password(this.password)
				.roles(new HashSet<>())
				.build();
	}
	
	public void encodePassword(String password) {
		this.password = password;
	}
}
