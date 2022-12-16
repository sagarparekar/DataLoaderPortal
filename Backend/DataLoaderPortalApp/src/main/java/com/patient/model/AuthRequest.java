package com.patient.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AuthRequest {
	
	@NotNull 
	@Email 
	@Length(min = 5, max = 50)
	private String username;
	
	@NotNull 
	@Length(min = 8, max = 20)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
