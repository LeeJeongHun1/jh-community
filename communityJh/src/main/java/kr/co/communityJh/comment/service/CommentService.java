package kr.co.communityJh.comment.service;

import kr.co.communityJh.account.dto.AccountRequestDto;
import kr.co.communityJh.account.repository.AccountQueryRepository;
import kr.co.communityJh.board.repository.BoardQueryRepository;
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
		return commentQueryRepository.findById(bno);
	}

}
