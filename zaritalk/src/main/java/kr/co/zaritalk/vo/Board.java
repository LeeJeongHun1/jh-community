package kr.co.zaritalk.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
	private String title;
	private String body;
	private String nickname;
	private Date createDate;
	private Date lastUpdateDate;
	private int viewCount;
	private int likeCount;
}
