package com.keyguard.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.keyguard.domain.password.Password;
import com.keyguard.domain.user.User;

@Repository
public interface PasswordRepository  extends MongoRepository<Password, String> {
  Optional<Password> findByApplication(String application);
}
