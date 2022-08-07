package kr.co.communityJh.account.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoleType {
	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_USER("ROLE_USER");

	private final String value;
}
