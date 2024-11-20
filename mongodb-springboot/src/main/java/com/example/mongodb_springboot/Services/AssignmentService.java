package com.example.mongodb_springboot.Services;

import com.example.mongodb_springboot.Models.Assignment;
import com.example.mongodb_springboot.Repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;

    public Assignment uploadAssignment(Assignment assignment) {

        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAssignmentsByAdmin(String adminId) {
        return assignmentRepository.findByAdminUsername(adminId);
    }
    
    public void updateAssignmentStatus(String id, String status) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));
        assignment.setStatus(status);
        assignmentRepository.save(assignment);
    }


}