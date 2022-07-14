package kr.co.communityJh.dto.board;

import javax.validation.constraints.NotBlank;

import kr.co.communityJh.entity.Account;
import kr.co.communityJh.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class BoardDto{
	
	private int id;
	
	@NotBlank(message = "제목을 입력하세요!")
	private String title;
	
	@NotBlank(message = "내용을 입력하세요!")
	private String body;
	
	private Account account;
	private int viewCount;
	private int likeCount;
	
	
	/**
	 * dto class를 table에 매핑된 entity로 변환
	 * @param dto
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
}
