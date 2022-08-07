package kr.co.communityJh.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import kr.co.communityJh.board.domain.Board;

/**
 * @author jhlee
 * Board JpaRepository
 */
public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board>{

}
