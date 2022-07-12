package kr.co.communityJh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
		 name = "LIKES_SEQ_GENERATOR",
		 sequenceName = "SEQ_LIKES", //매핑할 데이터베이스 시퀀스 이름
		 initialValue = 1, allocationSize = 1)
//@Entity(name = "TB_LIKES")
public class Likes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIKES_SEQ_GENERATOR")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Account user;
	
	@ManyToOne
	@JoinColumn(name = "boardId")
	private Board board;
	
//	@CreationTimestamp
//	private LocalDateTime createDate;
}
