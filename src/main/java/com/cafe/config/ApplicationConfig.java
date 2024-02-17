package com.cafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cafe.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
	
	private final UserRepository repository;
	
	
	
	
	public ApplicationConfig(UserRepository repository) {
		super();
		this.repository = repository;
	}




	@Bean
	public UserDetailsService userDetailsService() {
		return  username -> repository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
		
	}
	
	

}
