package com.keyguard.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.keyguard.domain.user.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{ 
  Optional<User> findByEmail(String email);

  ArrayList<User> findAll();
}
