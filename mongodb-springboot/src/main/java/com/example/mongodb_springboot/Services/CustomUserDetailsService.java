package com.example.mongodb_springboot.Services;


import com.example.mongodb_springboot.Models.User;
import com.example.mongodb_springboot.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private final UserRepository userRepository;
        public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            // Fetch user from the database
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() ->new UsernameNotFoundException("User not found"));
            // Convert your User entity to Spring Security's UserDetails
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole()) // Assuming role is a valid Spring Security role
                    .build();
        }
    }
