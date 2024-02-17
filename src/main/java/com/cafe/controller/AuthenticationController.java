package com.cafe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.entity.AuthenticationResponse;
import com.cafe.entity.User;
import com.cafe.services.AuthenticationService;

@RestController
public class AuthenticationController {
	
	private final AuthenticationService authService;

	public AuthenticationController(AuthenticationService authService) {
		super();
		this.authService = authService;
	}
	
	@PostMapping("/register/user")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody User request){
		
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/login/user")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody User request){
		
		return ResponseEntity.ok(authService.authenticate(request));
	}
	
	
	
	
}
