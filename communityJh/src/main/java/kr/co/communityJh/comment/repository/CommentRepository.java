package kr.co.communityJh.comment.repository;

import kr.co.communityJh.entity.Board;
import kr.co.communityJh.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author jhlee
 * Comment JpaRepository
 */
public interface CommentRepository extends JpaRepository<Comment, Long>, QuerydslPredicateExecutor<Board>{
	

}
