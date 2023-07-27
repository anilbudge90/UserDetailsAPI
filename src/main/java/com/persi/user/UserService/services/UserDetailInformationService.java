package com.persi.user.UserService.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.persi.user.UserService.entities.User;
import com.persi.user.UserService.repositories.UserReporitory;
import com.persi.user.UserService.services.impl.UserDetailsImpl;

@Component
public class UserDetailInformationService implements UserDetailsService {

	@Autowired
	private UserReporitory reporitory;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> byUserName = reporitory.findByUserName(username);
		
		return byUserName.map(UserDetailsImpl::new)
		.orElseThrow(()->new UsernameNotFoundException("User not Found !!!"));
		
		
	}

}
