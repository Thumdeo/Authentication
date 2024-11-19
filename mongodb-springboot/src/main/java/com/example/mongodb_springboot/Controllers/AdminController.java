package com.example.mongodb_springboot.Controllers;


import com.example.mongodb_springboot.Services.AdminService;
import com.example.mongodb_springboot.dto.LoginRequest;
import com.example.mongodb_springboot.dto.UserRegistrationRequest;
import com.example.mongodb_springboot.Models.Admin;
import com.example.mongodb_springboot.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody UserRegistrationRequest request) {
        adminService.registerAdmin(request);
        return ResponseEntity.ok("Admin registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestBody LoginRequest request) {
        Admin admin = adminService.getAdminByUsername(request.getUsername());
        if (admin != null && passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            String token = jwtUtil.generateToken(admin.getUsername(), admin.getRole());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}



