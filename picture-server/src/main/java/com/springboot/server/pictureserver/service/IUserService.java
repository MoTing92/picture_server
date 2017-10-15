package com.springboot.server.pictureserver.service;

import java.util.List;

import com.springboot.server.pictureserver.model.User;


public interface IUserService {

	List<User> query(User user);
	
	int add(User user);

	int delete(int ... userId);
	
	int update(User user);

	User findUserByUserName(String username);

	List<String> queryMenusByUid(Integer userId);

	List<String> queryMenusByUsername(String username);
	
	
}
