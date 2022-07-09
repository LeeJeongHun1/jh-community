package kr.co.communityJh.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import kr.co.communityJh.enumType.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author jhlee
 */
@Data
@ToString(exclude = "account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
		 name = "ROLE_SEQ_GENERATOR",
		 sequenceName = "SEQ_ROLE", //매핑할 데이터베이스 시퀀스 이름
		 initialValue = 1, allocationSize = 1)
@Entity(name = "TB_ROLE")
public class Role {

	// role Id
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ_GENERATOR")
	private int Id;
	
	// role Type: ADMIN, USER ...
	@Enumerated(EnumType.STRING)
	private AccountType role;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonBackReference
	private Account account;
	
}
