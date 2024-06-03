package com.keyguard.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO (
  @NotNull(message = "Field 'name' is required")  
  String name, 
  @NotNull(message = "Field 'email' is required")  
  String email, 
  @NotNull(message = "Field 'password' is required")  
  String password) {}
