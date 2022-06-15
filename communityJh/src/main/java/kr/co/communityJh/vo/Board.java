package kr.co.communityJh.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity(name = "TB_BOARD")
@SequenceGenerator(
		 name = "BOARD_SEQ_GENERATOR",
		 sequenceName = "SEQ_BOARD", //매핑할 데이터베이스 시퀀스 이름
		 initialValue = 1, allocationSize = 1)
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
	private int seq;
	
	private String title;
	private String body;
	private String nickname;
	private Date createDate;
	private Date lastUpdateDate;
	private int viewCount;
	private int likeCount;
}
