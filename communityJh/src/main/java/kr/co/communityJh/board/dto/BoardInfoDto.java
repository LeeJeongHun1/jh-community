package kr.co.communityJh.board.dto;

import kr.co.communityJh.board.domain.Board;
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
	private String accountEmail;
	private LocalDateTime createDate;
	private int viewCount;
	private int likeCount;
	private int commentCount;



	public BoardInfoDto toBoardDto(Board board) {
		return BoardInfoDto.builder()
				.id(board.getId())
				.body(board.getBody())
				.title(board.getTitle())
				.accountNickname(board.getAccount().getNickname())
				.accountEmail(board.getAccount().getEmail())
				.createDate(board.getCreateDate())
				.viewCount(board.getViewCount())
				.build();
	}

}
