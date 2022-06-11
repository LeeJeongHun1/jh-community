package kr.co.communityJh.vo;

import lombok.Data;

@Data
public class User {

	// user seq
	private String id;
	// user nickname
	private String nickName;
	// user Type: Realtor, Lessor, Lessee ...
	private AccountType accountType;
	// user id
	private String accountId;
	// 탈퇴여부
	private String quit;
}
