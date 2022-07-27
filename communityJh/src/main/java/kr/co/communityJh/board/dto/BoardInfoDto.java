package kr.co.communityJh.board.dto;

import kr.co.communityJh.entity.Board;
import lombok.*;

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
public class BoardInfoDto {
	private Long id;
	private String title;
	private String body;
	private String accountNickname;
	private LocalDateTime createDate;
	private int viewCount;
	private Long commentCount;



	public BoardInfoDto toBoardDto(Board board) {
		return BoardInfoDto.builder()
				.id(board.getId())
				.body(board.getBody())
				.title(board.getTitle())
				.accountNickname(board.getAccount().getNickname())
				.createDate(board.getCreateDate())
				.viewCount(board.getViewCount())
				.build();
	}

}
