package kr.co.communityJh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.communityJh.entity.Account;
import kr.co.communityJh.entity.Likes;

/**
 * @author jhlee
 *
 * Likes JpaRepository
 */
public interface LikesRepository extends JpaRepository<Likes, Integer>{
	
}
