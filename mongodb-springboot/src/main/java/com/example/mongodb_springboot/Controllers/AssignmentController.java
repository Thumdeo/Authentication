package com.example.mongodb_springboot.Controllers;

import com.example.mongodb_springboot.Models.Assignment;
import com.example.mongodb_springboot.Services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/upload")
    public Assignment uploadAssignment(@RequestBody Assignment assignment) {
        return assignmentService.uploadAssignment(assignment);
    }

    @GetMapping
    public List<Assignment> getAssignments(@RequestParam String adminId) {
        return assignmentService.getAssignmentsByAdmin(adminId);
    }

    @PostMapping("/{id}/accept")
    public void acceptAssignment(@PathVariable String id) {
        assignmentService.updateAssignmentStatus(id, "ACCEPTED");
    }

    @PostMapping("/{id}/reject")
    public void rejectAssignment(@PathVariable String id) {
        assignmentService.updateAssignmentStatus(id, "REJECTED");
    }
}