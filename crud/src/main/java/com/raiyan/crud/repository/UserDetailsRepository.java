package com.raiyan.crud.repository;

import com.raiyan.crud.model.UserDetails;

import org.springframework.dao.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDetailsRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserDetailsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to convert SQL ResultSet into UserDetails object
    private final RowMapper<UserDetails> rowMapper = (rs, rowNum) -> new UserDetails(
            rs.getInt("UID"),
            rs.getString("fullName"),
            rs.getString("address"),
            rs.getString("contact"),
            rs.getString("driver_license_number") // Matches SQL column name
    );

    // ✅ Get UserDetails by UID
    public Optional<UserDetails> findByUID(int UID) {
        String sql = "SELECT * FROM userDetails WHERE UID = ?";
        List<UserDetails> details = jdbcTemplate.query(sql, rowMapper, UID);
        return details.stream().findFirst();
    }

    // ✅ Insert UserDetails
    public void saveUserDetails(UserDetails userDetails) {
        try {
            // ✅ Step 1: Check if UID exists in the users table
            String checkUserSql = "SELECT COUNT(*) FROM users WHERE UID = ?";
            Integer count = jdbcTemplate.queryForObject(checkUserSql, Integer.class, userDetails.getUID());

            if (count == null || count == 0) {
                // 🔴 UID does not exist in the users table, return false or throw exception
                throw new DataIntegrityViolationException("Error: UID " + userDetails.getUID() + userDetails.getAddress() + " does not exist in users table.");
            }

            // ✅ Step 2: If UID exists, insert into userDetails
            String sql = "INSERT INTO userDetails (UID, fullName, address, contact, driver_license_number) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, userDetails.getUID(), userDetails.getFullName(), userDetails.getAddress(),
                    userDetails.getContact(), userDetails.getDriverLicenseNumber());
        } catch (DuplicateKeyException e) {
            throw new DataIntegrityViolationException("Driving License Number " + userDetails.getDriverLicenseNumber() + " already exists.");
        }
    }

    // ✅ Update UserDetails
    public void updateUserDetails(UserDetails userDetails) {
        String sql = "UPDATE userDetails SET fullName = ?, address = ?, contact = ?, driver_license_number = ? WHERE UID = ?";
        jdbcTemplate.update(sql, userDetails.getFullName(), userDetails.getAddress(), userDetails.getContact(),
                userDetails.getDriverLicenseNumber(), userDetails.getUID());
    }

    // ✅ Delete UserDetails by UID
    public void deleteUserDetails(int UID) {
        String sql = "DELETE FROM userDetails WHERE UID = ?";
        jdbcTemplate.update(sql, UID);
    }
}
