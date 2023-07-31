package com.persi.user.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.persi.user.UserService.entities.ProductMaster;
import com.persi.user.UserService.entities.User;
import com.persi.user.UserService.security.JwtAuthRequest;
import com.persi.user.UserService.security.JwtAuthResponse;
import com.persi.user.UserService.security.JwtTokenHelper;
import com.persi.user.UserService.services.JwtService;
import com.persi.user.UserService.services.ProductService;
import com.persi.user.UserService.services.UserDetailInformationService;
import com.persi.user.UserService.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailInformationService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
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
	
	/*
	 * @PostMapping("/loginToken") public ResponseEntity<JwtAuthResponse>
	 * createToken(@RequestBody JwtAuthRequest authRequest) throws Exception {
	 * this.authenticate(authRequest.getUserName(), authRequest.getPassword());
	 * 
	 * UserDetails userDetails =
	 * this.userDetailsService.loadUserByUsername(authRequest.getUserName());
	 * 
	 * String token =this.tokenHelper.generateToken(userDetails);
	 * 
	 * JwtAuthResponse response= new JwtAuthResponse(); response.setToken(token);
	 * 
	 * return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	 * 
	 * }
	 */

	@PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody JwtAuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUserName());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }
	
	

}
