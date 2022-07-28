package kr.co.communityJh.board.dto;

import kr.co.communityJh.comment.dto.CommentResponseDto;
import kr.co.communityJh.board.domain.Board;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
	private String accountEmail;
	private LocalDateTime createDate;
	private int viewCount;
	private List<CommentResponseDto> commentList;


	@Builder
	public BoardWithCommentDto(Board board) {
		this.id = board.getId();
		this.title = board.getTitle();
		this.body = board.getBody();
		this.accountNickname = board.getAccount().getNickname();
		this.accountEmail = board.getAccount().getEmail();
		this.createDate = board.getCreateDate();
		this.commentList = board.getComments().stream().map(CommentResponseDto::fromEntity).collect(Collectors.toList());
		this.viewCount = board.getViewCount();
	}
}
