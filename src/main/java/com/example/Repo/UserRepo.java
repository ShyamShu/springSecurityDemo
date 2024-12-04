package com.example.Repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.MyUser;

@Repository
public interface UserRepo extends MongoRepository<MyUser , ObjectId>{

	MyUser findByUsername(String username);
}

