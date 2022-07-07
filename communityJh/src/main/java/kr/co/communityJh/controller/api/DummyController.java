package kr.co.communityJh.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.communityJh.entity.Account;
import kr.co.communityJh.entity.Board;
import kr.co.communityJh.service.AccountService;
import kr.co.communityJh.service.BoardService;

/**
 * @author "jhlee"
 * board, user에 관한 더미 데이터 관련 rest controller
 */
@RestController
@Api(tags = "더미 컨트롤러 문서")
public class DummyController {

	@Autowired
	BoardService boardService;
	
	@Autowired
	AccountService accountService;
	
	/**
	 * @return 게시글 목록
	 */
	@GetMapping("/board")
	@ApiOperation(notes = "커뮤니티 게시글 목록을 조회 할 수 있습니다.", value = "목록조회")
	public List<Board> boards(Board board) {
//		System.out.println(board);
//		service.save(board);
		return null;
	}
	
	@PostMapping("/board")
	@ApiOperation(notes = "게시글을 작성 할 수 있습니다.", value = "게시글 작성")
	public String boardSave(Board board) {
		boardService.save(board);
		return "게시글 추가 완료";
	}
	
	@PostMapping("/users")
	@ApiOperation(notes = "유저 등록", value = "유저 등록")
	public String userSave(Account user) {
		System.out.println(user);
//		accountService.registerUser(user);
//		ResponseEntity<Board> as = new ResponseEntity<Board>(new Board(), HttpStatus.OK);
		return "유저 추가 완료";
	}
	
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
