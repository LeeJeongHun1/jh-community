package kr.co.communityJh.dto;

import kr.co.communityJh.enumType.AccountType;
import lombok.Builder;
import lombok.Data;

/**
 * @author jhlee
 */
@Data
@Builder
public class RoleDTO {

	private int Id;
	private AccountType role;
	private AccountDTO accountDTO;
	
}
