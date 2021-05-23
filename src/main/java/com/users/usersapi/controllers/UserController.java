package com.users.usersapi.controllers;

import java.util.List;

import com.users.usersapi.models.User;

import com.users.usersapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class UserController {

    @Autowired
    UserRepository userRepository;

    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") long id) {

        User user = userRepository.findById(id);

        if (user != null) {
            return ResponseEntity.status(200).body(user);
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping("/users/signup")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {

        User currentUser = userRepository.findById(id);

        if (currentUser == null) {
            return ResponseEntity.notFound().build();
        }
        
        user.setId(id);
        userRepository.save(user);
        return ResponseEntity.status(200).body(user);

    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestBody User user) {
        userRepository.delete(user);
    }
}
