package com.keyguard.infra.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.keyguard.domain.exceptions.user.UserNotFoundException;
import com.keyguard.domain.user.User;
import com.keyguard.repositories.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{

  @Autowired
  private UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.repository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found"));
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
  }
  
}
