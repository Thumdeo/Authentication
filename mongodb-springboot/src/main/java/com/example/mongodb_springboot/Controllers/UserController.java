package com.example.mongodb_springboot.Controllers;


import com.example.mongodb_springboot.Models.Assignment;
import com.example.mongodb_springboot.Services.UserService;
import com.example.mongodb_springboot.dto.LoginRequest;
import com.example.mongodb_springboot.dto.UserRegistrationRequest;
import com.example.mongodb_springboot.dto.AssignmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
        userService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }

    @PostMapping("/assignments/upload")
    public ResponseEntity<String> uploadAssignment(@RequestBody AssignmentRequest request) {
        userService.uploadAssignment(request);
        return ResponseEntity.ok("Assignment uploaded successfully");
    }

    @GetMapping("/assignments")
    public ResponseEntity<List<Assignment>> getAssignments(@RequestParam String userId) {
        return ResponseEntity.ok(userService.getAssignmentsByUser(userId));
    }
}