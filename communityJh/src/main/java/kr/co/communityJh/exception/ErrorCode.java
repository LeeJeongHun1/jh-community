/**
 * Created By jhlee on 2022. 7. 13.
 */
package kr.co.communityJh.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author jhlee
 *
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	INVALID_ID_PASSWORD(4001, "이메일 또는 비밀번호가 일치하지 않습니다."),
	USER_NOT_FOUND(4002, "존재하지 않는 이메일입니다."),
	NOT_FOUND(404, "존재하지 않는 페이지입니다."),
	BOARD_NOT_FOUND(404, "존재하지 않는 게시글입니다."),
	ACCOUNT_DISABLED_ERROR(4003, "계정이 비활성화 되었습니다."),
	CREDENTIALS_EXPIRED_ERROR(4004, "비밀번호 유효기간이 만료 되었습니다."),
	INTERNAL_ERROR(5001, "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
	ELSE_ERROR(5002, "알 수 없는 이유로 오류가 발생했습니다.");

	private final int code;
	private final String message;
}
