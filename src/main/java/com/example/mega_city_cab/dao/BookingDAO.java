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
        String query = "INSERT INTO booking (customer_id, driver_id, car_id, destination, payment_method, distance_km, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, booking.getCustomerId());
            stmt.setInt(2, booking.getDriverId());
            stmt.setInt(3, booking.getCarId());
            stmt.setString(4, booking.getDestination());
            stmt.setString(5, booking.getPaymentMethod());
            stmt.setDouble(6, booking.getDistanceKm());
            stmt.setDouble(7, booking.getTotalPrice());
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
                return new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("driver_id"),
                        rs.getInt("car_id"),
                        rs.getString("destination"),
                        rs.getString("payment_method"),
                        rs.getDouble("distance_km"),
                        rs.getDouble("total_price"),
                        rs.getTimestamp("booking_date")
                );
            }
        }
        return null;
    }

    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM booking";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("driver_id"),
                        rs.getInt("car_id"),
                        rs.getString("destination"),
                        rs.getString("payment_method"),
                        rs.getDouble("distance_km"),
                        rs.getDouble("total_price"),
                        rs.getTimestamp("booking_date")
                ));
            }
        }
        return bookings;
    }

    public List<Booking> getBookingsByCustomerId(int customerId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM booking WHERE customer_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("driver_id"),
                        rs.getInt("car_id"),
                        rs.getString("destination"),
                        rs.getString("payment_method"),
                        rs.getDouble("distance_km"),
                        rs.getDouble("total_price"),
                        rs.getTimestamp("booking_date")
                ));
            }
        }
        return bookings;
    }

    public void deleteBooking(int bookingID) throws SQLException {
        String query = "DELETE FROM booking WHERE booking_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookingID);
            stmt.executeUpdate();
        }
    }
}