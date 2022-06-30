package kr.co.communityJh.vo;

import java.time.LocalDateTime;
import java.util.List;

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
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
		 name = "BOARD_SEQ_GENERATOR",
		 sequenceName = "SEQ_BOARD", //매핑할 데이터베이스 시퀀스 이름
		 initialValue = 1, allocationSize = 1)
@Entity(name = "TB_BOARD")
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
	private int seq;
	
	@Column(nullable = false, length = 2000)
	@NotBlank(message = "제목을 입력하세요.")
	private String title;
	
	@Column(nullable = false)
	@NotBlank(message = "내용을 입력하세요.")
	private String body;
	
	@ManyToOne // default EAGER 
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToMany(mappedBy = "board")
	private List<Comment> comments;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdateDate;
	
	@ColumnDefault("0")
	private int viewCount;
	@ColumnDefault("0")
	private int likeCount;
}
