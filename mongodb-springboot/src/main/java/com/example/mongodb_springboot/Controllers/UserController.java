package com.example.mongodb_springboot.Controllers;


import com.example.mongodb_springboot.Models.Assignment;
import com.example.mongodb_springboot.Services.AssignmentService;
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


    @Autowired
    private AssignmentService assignmentService;  // Use AssignmentService instead of UserService


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
        // Convert AssignmentRequest to Assignment and upload it
        Assignment assignment = new Assignment();
        assignment.setTask(request.getTask());
        assignment.setUserId(request.getUserId());
        assignment.setAdminUsername(request.getAdminUsername());
        assignmentService.uploadAssignment(assignment);  // Call the correct service method
        return ResponseEntity.ok("Assignment uploaded successfully");
    }

    @GetMapping("/assignments")
    public ResponseEntity<List<Assignment>> getAssignments(@RequestParam String userId) {
        return ResponseEntity.ok(userService.getAssignmentsByUser(userId));
    }
}