package com.keyguard.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.keyguard.domain.password.Password;

@Repository
public interface PasswordRepository extends MongoRepository<Password, String> {
  Optional<Password> findByApplication(String application);

  //@Query("{ 'user' : ?0 }")
  List<Password> findByUserId(ObjectId userId);

  List<Password> findByUserIdAndApplication(ObjectId userId, String application);
}
