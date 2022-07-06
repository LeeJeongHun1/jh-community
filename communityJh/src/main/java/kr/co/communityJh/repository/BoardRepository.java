package kr.co.communityJh.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.communityJh.entity.Board;

/**
 * @author jhlee
 * Board JpaRepository
 */
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	Page<Board> findByTitleContainingOrBodyContaining(String title, String body, Pageable pageable);
//	Page<Board> findByNicknameContaining(String nickName, Pageable pageable);
}
