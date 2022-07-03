package kr.co.communityJh.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@DynamicUpdate
@SequenceGenerator(
		 name = "BOARD_SEQ_GENERATOR",
		 sequenceName = "SEQ_BOARD", //매핑할 데이터베이스 시퀀스 이름
		 initialValue = 1, allocationSize = 1)
@Entity(name = "TB_BOARD")
public class Board extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
	private int id;
	
	@Column(nullable = false, length = 2000)
	@NotBlank(message = "제목을 입력하세요.")
	private String title;
	
	@Column(nullable = false)
	@NotBlank(message = "내용을 입력하세요.")
	private String body;
	
	//cascade = CascadeType.ALL 영속성 전의
	@ManyToOne(cascade = CascadeType.ALL) // default EAGER 
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToMany(mappedBy = "board")
	private List<Comment> comments;
	
	@ColumnDefault("0")
	private int viewCount;
	@ColumnDefault("0")
	private int likeCount;
}
