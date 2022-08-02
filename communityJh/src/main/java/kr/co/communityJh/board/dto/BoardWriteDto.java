package kr.co.communityJh.board.dto;

import javax.validation.constraints.NotBlank;

import kr.co.communityJh.account.domain.Account;
import kr.co.communityJh.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author jhlee
 * 
 * Board Entity DTO
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardWriteDto {
	
	private Long id;
	
	@NotBlank(message = "제목을 입력하세요!")
	private String title;
	
	@NotBlank(message = "내용을 입력하세요!")
	private String body;

	private LocalDateTime createDate;
	private Account account;
	private int viewCount;
	private int likeCount;
	
	
	/**
	 * dto class를 table에 매핑된 entity로 변환
	 * @return boardEntity
	 */
	public Board toBoardEntity() {
		return Board.builder()
				.body(this.body)
				.title(this.title)
				.account(this.account)
				.viewCount(this.viewCount)
				.build();
	}

	/**
	 * Entity to Dto
	 * @param board
	 * @return
	 */
	public BoardWriteDto toBoardDto(Board board) {
		return BoardWriteDto.builder()
				.body(board.getBody())
				.title(board.getTitle())
				.account(board.getAccount())
				.viewCount(board.getViewCount())
				.build();
	}
}
