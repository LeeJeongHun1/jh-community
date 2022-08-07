package kr.co.communityJh.comment.controller.api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.communityJh.account.dto.AccountRequestDto;
import kr.co.communityJh.auth.AuthUser;
import kr.co.communityJh.comment.dto.CommentRequestDto;
import kr.co.communityJh.comment.service.CommentService;
import kr.co.communityJh.exception.CustomException;
import kr.co.communityJh.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/comment")
public class CommentApiController {

    private final CommentService commentService;

    /**
     * 해당 게시글에 신규 댓글 등록
     * @param boardId 댓글이 등록될 게시글 id
     * @param commentRequestDto 댓글 dto
     * @param accountRequestDto 유저 dto
     * @return
     */
    @PostMapping("/{boardId}")
    public ResponseEntity addComment(@PathVariable Long boardId,
                            @RequestBody CommentRequestDto commentRequestDto,
                            @AuthUser AccountRequestDto accountRequestDto){
//        System.out.println(httpServletRequest.getRequestURI());

        if (accountRequestDto == null) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        commentService.commentAdd(boardId, accountRequestDto, commentRequestDto);
        return ResponseEntity.ok(commentService.readAll(boardId));
    }

    /**
     * 로그인 세션이 존재할 경우
     * 해당 댓글을 삭제
     * @param commentId 댓글 id
     * @param accountRequestDto 유저 dto
     * @return
     */
    @DeleteMapping("/{commentId}")
    @ApiOperation(notes = "해당 댓글을 삭제합니다.", value = "댓글 삭제" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentId", value = "commentId", example = "1")
    })
    public ResponseEntity removeComment(@PathVariable Long commentId,
                                        @AuthUser AccountRequestDto accountRequestDto) {
        if (accountRequestDto == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
        }
        commentService.remove(commentId);
        return ResponseEntity.ok(1);
    }

    /**
     * 로그인 세션이 존재할 경우 댓글 수정
     * @param commentId 댓글 id
     * @param commentRequestDto 댓글 dto
     * @param accountRequestDto 유저 dto
     * @return
     */
    @PutMapping("/{commentId}")
    public ResponseEntity modifyComment(@PathVariable Long commentId,
                                        @RequestBody CommentRequestDto commentRequestDto,
                                        @AuthUser AccountRequestDto accountRequestDto) {
        if (accountRequestDto == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
        }
        return ResponseEntity.ok(commentService.modify(commentId, commentRequestDto));
    }
}
