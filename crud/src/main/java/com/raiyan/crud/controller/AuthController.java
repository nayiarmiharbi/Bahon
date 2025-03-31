package com.raiyan.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.raiyan.crud.model.User;
import com.raiyan.crud.model.UserDetails;
import com.raiyan.crud.repository.UserRepository;
import com.raiyan.crud.repository.UserDetailsRepository;
import com.raiyan.crud.dto.AuthRequest;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // Adjust for frontend
public class AuthController {

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        logger.info("Login attempt for user: " + authRequest.getUsername());

        // Fetch user from database
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Fetch user details
            Optional<UserDetails> userDetailsOptional = userDetailsRepository.findByUID(user.getUID());

            if (userDetailsOptional.isPresent()) {
                logger.info("User found with details: " + userDetailsOptional.get());
                return ResponseEntity.ok(userDetailsOptional.get());
            } else {
                logger.warning("User found but details missing for UID: " + user.getUID());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user.getUID());
            }
        } else {
            logger.warning("Invalid login attempt for username: " + authRequest.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }
}
