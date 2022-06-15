package kr.co.communityJh.vo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.DynamicInsert;

import lombok.Data;

@Data
@DynamicInsert
@Entity(name = "TB_USER")
@SequenceGenerator(
		 name = "USER_SEQ_GENERATOR",
		 sequenceName = "SEQ_USER", //매핑할 데이터베이스 시퀀스 이름
		 initialValue = 1, allocationSize = 1)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
	// user seq
	private int seq;
	
	// user nickname
	private String nickname;
	// user Type: ADMIN, USER ...
	@Enumerated(EnumType.STRING)
	private AccountType role;
	// user id
	private String id;
	// user passwd
	private String password;
	// 탈퇴여부
	private String quit;
}
