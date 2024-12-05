package com.example.mongodb_springboot.Services;


import com.example.mongodb_springboot.Models.Admin;
import com.example.mongodb_springboot.Repositories.AdminRepository;
import com.example.mongodb_springboot.dto.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

        private final AdminRepository adminRepository;

        private final BCryptPasswordEncoder passwordEncoder;

        @Autowired
        public AdminService(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }
        public void registerAdmin(UserRegistrationRequest request) {
            Admin admin = new Admin();
            admin.setUsername(request.getUsername());
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
            adminRepository.save(admin);
        }

        public Admin getAdminByUsername(String username) {
            return adminRepository.findByUsername(username).orElse(null);
        }
}
