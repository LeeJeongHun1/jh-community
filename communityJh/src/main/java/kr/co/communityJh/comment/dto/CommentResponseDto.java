package kr.co.communityJh.comment.dto;

import kr.co.communityJh.comment.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class CommentResponseDto {
    private Long id;
    private String body;
    private String accountEmail;
    private String accountNickname;
    private LocalDateTime createDate;


    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .accountEmail(comment.getAccount().getEmail())
                .accountNickname(comment.getAccount().getNickname())
                .createDate(comment.getCreateDate())
                .build();
    }

}
