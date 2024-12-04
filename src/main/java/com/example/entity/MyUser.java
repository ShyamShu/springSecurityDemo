package com.example.entity;


import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@EntityScan
@Document(collection = "User_entries")
public class MyUser {
	
	@Id
	private ObjectId id ;
	
	
	private String username ;
	
	private String password;
	
	
	public MyUser() {
		
	}
	public String getpassword() {
		return password;
	}


	public void setpassword(String password) {
		this.password = password;
		}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public ObjectId getId() {
		return id;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}


	
}
