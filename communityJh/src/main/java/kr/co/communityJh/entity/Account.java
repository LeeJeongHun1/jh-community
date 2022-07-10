package kr.co.communityJh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import kr.co.communityJh.dto.AccountRequestDTO;
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
@EqualsAndHashCode(callSuper = false, exclude = {"roles", "board"})
@DynamicInsert // insert 시 null 컬럼 제외
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
		 name = "USER_SEQ_GENERATOR",
		 sequenceName = "SEQ_USER", //매핑할 데이터베이스 시퀀스 이름
		 initialValue = 1, allocationSize = 1)
@Entity(name = "TB_USER")
public class Account {

	// user seq
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
	private int id;
	
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
	@OneToMany(mappedBy = "account",
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL)
	@JsonManagedReference
	@ToStringExclude
	private Set<Role> roles = new HashSet<>();
	
	// 탈퇴여부
	@ColumnDefault(value = "'Y'")
	private String isEnabledYn;
	
	
	public void addRoles(Role role) {
//		roles.add(role);
		role.setAccount(this);
	}
	
	public AccountRequestDTO toAccountDTO() {
		return AccountRequestDTO.builder()
				.id(this.id)
				.email(this.email)
				.nickname(this.nickname)
				.password(this.password)
				.roles(this.roles)
				.isEnabledYn(this.isEnabledYn)
				.build();
	}
	
}
