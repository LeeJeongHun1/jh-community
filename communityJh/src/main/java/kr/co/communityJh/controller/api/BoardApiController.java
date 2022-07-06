package kr.co.communityJh.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.communityJh.entity.Board;
import kr.co.communityJh.service.BoardService;

/**
 * @author "jhlee"
 * 커뮤니티 게시판 API 컨트롤러 
 * 게시글 목록, 조회, 수정, 삭제 RestAPI
 * 현재 thymeleaf에서 사용할일이 거의 없어서 추후 재작성 예정.
 */
@RestController
//@RequestMapping("/boards")
@Api(tags = "커뮤니티_API문서")
public class BoardApiController {

	@Autowired
	BoardService service;
	
	
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
	
//	@ApiOperation(notes = "커뮤니티 전체 게시글을 조회 할 수 있습니다.", value = "게시글 조회" )
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "seq", value = "id", example = "1")
//		
//	})
//	@PutMapping("/{seq}")
//	public int update(int seq) {
//		return 0;
//	}
//	@GetMapping()
//	public List<User> boards3() {
//		return service.selectList();
//	}
}
