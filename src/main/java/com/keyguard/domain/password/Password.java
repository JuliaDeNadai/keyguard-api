package com.keyguard.domain.password;

import java.time.ZonedDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "passwords")
public class Password {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String application;

  private String password;

  //private User user;

  private ZonedDateTime createdAt;

  private ZonedDateTime updatedAt;
}
