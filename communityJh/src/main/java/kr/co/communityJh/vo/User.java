package kr.co.communityJh.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
		 name = "USER_SEQ_GENERATOR",
		 sequenceName = "SEQ_USER", //매핑할 데이터베이스 시퀀스 이름
		 initialValue = 1, allocationSize = 1)
@Entity(name = "TB_USER")
public class User {

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
	@OneToMany(mappedBy = "user",
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL)
	private List<Role> Roles = new ArrayList<>();
	
	// 탈퇴여부
	@ColumnDefault(value = "'Y'")
	private String isEnabledYn;
	
//	@JoinTable(
//	name = "tb_user_role", //table name
//	joinColumns = @JoinColumn(name = "user_id"), // join column 명 
//	inverseJoinColumns = @JoinColumn(name = "role_id") // join 대상 table의 join column 명
//	)
}
