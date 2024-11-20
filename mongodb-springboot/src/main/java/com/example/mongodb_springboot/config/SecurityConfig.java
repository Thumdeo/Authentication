package com.example.mongodb_springboot.config;

import com.example.mongodb_springboot.Services.AdminService;
import com.example.mongodb_springboot.Services.UserService;
import com.example.mongodb_springboot.filter.JwtFilter;
import com.example.mongodb_springboot.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {



        @Autowired
        private JwtFilter jwtFilter;

        // Define the SecurityFilterChain bean
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                        .csrf(csrf -> csrf.disable())
                        .authorizeRequests()
                        .requestMatchers("/admins/register", "/admins/login", "/users/register", "/users/login", "/users/assignments/upload").permitAll()
                        .requestMatchers("/assignments/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                        .and()
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);  // Use your JWT filter

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        // If you still need AuthenticationManager, define it as a bean
        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
                return http.getSharedObject(AuthenticationManagerBuilder.class).build();
        }
}
