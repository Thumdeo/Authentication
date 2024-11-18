package com.example.mongodb_springboot.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "assignments")
public class Assignment {
    @Id
    private String id;
    private String userId;
    private String task;
    private String adminUsername;
    private String status = "PENDING"; // PENDING, ACCEPTED, REJECTED
    private LocalDateTime createdAt = LocalDateTime.now();
}