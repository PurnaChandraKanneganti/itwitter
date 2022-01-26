package com.incedo.itwitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incedo.itwitter.entity.StatusPosts;

public interface StatusPostsRepository extends JpaRepository<StatusPosts, Integer> {
	
	List<StatusPosts> findAllByUserId(Integer userId);
	List<StatusPosts> findAllByOrderByStatusIdDesc();
	StatusPosts findOneByStatusId(Integer status);

}
