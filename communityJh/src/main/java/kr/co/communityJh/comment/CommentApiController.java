package kr.co.communityJh.comment;

import kr.co.communityJh.account.dto.AccountRequestDto;
import kr.co.communityJh.auth.AuthUser;
import kr.co.communityJh.board.dto.BoardPageWithSearchDto;
import kr.co.communityJh.comment.dto.CommentRequestDto;
import kr.co.communityJh.comment.repository.CommentRepository;
import kr.co.communityJh.comment.service.CommentService;
import kr.co.communityJh.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentApiController {

    private final CommentService commentService;
    @RequestMapping("/{id}")
    public ResponseEntity a(@PathVariable Long id,
                            @RequestBody CommentRequestDto commentRequestDto,
                            @AuthUser AccountRequestDto accountRequestDto){
        return ResponseEntity.ok(commentService.commentAdd(id, accountRequestDto, commentRequestDto));
    }

}
