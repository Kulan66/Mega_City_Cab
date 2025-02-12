package com.example.mega_city_cab.dao;

import com.example.mega_city_cab.model.Booking;
import com.example.mega_city_cab.model.Car;
import com.example.mega_city_cab.model.Customer;
import com.example.mega_city_cab.model.Driver;
import com.example.mega_city_cab.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public void addBooking(Booking booking) throws SQLException {
        String query = "INSERT INTO booking (customer_id, driver_id, car_id, destination, payment_method, distance_km, total_price, discount_rate, booking_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, booking.getCustomer().getCustomerID());
            stmt.setInt(2, booking.getDriver().getDriverID());
            stmt.setInt(3, booking.getCar().getCarID());
            stmt.setString(4, booking.getDestination());
            stmt.setString(5, booking.getPaymentMethod());
            stmt.setDouble(6, booking.getDistanceKm());
            stmt.setDouble(7, booking.getTotalPrice());
            stmt.setDouble(8, booking.getDiscountRate());
            stmt.setDate(9, new java.sql.Date(booking.getBookingDate().getTime()));
            stmt.executeUpdate();
        }
    }

    public Booking getBooking(int bookingID) throws SQLException {
        String query = "SELECT * FROM booking WHERE booking_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookingID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBookingID(rs.getInt("booking_id"));
                    booking.setCustomer(getCustomerById(rs.getInt("customer_id")));
                    booking.setDriver(getDriverById(rs.getInt("driver_id")));
                    booking.setCar(getCarById(rs.getInt("car_id")));
                    booking.setDestination(rs.getString("destination"));
                    booking.setPaymentMethod(rs.getString("payment_method"));
                    booking.setDistanceKm(rs.getDouble("distance_km"));
                    booking.setTotalPrice(rs.getDouble("total_price"));
                    booking.setDiscountRate(rs.getDouble("discount_rate"));
                    booking.setBookingDate(rs.getDate("booking_date"));
                    return booking;
                }
                return null;
            }
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
                booking.setCustomer(getCustomerById(rs.getInt("customer_id")));
                booking.setDriver(getDriverById(rs.getInt("driver_id")));
                booking.setCar(getCarById(rs.getInt("car_id")));
                booking.setDestination(rs.getString("destination"));
                booking.setPaymentMethod(rs.getString("payment_method"));
                booking.setDistanceKm(rs.getDouble("distance_km"));
                booking.setTotalPrice(rs.getDouble("total_price"));
                booking.setDiscountRate(rs.getDouble("discount_rate"));
                booking.setBookingDate(rs.getDate("booking_date"));
                bookings.add(booking);
            }
        }
        return bookings;
    }

    public void updateBooking(Booking booking) throws SQLException {
        String query = "UPDATE booking SET customer_id = ?, driver_id = ?, car_id = ?, destination = ?, payment_method = ?, distance_km = ?, total_price = ?, discount_rate = ?, booking_date = ? WHERE booking_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, booking.getCustomer().getCustomerID());
            stmt.setInt(2, booking.getDriver().getDriverID());
            stmt.setInt(3, booking.getCar().getCarID());
            stmt.setString(4, booking.getDestination());
            stmt.setString(5, booking.getPaymentMethod());
            stmt.setDouble(6, booking.getDistanceKm());
            stmt.setDouble(7, booking.getTotalPrice());
            stmt.setDouble(8, booking.getDiscountRate());
            stmt.setDate(9, new java.sql.Date(booking.getBookingDate().getTime()));
            stmt.setInt(10, booking.getBookingID());
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

    private Customer getCustomerById(int customerID) throws SQLException {
        String query = "SELECT * FROM customer WHERE customer_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setNic(rs.getString("nic"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                return customer;
            }
            return null;
        }
    }

    private Driver getDriverById(int driverID) throws SQLException {
        String query = "SELECT * FROM driver WHERE driver_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Driver driver = new Driver();
                driver.setDriverID(rs.getInt("driver_id"));
                driver.setName(rs.getString("name"));
                driver.setContact(rs.getString("contact"));
                driver.setLicenseNumber(rs.getString("license_number"));
                driver.setAvailability(rs.getBoolean("availability"));
                return driver;
            }
            return null;
        }
    }

    private Car getCarById(int carID) throws SQLException {
        String query = "SELECT * FROM car WHERE car_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Car car = new Car();
                car.setCarID(rs.getInt("car_id"));
                car.setModel(rs.getString("model"));
                car.setLicensePlate(rs.getString("license_plate"));
                car.setAvailability(rs.getBoolean("availability"));
                return car;
            }
            return null;
        }
    }
}