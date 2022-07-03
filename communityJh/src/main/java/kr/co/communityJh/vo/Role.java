package kr.co.communityJh.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jhlee
 */
@Data
@Builder
@DynamicInsert
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
	@Column(nullable = false)
	private AccountType role;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	
//	@OneToMany(mappedBy = "role")
//	private List<UserRole> userRole = new ArrayList<>();

	
}
