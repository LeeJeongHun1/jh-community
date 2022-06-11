package kr.co.communityJh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.communityJh.vo.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}
