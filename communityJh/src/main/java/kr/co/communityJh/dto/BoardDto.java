package kr.co.communityJh.dto;

import kr.co.communityJh.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jhlee
 * 
 * Board Entity DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto{
	
	private int id;
	private String title;
	private String body;
	private Account account;
	private int viewCount;
	private int likeCount;
}
