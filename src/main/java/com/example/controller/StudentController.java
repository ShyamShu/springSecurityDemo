package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {

	private ArrayList<Student> students = new ArrayList<>(List.of(
			new Student(1 , "shyam" , 65),
			new Student(2,"ram",56)
		));
	
	@GetMapping("student")
	public List<Student> getAll()
	{
		return students;
	}
	
	@PostMapping("student")
	public Student saveNew(@RequestBody Student student)
	{
		students.add(student);
		return student;
	}
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrf(HttpServletRequest request)
	{
		return (CsrfToken)request.getAttribute("_csrf");
	}
}
