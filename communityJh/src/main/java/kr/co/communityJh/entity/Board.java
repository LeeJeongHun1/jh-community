package kr.co.communityJh.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import kr.co.communityJh.board.dto.BoardDto;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jhlee
 * 
 *PrePersist: 엔티티를 영속성 컨텍트스에 관리하기 직전
 *PostPersist: 엔티티를 데이터베이스에 저장한 직후
 *PreUpdate: 엔티티를 데이터베이스에 수정하기 직전
 *PostUpdate: 엔티티를 데티터베이스에 수정한 직후
 *PreRemove: 엔티티를 영속성 컨텍스트에서 삭제하기 직전
 *PostRemove: 엔티티를 데이터베이스에 삭제한 직후
 *PostLoad: 엔티티가 영속성 선텍스트에 조회된 직후 또는 refresh를 호출한 후
 *
 * EAGER 전략
 * OneToOne
 * ManyToOne
 * 
 * LAZY 전략
 * OneToMany
 * ManyToMany
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
		 name = "BOARD_SEQ_GENERATOR",
		 sequenceName = "SEQ_BOARD", //매핑할 데이터베이스 시퀀스 이름
		 initialValue = 1, allocationSize = 1)
@Entity(name = "TB_BOARD")
public class Board extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(columnDefinition="TEXT")
	private String body;
	
	//cascade 영속성 전의
	@ManyToOne(optional = false) // default EAGER 
	@JoinColumn(name = "userId")
	private Account account;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private Set<Comment> comments = new HashSet<>();
//	
//	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
//	private Set<Likes> likes = new HashSet<>();
	
	@ColumnDefault("0")
	private int viewCount;
	
	/**
	 * table에 매핑된 entity를 dto class로 변환
	 * 개발이 진행되는 상황에 따라 수정될 수 있음.
	 * @return boardDto
	 */
	public BoardDto toBoardDtd() {
		return BoardDto.builder()
				.id(this.id)
				.body(this.body)
				.title(this.title)
				.account(this.account)
				.createDate(this.getCreateDate())
				.viewCount(this.viewCount)
				.build();
	}
}
