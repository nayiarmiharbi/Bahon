package com.raiyan.crud.controller;

import com.raiyan.crud.model.User;
import com.raiyan.crud.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            logger.info("No users found.");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> {
                       logger.warning("User with ID " + id + " not found.");
                       return ResponseEntity.notFound().build();
                   });
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        logger.info("User created successfully with ID: " + createdUser.getUID());
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            User updatedUser = userService.updateUser(id, user);
            logger.info("User with ID " + id + " updated successfully.");
            return ResponseEntity.ok(updatedUser);
        } else {
            logger.warning("User with ID " + id + " not found for update.");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            userService.deleteUser(id);
            logger.info("User with ID " + id + " deleted successfully.");
            return ResponseEntity.noContent().build();
        } else {
            logger.warning("User with ID " + id + " not found for deletion.");
            return ResponseEntity.notFound().build();
        }
    }
}
