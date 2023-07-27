package com.persi.user.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persi.user.UserService.entities.User;
import com.persi.user.UserService.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity<User> createUser(@RequestBody User usr)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.crate(usr));
	}
	
	@GetMapping("/adminUser")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String getAuthUsers()
	{
		return "Admin User";
	}
	
	@GetMapping("/normalUser")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String getAllUsers()
	{
		return "Normal Users";
	}
	
	@GetMapping("/getAllUsers")
	public List<User> getData()
	{
		List<User> allUsers = userService.getAllUsers();
		
		return allUsers;
	}

}
