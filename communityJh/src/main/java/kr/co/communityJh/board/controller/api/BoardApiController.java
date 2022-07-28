package kr.co.communityJh.board.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.communityJh.auth.AuthUser;
import kr.co.communityJh.account.dto.AccountRequestDto;
import kr.co.communityJh.board.service.BoardService;

/**
 * @author "jhlee"
 * 커뮤니티 게시판 API 컨트롤러 
 * 게시글 목록, 조회, 수정, 삭제 RestAPI
 * 현재 thymeleaf에서 사용할일이 거의 없어서 추후 재작성 예정.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Api(tags = "커뮤니티_API문서")
public class BoardApiController {

	private final BoardService boardService;
	
//	/**
//	 * @return 게시글 목록
//	 */
//	@PostMapping("/board")
//	@ApiOperation(notes = "커뮤니티 게시글 목록을 조회 할 수 있습니다.", value = "목록조회")
//	public List<Board> boards(Board board) {
//		System.out.println(board);
//		service.save(board);
//		return null;
//	}
	
//	@GetMapping("/{seq}")
//	@ApiOperation(notes = "커뮤니티 게시글을 조회 할 수 있습니다.", value = "게시글 조회")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "seq", value = "id", example = "1")
//		
//	})
//	public Board boards(@PathVariable int seq) {
//		return service.selectList();
//	}

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

		boardService.deleteBoardById(id, accountRequestDTO);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
}
