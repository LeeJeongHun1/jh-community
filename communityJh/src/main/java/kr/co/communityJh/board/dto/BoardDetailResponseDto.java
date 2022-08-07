package kr.co.communityJh.board.dto;

import kr.co.communityJh.account.dto.AccountRequestDto;
import kr.co.communityJh.comment.dto.CommentResponseDto;
import kr.co.communityJh.board.domain.Board;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
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
public class BoardDetailResponseDto {
	private Long id;
	private String title;
	private String body;
	private String accountNickname;
	private String accountEmail;
	private LocalDateTime createDate;
	private int viewCount;
	private boolean userLike;

	private List<LikeResponseDto> likeList;
	private List<CommentResponseDto> commentList;


	public void sortCommentCollection() {
		this.commentList = this.commentList.stream()
				.distinct() // 중복제거
				.sorted(Comparator.comparing(CommentResponseDto::getCreateDate).reversed()) // 정렬
				.collect(Collectors.toList());
	}

	public void distinctLikeList() {
		this.likeList = this.likeList.stream()
				.distinct() // 중복제거
				.collect(Collectors.toList());
	}

	public void findUserLike(AccountRequestDto accountRequestDto) {
		long count = this.likeList.stream().filter(l -> l.getAccountNickname().equals(accountRequestDto.getNickname())).count();
		this.userLike = count == 1 ? true : false;
	}


}
