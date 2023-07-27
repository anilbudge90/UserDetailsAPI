package com.persi.user.UserService.services;

import java.util.List;

import com.persi.user.UserService.entities.User;

public interface UserService {
	
	User crate(User usr);
	String getAuthUser(User usr);
	List<User> getAllUsers();
	

}
