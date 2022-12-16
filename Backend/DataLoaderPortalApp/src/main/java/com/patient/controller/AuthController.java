package com.patient.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.jwt.JwtTokenUtil;
import com.patient.model.AuthRequest;
import com.patient.model.AuthResponse;
import com.patient.model.User;



@RestController
@CrossOrigin("*")
public class AuthController {
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	
	@Autowired 
	AuthenticationManager authManager;
	@Autowired 
	JwtTokenUtil jwtUtil;

	// login method to generate barer token
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getUsername(), request.getPassword())
			);
			
			User user = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			log.info("Token generated : "+accessToken);
			AuthResponse response = new AuthResponse(user.getUsername(), accessToken);
			
			return ResponseEntity.ok().body(response);
			
		} catch (BadCredentialsException ex) {
			log.warn("Username or passward is wrong");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}


}
