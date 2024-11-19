package com.example.mongodb_springboot.dto;


import lombok.Data;

@Data
public class AssignmentRequest {
    private String userId;
    private String task;
    private String adminUsername;
}
