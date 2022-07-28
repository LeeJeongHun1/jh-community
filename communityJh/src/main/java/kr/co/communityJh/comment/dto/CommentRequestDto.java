package kr.co.communityJh.comment.dto;

import kr.co.communityJh.account.domain.Account;
import kr.co.communityJh.board.domain.Board;
import kr.co.communityJh.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {
    private String body;


    public Comment toEntity(CommentRequestDto commentRequestDto, Board board,
                            Account account) {
        return Comment.builder()
                .body(commentRequestDto.getBody())
                .board(board)
                .account(account)
                .build();
    }

}
