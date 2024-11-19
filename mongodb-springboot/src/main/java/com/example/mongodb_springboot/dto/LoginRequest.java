package com.example.mongodb_springboot.dto;

import lombok.Data;
@Data
public class LoginRequest {
    private String username;
    private String password;

}
