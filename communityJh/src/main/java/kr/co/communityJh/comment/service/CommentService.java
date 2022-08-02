package kr.co.communityJh.comment.service;

import kr.co.communityJh.account.dto.AccountRequestDto;
import kr.co.communityJh.account.repository.AccountQueryRepository;
import kr.co.communityJh.board.repository.BoardQueryRepository;
import kr.co.communityJh.comment.domain.Comment;
import kr.co.communityJh.comment.dto.CommentRequestDto;
import kr.co.communityJh.comment.dto.CommentResponseDto;
import kr.co.communityJh.comment.repository.CommentQueryRepository;
import kr.co.communityJh.comment.repository.CommentRepository;
import kr.co.communityJh.account.domain.Account;
import kr.co.communityJh.board.domain.Board;
import kr.co.communityJh.exception.CustomException;
import kr.co.communityJh.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author jhlee
 * comment service
 */
@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final CommentQueryRepository commentQueryRepository;
	private final AccountQueryRepository accountQueryRepository;
	private final BoardQueryRepository boardQueryRepository;

	@Transactional
	public Long commentAdd(Long id, AccountRequestDto accountRequestDto,
							   CommentRequestDto commentRequestDto) {
		Board board = boardQueryRepository.findEntityById(id).orElseThrow(() -> {
			return new CustomException(ErrorCode.BOARD_NOT_FOUND);
		});
		Account account = accountQueryRepository.findByEmail(accountRequestDto.getEmail()).orElseThrow(() -> {
			return new CustomException(ErrorCode.USER_NOT_FOUND);
		});

		if(commentRepository.save(commentRequestDto.toEntity(commentRequestDto, board, account)) != null){
			return 1L;
		}
		return 0L;
	}

	@Transactional(readOnly = true)
	public List<CommentResponseDto> readAll(Long bno){
		List<CommentResponseDto> list = commentQueryRepository.findById(bno).orElseThrow(() -> {
			return new CustomException(ErrorCode.BOARD_NOT_FOUND);
		});
		return list;
	}

	@Transactional
	public Long remove(Long commentId){
		commentRepository.deleteById(commentId);
		return 0L;
	}

	@Transactional
	public Long modify(Long commentId, CommentRequestDto commentRequestDto){
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> {
			return new CustomException(ErrorCode.UNAUTHORIZED_USER);
		});
		comment.edit(commentRequestDto.getBody());
		return 1L;
	}

}
