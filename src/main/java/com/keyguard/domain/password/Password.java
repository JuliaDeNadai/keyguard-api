package com.keyguard.domain.password;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.keyguard.domain.user.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
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

  @MongoId(FieldType.OBJECT_ID)
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String application;

  private String password;

  private User user;

  @CreatedDate
  private Date createdAt;

  private Date updatedAt;

  @Transient
  private Date expiresAt;

  @PrePersist
  public void prePersist() {
    if (this.createdAt == null) {
        this.createdAt = new Date();
    }

    calculateExpiresAt();
  }
  
  public void calculateExpiresAt() {
        LocalDate createdDate = createdAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate expiresDate = createdDate.plusMonths(1);
        this.expiresAt = Date.from(expiresDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}



