package com.example.mongodb_springboot.Controllers;

import com.example.mongodb_springboot.Models.Assignment;
import com.example.mongodb_springboot.Services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAssignment(@RequestBody Assignment assignment) {
        assignmentService.uploadAssignment(assignment);
        return ResponseEntity.ok("Assignment uploaded successfully");
    }

    @GetMapping("/admin")
    public ResponseEntity<List<Assignment>> getAssignmentsForAdmin(@RequestParam String adminUsername) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByUser(adminUsername));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateAssignmentStatus(@PathVariable String id, @RequestParam String status) {
        assignmentService.updateAssignmentStatus(id, status);
        return ResponseEntity.ok("Assignment status updated successfully");
    }

//    @GetMapping
//    public List<Assignment> getAssignments(@RequestParam String adminId) {
//        return assignmentService.getAssignmentsByAdmin(adminId);
//    }
//
//    @PostMapping("/{id}/accept")
//    public void acceptAssignment(@PathVariable String id) {
//        assignmentService.updateAssignmentStatus(id, "ACCEPTED");
//    }
//
//    @PostMapping("/{id}/reject")
//    public void rejectAssignment(@PathVariable String id) {
//        assignmentService.updateAssignmentStatus(id, "REJECTED");
//    }
}