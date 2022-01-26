package com.incedo.itwitter.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.incedo.itwitter.entity.Users;
import com.incedo.itwitter.model.UserDetails;
import com.incedo.itwitter.repository.StatusPostsRepository;
import com.incedo.itwitter.repository.UsersRepository;
import com.incedo.itwitter.services.ItwitterService;
import com.incedo.itwitter.entity.StatusPosts;

@Configuration
public class ItwitterServiceImpl implements ItwitterService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private StatusPostsRepository statusPostsRepository;
	
	@Override
	public UserDetails validateUser(UserDetails details) {
		
		Users findOneByUserNameAndPassword = usersRepository.findOneByUserNameAndPassword(details.getUserName(), details.getPassword());
		if(null != findOneByUserNameAndPassword && findOneByUserNameAndPassword.getUserName().equals(details.getUserName()) 
				&& findOneByUserNameAndPassword.getPassword().equals(details.getPassword())) {
			details.setIsExist(true);
			details.setStatus("User Exists");
			details.setUserId(findOneByUserNameAndPassword.getUserId());
			
		} else {
			details.setIsExist(false);
			details.setStatus("User does not Exists");
		}
		return details;
	}

	@Override
	public UserDetails signUpUser(UserDetails details) {
		Users users = new Users();
		if(null != details) {
			users.setCity(details.getCity());
			users.setFirstName(details.getFirstName());
			users.setLastName(details.getLastName());
			users.setPassword(details.getPassword());
			users.setPhone(details.getPhone());
			users.setUserName(details.getUserName());
			users = usersRepository.save(users);
		}
		details.setIsExist(true);
		details.setStatus("Saved Successfully");
		details.setUserId(users.getUserId());
		return details;
	}

	@Override
	public UserDetails postStatus(UserDetails details) {
		if(null != details && null != details.getUserId() && null != details.getStatus()) {
			StatusPosts statusPosts = new StatusPosts();
			statusPosts.setUserId(details.getUserId());
			statusPosts.setComments(details.getStatus());
			statusPosts.setUserName(details.getUserName());
			statusPostsRepository.save(statusPosts);
		}
		details.setStatus("Saved Successfully");
		return details;
	}

	@Override
	public List<StatusPosts> getAllStatus() {
		return statusPostsRepository.findAllByOrderByStatusIdDesc();
	}

	@Override
	public StatusPosts likePost(UserDetails details) {
		StatusPosts findOneByStatusId = null;
		if(null != details.getStatusId()) {
			findOneByStatusId = statusPostsRepository.findOneByStatusId(details.getStatusId());
			findOneByStatusId.setLikes(null == findOneByStatusId.getLikes() ? 1 : findOneByStatusId.getLikes()+1);
			statusPostsRepository.save(findOneByStatusId);
		}
		return findOneByStatusId;
	}

}
