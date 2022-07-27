/**
 * Created By jhlee on 2022. 7. 18.
 */
package kr.co.communityJh.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jhlee
 *
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {
	private final int errorCode;
	private final String errorMessage;
}
