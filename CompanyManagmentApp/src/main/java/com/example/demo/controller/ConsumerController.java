package com.example.demo.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.example.demo.model.UserDto;


@RestController
@CrossOrigin("*")
public class ConsumerController {

	@PostMapping(value="/register", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> consumerCompany(@RequestBody UserDto userDto) throws Exception
	{
		//UserDto userDto=new UserDto(id, username, password);
	
		String baseUrl = "http://localhost:8082/auth/user/registerUser";
		//String baseUrl = "http://35.89.54.177:8082/auth/user/registerUser";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map<String, String>> response=null;

		try { 
	
			response = restTemplate.exchange(baseUrl, HttpMethod.POST, getHeader(userDto), new ParameterizedTypeReference<Map<String, String>>(){}); 	
			
		} catch (Exception e) {
			return new ResponseEntity<String>("User registered successfully", HttpStatus.OK);
		}

		return new ResponseEntity<Map<String, String>>(response.getBody(),HttpStatus.OK);

	}
	
	@PostMapping(value="/login", consumes=MediaType.APPLICATION_JSON_VALUE)
	//public ResponseEntity<?> consumerLogin(@RequestParam("username")String username, @RequestParam("password")String password) throws Exception
	public ResponseEntity<?> consumerLogin(@RequestBody UserDto userDto) throws Exception
	{
		
	
		String baseUrl = "http://localhost:8082/auth/user/login";
	//	String baseUrl = "http://35.89.54.177:8082/auth/user/login";
		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<Map<String, String>> response=null;
//		UserDto userDto=new UserDto();
//		userDto.setPassword(password);
//		userDto.setUsername(username);
		  String response = null;
		  try {
	            response =restTemplate.postForObject(baseUrl, userDto, String.class);
	            System.out.println("Response:::: "+response.toString());
	            return new ResponseEntity<>(response, HttpStatus.OK);
	            
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	        
	        return new ResponseEntity<String>("Response object is null", HttpStatus.NO_CONTENT);
//		try { 
//	
//			response = restTemplate.exchange(baseUrl, HttpMethod.POST, getHeader(userDto), new ParameterizedTypeReference<Map<String, String>>(){}); 	
//			
//		} catch (Exception e) {
//			return new ResponseEntity<String>("User registered successfully", HttpStatus.OK);
//		}
//
//		return new ResponseEntity<Map<String, String>>(response.getBody(),HttpStatus.OK);

	}
	
	private static HttpEntity<UserDto> getHeader(UserDto userDto) throws Exception
	{
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<UserDto>(userDto,headers);
	}
	
	

	
}

//@RequestParam("id")int id,@RequestParam("username")String username,@RequestParam("password")String password
