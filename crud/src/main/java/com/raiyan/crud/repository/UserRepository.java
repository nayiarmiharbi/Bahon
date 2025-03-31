package com.raiyan.crud.repository;

import com.raiyan.crud.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> rowMapper = (rs, rowNum) -> new User(
            rs.getInt("UID"),
            rs.getInt("userType"),
            rs.getString("username"),
            rs.getString("password")
    );

    // Insert User
    public void saveUser(User user) {
        String sql = "INSERT INTO users (userType, username, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserType(), user.getUsername(), user.getPassword());
    }

    // Get All Users
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Get User by ID
    public Optional<User> getUserById(int id) {
        String sql = "SELECT * FROM users WHERE UID = ?";
        List<User> users = jdbcTemplate.query(sql, rowMapper, id);
        return users.stream().findFirst();
    }

    // Update User
    public void updateUser(int id, User user) {
        String sql = "UPDATE users SET userType = ?, username = ?, password = ? WHERE UID = ?";
        jdbcTemplate.update(sql, user.getUserType(), user.getUsername(), user.getPassword(), id);
    }

    // Delete User
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE UID = ?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        List<User> users = jdbcTemplate.query(sql, rowMapper, username, password);
        return users.stream().findFirst();
    }
    
}
