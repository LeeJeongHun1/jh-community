package kr.co.communityJh.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kr.co.communityJh.entity.Account;
import kr.co.communityJh.entity.Board;
import kr.co.communityJh.entity.Comment;
import kr.co.communityJh.entity.Likes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jhlee
 * 
 * Board Entity DTO
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto{
	
	private int id;
	private String title;
	private String body;
	private Account account;
	private List<Comment> comments = new ArrayList<>();
	private Set<Likes> likes = new HashSet<>();
	private int viewCount;
	private int likeCount;
	
	
	public Board toEntity() {
		return Board.builder()
				.body(this.body)
				.build();
	}
}
