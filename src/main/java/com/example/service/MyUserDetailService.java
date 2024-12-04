package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Repo.UserRepo;
import com.example.entity.MyUser;

@Service
public class MyUserDetailService implements UserDetailsService {

	
	@Autowired
	private UserRepo userRepo ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MyUser user = userRepo.findByUsername(username);
		//System.out.println(user.getUserName());
		
		if(user == null)
		{
			System.out.println("No User Found");
			throw new UsernameNotFoundException("user");
		}
		return new UserPrincipal(user);
	}
	
	

}
