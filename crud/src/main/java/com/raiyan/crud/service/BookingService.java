package com.raiyan.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.raiyan.crud.model.Booking;
import com.raiyan.crud.repository.BookingRepository;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.getAllBookings();
    }

    public Booking getBookingById(int id) {
        return bookingRepository.getBookingById(id);
    }

    public Booking createBooking(Booking booking) {
        bookingRepository.saveBooking(booking);
        return booking;
    }

    public Booking updateBooking(int id, Booking booking) {
        Booking existingBooking = bookingRepository.getBookingById(id);
        if (existingBooking != null) {
            bookingRepository.updateBooking(id, booking);
            return booking;
        } else {
            throw new RuntimeException("Booking not found with ID: " + id);
        }
    }

    public void deleteBooking(int id) {
        bookingRepository.deleteBooking(id);
    }

    public List<Booking> getBookingsByRegNo(String regNo) {
        return bookingRepository.getBookingsByRegNo(regNo);
    }
}
