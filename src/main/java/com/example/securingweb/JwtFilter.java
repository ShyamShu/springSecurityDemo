package com.example.securingweb;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.service.JWTservice;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class JwtFilter extends OncePerRequestFilter {

	
	@Autowired
	JWTservice jwTservice;
	
	@Autowired
	org.springframework.context.ApplicationContext context;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader =  request.getHeader("Authorization");
		String username = null ;
		String token = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer")) {
			token = authHeader.substring(7);
			username = jwTservice.extractUserName(token);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			 UserDetailsService userDetailsService = context.getBean(UserDetailsService.class);
		        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		        
			if(jwTservice.validate(token , userDetails))
			{
				UsernamePasswordAuthenticationToken authToken = new  UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			
		}
		filterChain.doFilter(request, response);
	}

}
