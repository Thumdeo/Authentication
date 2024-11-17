package com.example.mongodb_springboot.Repositories;

import com.example.mongodb_springboot.Models.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AssignmentRepository extends MongoRepository<Assignment, String> {
    List<Assignment> findByAdminId(String adminId);
}
