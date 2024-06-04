package com.keyguard.controllers;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keyguard.domain.exceptions.user.UserNotFoundException;
import com.keyguard.domain.password.Password;
import com.keyguard.domain.user.User;
import com.keyguard.dto.PasswordRequestDTO;
import com.keyguard.dto.PasswordResponseDTO;
import com.keyguard.infra.security.TokenService;
import com.keyguard.repositories.PasswordRepository;
import com.keyguard.repositories.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
public class PasswordController {

  private final PasswordEncoder passwordEncoder;
  private final PasswordRepository repository;
  private final UserRepository userRepository;

  private final TokenService tokenService;

  @GetMapping("/{userToken}/all")
  public ResponseEntity getPassword(@PathVariable String userToken, @RequestParam(required = false) String application){

    String email = tokenService.validateToken(userToken);

    User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));

    List<Password> passwords = new ArrayList<>();

    if(application != null){
      passwords = this.repository.findByUserIdAndApplication(new ObjectId(user.getId()), application);
      return ResponseEntity.ok(passwords);
    }


    passwords = this.repository.findByUserId(new ObjectId(user.getId()));
    return ResponseEntity.ok(passwords);
  }

  @GetMapping("/{application}")
  public ResponseEntity getPasswordByApplication(@PathVariable String application){

    Password password = this.repository.findByApplication(application).orElseThrow(() -> new UserNotFoundException("User not found"));;

    return ResponseEntity.ok(password);
  }

  @PostMapping("/")
  public ResponseEntity register(@Valid @RequestBody PasswordRequestDTO body){
    //Optional<User> user = this.userRepository.findById(body.userId());

    //if(user.isEmpty()) return ResponseEntity.badRequest().build();

    Optional<Password> pass = this.repository.findByApplication(body.application());

    if(pass.isEmpty()) {
      Password newPass = new Password();
        newPass.setPassword(passwordEncoder.encode(body.password()));
        newPass.setApplication(body.application());
        //newPass.setUser(user);

        this.repository.save(newPass);
        
        return ResponseEntity.ok(new PasswordResponseDTO(newPass.getApplication()));
    }

    return ResponseEntity.badRequest().build();
  }
}
