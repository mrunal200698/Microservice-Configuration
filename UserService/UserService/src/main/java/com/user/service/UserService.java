package com.user.service;

import java.util.List;
import java.util.Optional;

import com.user.entities.User;
import com.user.repository.UserRepository;

public interface UserService {

	//create
	public User createUser(User user);
	//get all
	public List<User> getAllUser();
	//get by id
	public User getUser(String userId);
	
}
