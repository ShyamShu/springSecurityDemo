package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Repo.UserRepo;
import com.example.entity.MyUser;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JWTservice jwTservice;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public void saveUser(MyUser user)
	{
		user.setpassword(encoder.encode(user.getpassword()));
		userRepo.save(user);
	}
	public String verify(MyUser user) {
		org.springframework.security.core.Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getpassword()));
		
		if(authentication.isAuthenticated())
		{
			return jwTservice.generateToken(user.getUsername());
		}
		else {
			return "login failed";
		}
	}

}
