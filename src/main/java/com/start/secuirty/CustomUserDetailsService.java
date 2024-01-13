package com.start.secuirty;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.start.entity.User;
import com.start.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
									.orElseThrow(()-> new UsernameNotFoundException("User not exist by username or email"));
			Set<SimpleGrantedAuthority> authorities = user.getRoles()
														.stream()
														.map((role) -> new SimpleGrantedAuthority(role.getName()))
														.collect(Collectors.toSet());
			
		return new org.springframework.security.core.userdetails.User(
								usernameOrEmail,
								user.getPassword(),
								authorities);
	}

}
