package kr.co.communityJh.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.communityJh.auth.AuthUser;
import kr.co.communityJh.dto.account.AccountRequestDto;
import kr.co.communityJh.dto.board.BoardDto;
import kr.co.communityJh.service.BoardService;

/**
 * @author "jhlee"
 * 커뮤니티 게시판 API 컨트롤러 
 * 게시글 목록, 조회, 수정, 삭제 RestAPI
 * 현재 thymeleaf에서 사용할일이 거의 없어서 추후 재작성 예정.
 */
@RestController
@RequestMapping("/api/board")
@Api(tags = "커뮤니티_API문서")
public class BoardApiController {

	@Autowired BoardService boardService;
	
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
	 * @param board
	 * @return
	 */
	@ApiOperation(notes = "게시글 id에 해당하는 게시글을 삭제합니다.", value = "게시글 삭제" )
	@ApiImplicitParams({
		@ApiImplicitParam(name = "seq", value = "id", example = "1")
		
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> boardDelete(@PathVariable int id,
			@AuthUser AccountRequestDto accountRequestDTO) {
		boardService.deleteBoardById(id);
//		ResponseEntity.ok().header("", "").body(null);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	/**
	 * 게시글 삭제
	 * @param board
	 * @return
	 */
	@ApiOperation(notes = "게시글 id에 해당하는 게시글을 수정합니다.", value = "게시글 수정" )
	@ApiImplicitParams({
		@ApiImplicitParam(name = "seq", value = "id", example = "1"),
		@ApiImplicitParam(name = "sdeq", value = "id", example = "1")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Object> boardModify(@RequestBody BoardDto boardDTO) {
		BoardDto boardResponseDTO = boardService.updateBoardById(boardDTO);
		return ResponseEntity.ok(boardResponseDTO);
	}
}
