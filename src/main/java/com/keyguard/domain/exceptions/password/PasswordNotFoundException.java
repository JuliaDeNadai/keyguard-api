package com.keyguard.domain.exceptions.password;

public class PasswordNotFoundException extends RuntimeException {

  public PasswordNotFoundException() {
    super("Password not found");
  }

  public PasswordNotFoundException(String message){
    super(message);
  }
}