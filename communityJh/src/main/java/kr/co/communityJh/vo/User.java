package kr.co.communityJh.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	private int seq;
	
	// user nickname
	@Column(nullable = false)
	private String nickname;
	// user Type: ADMIN, USER ...
	@Enumerated(EnumType.STRING)
	private AccountType role;
	// user id
	@Column(nullable = false)
	private String id;
	// user passwd
	@Column(nullable = false)
	private String password;
	// 탈퇴여부
	@ColumnDefault(value = "'N'")
	private String quit;
	
}
