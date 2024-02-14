package com.cafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
	
	private final UserRepository repository;
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return  username -> repository.findByEmail(username).orElseThrow(()-> new UserNotFoundException("user not found"));
		
	}

}
