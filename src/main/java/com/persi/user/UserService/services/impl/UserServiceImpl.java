package com.persi.user.UserService.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.persi.user.UserService.entities.User;
import com.persi.user.UserService.repositories.UserReporitory;
import com.persi.user.UserService.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserReporitory reporitory;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@Override
	public User crate(User usr) {
		usr.setPassword(this.bCryptPasswordEncoder.encode(usr.getPassword()));
		return reporitory.save(usr);
	}

	@Override
	public String getAuthUser(User usr) {
		
	Optional<User> byUserName = reporitory.getByUserName(usr.getUserName());
	
	if (byUserName.isPresent())
		{
			if(bCryptPasswordEncoder.matches(usr.getPassword(), byUserName.get().getPassword()))
			return "valid User";
	
		else
			return "Invalid User";
		}
		return"User Not Available in system";
	}

	@Override
	public List<User> getAllUsers() {
		List<User> findAll = reporitory.findAll();
		
		
		return findAll;
	}

}
