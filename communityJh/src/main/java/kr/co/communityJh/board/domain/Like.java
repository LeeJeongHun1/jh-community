package kr.co.communityJh.board.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.co.communityJh.account.domain.Account;
import kr.co.communityJh.board.domain.Board;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"board", "account"})
@SequenceGenerator(
		 name = "LIKE_SEQ_GENERATOR",
		 sequenceName = "SEQ_LIKE", //매핑할 데이터베이스 시퀀스 이름
		 initialValue = 1, allocationSize = 1)
@Entity(name = "TB_LIKE")
public class Like {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIKE_SEQ_GENERATOR")
	private Long id;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "userId")
	private Account account;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "boardId")
	private Board board;
	
//	@CreationTimestamp
//	private LocalDateTime createDate;
}
