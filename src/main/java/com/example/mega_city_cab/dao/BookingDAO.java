package com.example.mega_city_cab.dao;

import com.example.mega_city_cab.model.Booking;
import com.example.mega_city_cab.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public void addBooking(Booking booking) throws SQLException {
        String query = "INSERT INTO booking (customer_id, driver_id, car_id, destination, payment_method, distance_km, total_price, booking_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, booking.getCustomerID());
            stmt.setInt(2, booking.getDriverID());
            stmt.setInt(3, booking.getCarID());
            stmt.setString(4, booking.getDestination());
            stmt.setString(5, booking.getPaymentMethod());
            stmt.setDouble(6, booking.getDistanceKm());
            stmt.setDouble(7, booking.getTotalPrice());
            stmt.setTimestamp(8, new java.sql.Timestamp(booking.getBookingDate().getTime()));
            stmt.executeUpdate();
        }
    }

    public Booking getBooking(int bookingID) throws SQLException {
        String query = "SELECT * FROM booking WHERE booking_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookingID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingID(rs.getInt("booking_id"));
                booking.setCustomerID(rs.getInt("customer_id"));
                booking.setDriverID(rs.getInt("driver_id"));
                booking.setCarID(rs.getInt("car_id"));
                booking.setDestination(rs.getString("destination"));
                booking.setPaymentMethod(rs.getString("payment_method"));
                booking.setDistanceKm(rs.getDouble("distance_km"));
                booking.setTotalPrice(rs.getDouble("total_price"));
                booking.setBookingDate(rs.getTimestamp("booking_date"));
                return booking;
            }
            return null;
        }
    }

    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM booking";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingID(rs.getInt("booking_id"));
                booking.setCustomerID(rs.getInt("customer_id"));
                booking.setDriverID(rs.getInt("driver_id"));
                booking.setCarID(rs.getInt("car_id"));
                booking.setDestination(rs.getString("destination"));
                booking.setPaymentMethod(rs.getString("payment_method"));
                booking.setDistanceKm(rs.getDouble("distance_km"));
                booking.setTotalPrice(rs.getDouble("total_price"));
                booking.setBookingDate(rs.getTimestamp("booking_date"));
                bookings.add(booking);
            }
        }
        return bookings;
    }

    public void updateBooking(Booking booking) throws SQLException {
        String query = "UPDATE booking SET customer_id = ?, driver_id = ?, car_id = ?, destination = ?, payment_method = ?, distance_km = ?, total_price = ?, booking_date = ? WHERE booking_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, booking.getCustomerID());
            stmt.setInt(2, booking.getDriverID());
            stmt.setInt(3, booking.getCarID());
            stmt.setString(4, booking.getDestination());
            stmt.setString(5, booking.getPaymentMethod());
            stmt.setDouble(6, booking.getDistanceKm());
            stmt.setDouble(7, booking.getTotalPrice());
            stmt.setTimestamp(8, new java.sql.Timestamp(booking.getBookingDate().getTime()));
            stmt.setInt(9, booking.getBookingID());
            stmt.executeUpdate();
        }
    }

    public void deleteBooking(int bookingID) throws SQLException {
        String query = "DELETE FROM booking WHERE booking_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookingID);
            stmt.executeUpdate();
        }
    }

    public List<Booking> getBookingsByCustomer(int customerID) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM booking WHERE customer_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingID(rs.getInt("booking_id"));
                booking.setCustomerID(rs.getInt("customer_id"));
                booking.setDriverID(rs.getInt("driver_id"));
                booking.setCarID(rs.getInt("car_id"));
                booking.setDestination(rs.getString("destination"));
                booking.setPaymentMethod(rs.getString("payment_method"));
                booking.setDistanceKm(rs.getDouble("distance_km"));
                booking.setTotalPrice(rs.getDouble("total_price"));
                booking.setBookingDate(rs.getTimestamp("booking_date"));
                bookings.add(booking);
            }
        }
        return bookings;
    }
}