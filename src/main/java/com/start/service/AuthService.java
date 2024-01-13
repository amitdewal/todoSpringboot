package com.start.service;

import com.start.dto.LoginDto;
import com.start.dto.RegisterDto;

public interface AuthService {
	String register(RegisterDto registerDto);
	String login(LoginDto loginDto);

}
