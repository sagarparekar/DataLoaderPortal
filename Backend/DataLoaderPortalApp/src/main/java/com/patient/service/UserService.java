package com.patient.service;


import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.patient.model.User;
import com.patient.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Service
@Transactional
public class UserService {
	
	Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired 
	private UserRepository repo;
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	public User save(User user) {
		
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		
		return repo.save(user);
		
		
	}

}
