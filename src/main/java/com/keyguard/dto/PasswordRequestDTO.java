package com.keyguard.dto;

import jakarta.validation.constraints.NotNull;

public record PasswordRequestDTO (
  
  @NotNull(message = "Field 'passsword' is required")  
  String password, 
  @NotNull(message = "Field 'aplication' is required")  
  String application/* ,
  @NotNull(message = "Field 'userId' is required")  
  String userId*/
  
  ) {}
