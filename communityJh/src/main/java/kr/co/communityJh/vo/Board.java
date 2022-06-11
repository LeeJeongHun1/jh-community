package kr.co.communityJh.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int seq;
	
	private String title;
	private String body;
	private String nickname;
	private Date createDate;
	private Date lastUpdateDate;
	private int viewCount;
	private int likeCount;
}
