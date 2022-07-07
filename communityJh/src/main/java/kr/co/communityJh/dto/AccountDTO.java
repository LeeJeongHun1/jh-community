package kr.co.communityJh.dto;

import java.util.ArrayList;
import java.util.List;

import kr.co.communityJh.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author jhlee
 * EAGER 전략
 * OneToOne
 * ManyToOne
 * 
 * LAZY 전략
 * OneToMany
 * ManyToMany
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

	private int id;
	private String email;
	private String nickname;
	private String password;
	private List<Role> Roles = new ArrayList<>();
}
