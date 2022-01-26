package com.incedo.itwitter.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.incedo.itwitter.entity.StatusPosts;
import com.incedo.itwitter.model.UserDetails;

@Service
public interface ItwitterService {

	public UserDetails validateUser(UserDetails details);
	
	public UserDetails signUpUser(UserDetails details);
	
	public UserDetails  postStatus(UserDetails details);
	
	public List<StatusPosts> getAllStatus();
	
	public StatusPosts likePost(UserDetails details);
}
