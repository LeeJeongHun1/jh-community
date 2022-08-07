package kr.co.communityJh.board.repository;

import kr.co.communityJh.board.domain.Board;
import kr.co.communityJh.board.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author jhlee
 * Board JpaRepository
 */
public interface LikeRepository extends JpaRepository<Like, Long>{

}
