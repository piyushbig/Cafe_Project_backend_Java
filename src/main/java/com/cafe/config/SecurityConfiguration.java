package com.cafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	private final UserDetailsService userDetailsService;
	
	


	private final JwtAuthenticationFilter jwtauthenticationFilter;
	
	public SecurityConfiguration(UserDetailsService userDetailsService, JwtAuthenticationFilter jwtauthenticationFilter) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtauthenticationFilter = jwtauthenticationFilter;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		
		
		
		return http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						req->req.requestMatchers("/login/**", "/register/**","/products/**").permitAll()
						.requestMatchers("/products/**").hasRole("Cafe")
						
						.anyRequest()
						.authenticated()
						
							).userDetailsService(userDetailsService).sessionManagement(
									session->session
									
									.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
									).addFilterBefore(jwtauthenticationFilter,UsernamePasswordAuthenticationFilter.class)
					.build();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	public AuthenticationManager authenticatiionManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
