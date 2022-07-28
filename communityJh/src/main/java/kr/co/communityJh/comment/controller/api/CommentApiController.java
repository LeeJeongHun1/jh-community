package kr.co.communityJh.comment.controller.api;

import kr.co.communityJh.account.dto.AccountRequestDto;
import kr.co.communityJh.auth.AuthUser;
import kr.co.communityJh.comment.dto.CommentRequestDto;
import kr.co.communityJh.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentApiController {

    private final CommentService commentService;
    @RequestMapping("/{id}")
    public ResponseEntity addComment(@PathVariable Long id,
                            HttpServletRequest httpServletRequest,
                            @RequestBody CommentRequestDto commentRequestDto,
                            @AuthUser AccountRequestDto accountRequestDto){
        httpServletRequest.getRequestURI();
        commentService.commentAdd(id, accountRequestDto, commentRequestDto);
        return ResponseEntity.ok(commentService.readAll(id));
    }

}
