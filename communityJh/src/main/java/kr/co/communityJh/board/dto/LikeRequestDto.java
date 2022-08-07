package kr.co.communityJh.board.dto;

import kr.co.communityJh.account.domain.Account;
import kr.co.communityJh.board.domain.Board;
import kr.co.communityJh.board.domain.Like;
import lombok.*;

/**
 * @author jhlee
 * 
 * Like Entity Request DTO
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequestDto {
	private Long boardId;
	private boolean like;
	private Long accountId;

	public Like toEntity() {
		return Like.builder()
				.board(Board.builder()
						.id(this.boardId)
						.build())
				.account(Account.builder()
						.id(this.accountId)
						.build())
				.build();
	}
}
