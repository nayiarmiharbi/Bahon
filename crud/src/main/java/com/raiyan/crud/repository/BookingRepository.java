package com.raiyan.crud.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.raiyan.crud.model.Booking;

@Repository
public class BookingRepository {
    private final JdbcTemplate jdbcTemplate;
    public BookingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private final RowMapper<Booking> rowMapper = (rs, rowNum) -> new Booking(
            rs.getInt("BID"),
            rs.getString("regNo"),
            rs.getDate("fromDate"),
            rs.getDate("tillDate")
    );

    // Insert Booking
    public void saveBooking(Booking booking) {
        String sql = "INSERT INTO bookings (regNo, fromDate, tillDate) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, booking.getRegNo(), booking.getFromDate(), booking.getTillDate());
    }

    // Get All Bookings
    public List<Booking> getAllBookings() {
        String sql = "SELECT * FROM bookings";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Get Booking by ID
    public Booking getBookingById(int id) {
        String sql = "SELECT * FROM bookings WHERE BID = ?";
        List<Booking> bookings = jdbcTemplate.query(sql, rowMapper, id);
        return bookings.isEmpty() ? null : bookings.get(0);
    }

    // Update Booking
    public void updateBooking(int id, Booking booking) {
        String sql = "UPDATE bookings SET regNo = ?, fromDate = ?, tillDate = ? WHERE BID = ?";
        jdbcTemplate.update(sql, booking.getRegNo(), booking.getFromDate(), booking.getTillDate(), id);
    }

    // Delete Booking
    public void deleteBooking(int id) {
        String sql = "DELETE FROM bookings WHERE BID = ?";
        jdbcTemplate.update(sql, id);
    }

    // Get Booking by regNo
    public List<Booking> getBookingsByRegNo(String regNo) {
        String sql = "SELECT * FROM bookings WHERE regNo = ?";
        return jdbcTemplate.query(sql, rowMapper, regNo);
    }

    // Get Booking by Date Range
    public List<Booking> getBookingsByDateRange(String regNo, java.sql.Date fromDate, java.sql.Date tillDate) {
        String sql = "SELECT * FROM bookings WHERE regNo = ? AND fromDate >= ? AND tillDate <= ?";
        return jdbcTemplate.query(sql, rowMapper, regNo, fromDate, tillDate);
    }
}
