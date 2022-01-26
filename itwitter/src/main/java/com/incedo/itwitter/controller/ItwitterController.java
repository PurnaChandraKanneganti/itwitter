package com.incedo.itwitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.incedo.itwitter.entity.StatusPosts;
import com.incedo.itwitter.model.UserDetails;
import com.incedo.itwitter.services.ItwitterService;

@RestController
public class ItwitterController {
	
	@Autowired
	private ItwitterService itwitterService;

	@RequestMapping(value="/test",method = RequestMethod.GET)
	public String test() {
		return "Hello World";
	}
	
	@RequestMapping(value="/validateuser",method = RequestMethod.POST)
	public ResponseEntity<UserDetails> validateUser(@RequestBody UserDetails details) {
	return new ResponseEntity<UserDetails>(itwitterService.validateUser(details), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/signupuser",method = RequestMethod.POST)
	public ResponseEntity<UserDetails> signUpUser(@RequestBody UserDetails details) {
	return new ResponseEntity<UserDetails>(itwitterService.signUpUser(details), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/poststatus",method = RequestMethod.POST)
	public ResponseEntity<UserDetails> postStatus(@RequestBody UserDetails details) {
	return new ResponseEntity<UserDetails>(itwitterService.postStatus(details), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/getallstatus",method = RequestMethod.POST)
	public ResponseEntity<List<StatusPosts>> getAllStatus() {
	return new ResponseEntity<List<StatusPosts>>(itwitterService.getAllStatus(), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/likedpost",method = RequestMethod.POST)
	public ResponseEntity<StatusPosts> likePost(@RequestBody UserDetails details) {
	return new ResponseEntity<StatusPosts>(itwitterService.likePost(details), HttpStatus.OK);	
	}
	
	
}
