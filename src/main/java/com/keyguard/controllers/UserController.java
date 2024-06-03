package com.keyguard.controllers;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keyguard.domain.user.User;
import com.keyguard.repositories.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserRepository repository;

    @GetMapping
    public ResponseEntity<ArrayList<User>> getAll(){
        ArrayList<User> users = this.repository.findAll();

        return ResponseEntity.ok(users);
    }
}