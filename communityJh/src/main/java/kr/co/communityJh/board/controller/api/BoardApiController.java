package kr.co.communityJh.board.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.communityJh.account.dto.AccountRequestDto;
import kr.co.communityJh.auth.AuthUser;
import kr.co.communityJh.board.dto.LikeRequestDto;
import kr.co.communityJh.board.service.BoardService;
import kr.co.communityJh.exception.CustomException;
import kr.co.communityJh.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author "jhlee"
 * 커뮤니티 게시판 API 컨트롤러 
 * 게시글 목록, 조회, 수정, 삭제 RestAPI
 * 현재 thymeleaf에서 사용할일이 거의 없어서 추후 재작성 예정.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Slf4j
@Api(tags = "커뮤니티_API문서")
public class BoardApiController {

	private final BoardService boardService;

	/**
	 * 게시글 삭제
	 * @param id 삭제할 게시글 id
	 * @param accountRequestDTO
	 * @return
	 */
	@ApiOperation(notes = "게시글 id에 해당하는 게시글을 삭제합니다.", value = "게시글 삭제" )
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id", example = "1")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> boardDelete(@PathVariable Long id,
			@AuthUser AccountRequestDto accountRequestDTO) {

		if (accountRequestDTO == null) {
			throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
		}
		boardService.deleteBoardById(id, accountRequestDTO);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@ApiOperation(notes = "게시글 id에 해당하는 게시글의 좋아요 상태를 변경합니다.", value = "종아요 기능" )
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "id", example = "1"),
	})
	@PutMapping("/{boardId}/like")
	public ResponseEntity boardLike(@RequestBody LikeRequestDto likeRequestDto,
			@AuthUser AccountRequestDto accountRequestDTO) {

		if (accountRequestDTO == null) {
			throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
		}
		likeRequestDto.setAccountId(accountRequestDTO.getId());

		return ResponseEntity.ok(boardService.changeLike(likeRequestDto, accountRequestDTO));
	}
}
