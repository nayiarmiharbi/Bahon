package com.raiyan.crud.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.raiyan.crud.model.Booking;
import com.raiyan.crud.service.BookingService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bookings")
public class BookingController {
    
    private static final Logger logger = Logger.getLogger(BookingController.class.getName());
    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        if (bookings.isEmpty()) {
            logger.info("No bookings found.");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        } else {
            logger.warning("Booking not found with ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        bookingService.createBooking(booking);
        logger.info("Booking created with ID: " + booking.getBID());
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable int id, @RequestBody Booking booking) {
        Booking updatedBooking = bookingService.updateBooking(id, booking);
        if (updatedBooking != null) {
            return ResponseEntity.ok(updatedBooking);
        } else {
            logger.warning("Booking not found with ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
        logger.info("Booking deleted with ID: " + id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/regNo/{regNo}")
    public ResponseEntity<List<Booking>> getBookingsByRegNo(@PathVariable String regNo) {
        List<Booking> bookings = bookingService.getBookingsByRegNo(regNo);
        if (!bookings.isEmpty()) {
            return ResponseEntity.ok(bookings);
        } else {
            logger.warning("No bookings found for Registration Number: " + regNo);
            return ResponseEntity.notFound().build();
        }
    }

}
