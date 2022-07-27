package kr.co.communityJh.comment.service;

import kr.co.communityJh.account.dto.AccountRequestDto;
import kr.co.communityJh.account.repository.AccountQueryRepository;
import kr.co.communityJh.board.repository.BoardQueryRepository;
import kr.co.communityJh.comment.dto.CommentRequestDto;
import kr.co.communityJh.comment.repository.CommentRepository;
import kr.co.communityJh.entity.Account;
import kr.co.communityJh.entity.Board;
import kr.co.communityJh.exception.CustomException;
import kr.co.communityJh.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jhlee
 * comment service
 */
@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
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

		commentRepository.save(commentRequestDto.toEntity(commentRequestDto, board, account));
		return null;
	}

}
