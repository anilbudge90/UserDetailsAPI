package com.persi.user.UserService.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persi.user.UserService.entities.User;

public interface UserReporitory extends JpaRepository<User, Long> {
	
	Optional<User> getByUserName(String userName);

	Optional<User> findByUserName(String username);

}
