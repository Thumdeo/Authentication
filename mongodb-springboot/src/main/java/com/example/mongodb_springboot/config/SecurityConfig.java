package com.example.mongodb_springboot.config;

import com.example.mongodb_springboot.Services.AdminService;
import com.example.mongodb_springboot.Services.CustomUserDetailsService;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final UserDetailsService userDetailsService;
        private final JwtFilter jwtFilter;

        public SecurityConfig(UserDetailsService userDetailsService, JwtFilter jwtFilter) {
                this.userDetailsService = userDetailsService;
                this.jwtFilter = jwtFilter;
        }

        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
                AuthenticationManagerBuilder authenticationManagerBuilder =
                        http.getSharedObject(AuthenticationManagerBuilder.class);

                authenticationManagerBuilder
                        .userDetailsService(userDetailsService)
                        .passwordEncoder(passwordEncoder());
                return authenticationManagerBuilder.getOrBuild();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                        //Disable CSRF directly
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/register", "/login").permitAll()
                                .requestMatchers("/admins/**", "/assignments/**").authenticated()
                        )
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                System.out.println("BCryptPasswordEncoder bean initialized");
                return new BCryptPasswordEncoder();
        }

}





