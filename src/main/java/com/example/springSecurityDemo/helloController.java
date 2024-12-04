package com.example.springSecurityDemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class helloController {
	
	@GetMapping("/home")
	public String greet()
	{
		return "home";
	}
	@GetMapping("/hello")
	public String hello()
	{
		return "hello";
	}
	
}
