package kr.co.communityJh.comment.repository;

import kr.co.communityJh.board.domain.Board;
import kr.co.communityJh.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author jhlee
 * Comment JpaRepository
 */
public interface CommentRepository extends JpaRepository<Comment, Long>, QuerydslPredicateExecutor<Board>{
	

}
