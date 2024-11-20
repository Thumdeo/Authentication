package com.example.mongodb_springboot.Services;

import com.example.mongodb_springboot.Models.Assignment;
import com.example.mongodb_springboot.Models.User;
import com.example.mongodb_springboot.Repositories.AssignmentRepository;
import com.example.mongodb_springboot.Repositories.UserRepository;
import com.example.mongodb_springboot.dto.AssignmentRequest;
import com.example.mongodb_springboot.dto.LoginRequest;
import com.example.mongodb_springboot.dto.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private AssignmentService assignmentService;


    public void registerUser(UserRegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER"); // Default role for user registration
        userRepository.save(user);
    }

    public ResponseEntity<String> loginUser(LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent() && passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            // Generate JWT token (assuming JwtUtil is implemented)
            String token = "dummy-jwt-token"; // Replace with actual token generation logic
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    public void updateAssignmentTask(String id, String newTask) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));
        assignment.setTask(newTask); // Utilize the 'task' field
        assignmentRepository.save(assignment);
    }
    public void uploadAssignment(Assignment assignment) {
        assignmentService.uploadAssignment(assignment); // Delegating to AssignmentService
    }


}