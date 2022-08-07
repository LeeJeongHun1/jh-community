package kr.co.communityJh.board.dto;

import kr.co.communityJh.account.domain.Account;
import kr.co.communityJh.board.domain.Board;
import kr.co.communityJh.board.domain.Like;
import kr.co.communityJh.comment.domain.Comment;
import kr.co.communityJh.comment.dto.CommentResponseDto;
import lombok.*;

/**
 * @author jhlee
 * 
 * Like Entity Request DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class LikeResponseDto {

	private Long id;
	private Long boardId;
	private String accountNickname;

	public static LikeResponseDto fromEntity(Like like) {
		return LikeResponseDto.builder()
				.id(like.getId())
				.boardId(like.getBoard().getId())
				.accountNickname(like.getAccount().getNickname())
				.build();
	}
}
