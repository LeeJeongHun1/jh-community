package kr.co.communityJh.dto;

import kr.co.communityJh.dto.account.AccountRequestDto;
import kr.co.communityJh.enumType.AccountType;
import lombok.Builder;
import lombok.Data;

/**
 * @author jhlee
 */
@Data
@Builder
public class RoleDto {

	private int Id;
	private AccountType role;
	private AccountRequestDto accountDTO;
	
}
