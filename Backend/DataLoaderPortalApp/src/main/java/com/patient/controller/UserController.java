package com.patient.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.model.User;
import com.patient.model.UserDTO;
import com.patient.service.UserService;


@RestController
@CrossOrigin("*")
public class UserController {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);


	@Autowired
	private UserService service;

	@PutMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
		log.info("User : "+user);
		User createdUser = service.save(user);
		URI uri = URI.create("/users/" + createdUser.getId());
		
		UserDTO userDto = new UserDTO(createdUser.getId(), createdUser.getUsername());

		return ResponseEntity.created(uri).body(userDto);
	}

}
