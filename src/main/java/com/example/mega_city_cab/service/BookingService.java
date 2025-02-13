package com.example.mega_city_cab.service;

import com.example.mega_city_cab.dao.BookingDAO;
import com.example.mega_city_cab.model.Booking;

import java.sql.SQLException;
import java.util.List;

public class BookingService {
    private BookingDAO bookingDAO;

    public BookingService() {
        this.bookingDAO = new BookingDAO();
    }

    public void addBooking(Booking booking) throws SQLException {
        bookingDAO.addBooking(booking);
    }

    public Booking getBooking(int bookingID) throws SQLException {
        return bookingDAO.getBooking(bookingID);
    }

    public List<Booking> getAllBookings() throws SQLException {
        return bookingDAO.getAllBookings();
    }

    public void deleteBooking(int bookingID) throws SQLException {
        bookingDAO.deleteBooking(bookingID);
    }

    public List<Booking> getBookingsByCustomerId(int customerId) throws SQLException {
        return bookingDAO.getBookingsByCustomerId(customerId);
    }
}