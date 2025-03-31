package com.raiyan.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.raiyan.crud.model.UserDetails;
import com.raiyan.crud.service.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") // Adjust for frontend
@RestController
@RequestMapping("/userdetails")
public class UserDetailsController {
    
    @Autowired
    private UserDetailsService userDetailsService;

    // ✅ Get UserDetails by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDetails> getUserDetailsById(@PathVariable int id) {
        UserDetails userDetails = userDetailsService.getUserDetailsByUid(id);
        return (userDetails != null) ? ResponseEntity.ok(userDetails) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // ✅ Create UserDetails
    @PostMapping
    public ResponseEntity<String> createUserDetails(@RequestBody UserDetails userDetails) {
        userDetailsService.createUserDetails(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body("User details created successfully!");
    }

    // ✅ Update UserDetails
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserDetails(@PathVariable int id, @RequestBody UserDetails userDetails) {
        UserDetails updatedUserDetails = userDetailsService.updateUserDetails(id, userDetails);
        return (updatedUserDetails != null) ? ResponseEntity.ok(updatedUserDetails) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }

    // ✅ Delete UserDetails
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserDetails(@PathVariable int id) {
        userDetailsService.deleteUserDetails(id);
        return ResponseEntity.ok("User details deleted successfully.");
    }
}
