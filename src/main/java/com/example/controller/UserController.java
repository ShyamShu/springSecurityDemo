package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.MyUser;
import com.example.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody MyUser user)
	{
		 if (user.getpassword() == null || user.getpassword().isEmpty()) {
		        return ResponseEntity.badRequest().body("Password cannot be null or empty");
		    }
		userService.saveUser(user);
		return new ResponseEntity<String>("Registered Successfullty" , HttpStatus.OK );
    }
	
	@PostMapping("/login")
	public String login(@RequestBody MyUser user)
	{
		return userService.verify(user);
	}
	
	

	
	
}
