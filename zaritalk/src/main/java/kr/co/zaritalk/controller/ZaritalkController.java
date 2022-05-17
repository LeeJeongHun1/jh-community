package kr.co.zaritalk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.zaritalk.service.UserService;
import kr.co.zaritalk.vo.User;

/**
 * @author "jhlee"
 *
 */
@RestController
@RequestMapping("/boards")
@Api(tags = "커뮤니티_API")
public class ZaritalkController {

	@Autowired
	UserService service;
	
	/**
	 * @return 게시글 목록
	 */
	@GetMapping()
	@ApiOperation(notes = "커뮤니티 전체 게시글을 조회 할 수 있습니다.", value = "목록조회")
	public List<User> boards() {
		return service.selectList();
	}
	
	@PostMapping("/{seq}")
	@ApiOperation(notes = "커뮤니티 전체 게시글을 조회 할 수 있습니다.", value = "유저조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "seq", value = "id", example = "1")
		
	})
	public List<User> boards1(@PathVariable int seq) {
		return service.selectList();
	}
//	@GetMapping()
//	public List<User> boards2() {
//		return service.selectList();
//	}
//	@GetMapping()
//	public List<User> boards3() {
//		return service.selectList();
//	}
}
