package kr.co.communityJh.board.dto;

import kr.co.communityJh.entity.Board;
import kr.co.communityJh.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
public class BoardWithCommentDto {
	private Long id;
	private String title;
	private String body;
	private String accountNickname;
	private LocalDateTime createDate;
	private int viewCount;
	private Set<Comment> commentList;



	public BoardWithCommentDto toBoardDto(Board board) {
		return BoardWithCommentDto.builder()
				.id(board.getId())
				.body(board.getBody())
				.title(board.getTitle())
				.accountNickname(board.getAccount().getNickname())
				.createDate(board.getCreateDate())
				.viewCount(board.getViewCount())
				.build();
	}

}
