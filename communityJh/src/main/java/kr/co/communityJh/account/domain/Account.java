package kr.co.communityJh.account.domain;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Email;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import kr.co.communityJh.account.dto.AccountRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@Data
@Builder
@EqualsAndHashCode(callSuper = false, exclude = {"roles"})
@DynamicInsert // insert 시 null 컬럼 제외
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(
		 name = "ACCOUNTSEQ_GENERATOR",
		 sequenceName = "SEQ_ACCOUNT", //매핑할 데이터베이스 시퀀스 이름
		 initialValue = 1, allocationSize = 1)
@Entity(name = "TB_ACCOUNT")
public class Account {

	// user seq
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNTSEQ_GENERATOR")
	private Long id;
	
	// user email
	@Column(nullable = false)
	@Email
	private String email;
	
	// user nickname
	@Column(nullable = false)
	private String nickname;
	
	// user passwd
	@Column(nullable = false)
	private String password;

	// user role
//	@OneToMany(mappedBy = "account",
//			fetch = FetchType.EAGER,
//			cascade = CascadeType.ALL)
//	@JsonManagedReference
//	@ToStringExclude
//	private Set<Role> roles = new HashSet<>();
	@Column
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	// 탈퇴여부
	@ColumnDefault(value = "'Y'")
	private String isEnabledYn;
	
	@Column
	private String refreshToken;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createDate;

	public void addUserRoles(RoleType roleType) {
		this.role = roleType;

	}
	
	public AccountRequestDto toAccountDTO() {
		return AccountRequestDto.builder()
				.id(this.id)
				.email(this.email)
				.nickname(this.nickname)
				.password(this.password)
//				.roles(this.roles)
				.isEnabledYn(this.isEnabledYn)
				.build();
	}
	
	public void updateRefreshToken(String newToken) {
		this.refreshToken = newToken;
	}
	
}
