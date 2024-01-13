package com.start.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.start.dto.LoginDto;
import com.start.dto.RegisterDto;
import com.start.entity.Role;
import com.start.entity.User;
import com.start.exception.TodoAPIException;
import com.start.repository.RoleRepository;
import com.start.repository.UserRepository;
import com.start.service.AuthService;
@Service
public class AuthServiceImpl implements AuthService{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String register(RegisterDto registerDto) {
		//check username is already existing in DB
		Boolean existsByUsername = userRepository.existsByUsername(registerDto.getUsername());
		if(existsByUsername) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username already exists");
		}
		
		//check email already exist
		if(userRepository.existsByEmail(registerDto.getEmail())) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST, "");
		}
		User user = new User();
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER");
		roles.add(userRole);
		
		user.setRoles(roles);
		userRepository.save(user);
		return "User Registered Successfully";
	}

	@Override
	public String login(LoginDto loginDto) {
		Authentication authenticatation = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
												loginDto.getUsernameOrEmail(), 
												loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticatation);
		return "User logged-in successfully";
	}

}
