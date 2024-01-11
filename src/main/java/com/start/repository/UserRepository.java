package com.start.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.start.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	
	//Boolean existByEmail(String email);
	
	Optional<User> findByUsernameOrEmail(String username, String email);
}