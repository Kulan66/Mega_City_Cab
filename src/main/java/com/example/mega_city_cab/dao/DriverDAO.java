package com.example.mega_city_cab.dao;

import com.example.mega_city_cab.model.Driver;
import com.example.mega_city_cab.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {

    public void addDriver(Driver driver) throws SQLException {
        String query = "INSERT INTO driver (name, contact, license_number, car_id, availability) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getContact());
            stmt.setString(3, driver.getLicenseNumber());
            stmt.setInt(4, driver.getCar().getCarID());
            stmt.setBoolean(5, driver.isAvailability());
            stmt.executeUpdate();
        }
    }

    public Driver getDriver(int driverID) throws SQLException {
        String query = "SELECT * FROM driver WHERE driver_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, driverID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Driver driver = new Driver();
                driver.setDriverID(rs.getInt("driver_id"));
                driver.setName(rs.getString("name"));
                driver.setContact(rs.getString("contact"));
                driver.setLicenseNumber(rs.getString("license_number"));
                driver.setAvailability(rs.getBoolean("availability"));
                // You might need to fetch the Car object separately and set it here
                return driver;
            }
            return null;
        }
    }

    public List<Driver> getAllDrivers() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM driver";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Driver driver = new Driver();
                driver.setDriverID(rs.getInt("driver_id"));
                driver.setName(rs.getString("name"));
                driver.setContact(rs.getString("contact"));
                driver.setLicenseNumber(rs.getString("license_number"));
                driver.setAvailability(rs.getBoolean("availability"));
                // You might need to fetch the Car object separately and set it here
                drivers.add(driver);
            }
        }
        return drivers;
    }

    public void updateDriver(Driver driver) throws SQLException {
        String query = "UPDATE driver SET name = ?, contact = ?, license_number = ?, car_id = ?, availability = ? WHERE driver_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getContact());
            stmt.setString(3, driver.getLicenseNumber());
            stmt.setInt(4, driver.getCar().getCarID());
            stmt.setBoolean(5, driver.isAvailability());
            stmt.setInt(6, driver.getDriverID());
            stmt.executeUpdate();
        }
    }

    public void deleteDriver(int driverID) throws SQLException {
        String query = "DELETE FROM driver WHERE driver_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, driverID);
            stmt.executeUpdate();
        }
    }

    public Driver getDriverByName(String name) throws SQLException {
        String query = "SELECT * FROM driver WHERE name = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Driver driver = new Driver();
                driver.setDriverID(rs.getInt("driver_id"));
                driver.setName(rs.getString("name"));
                driver.setContact(rs.getString("contact"));
                driver.setLicenseNumber(rs.getString("license_number"));
                driver.setAvailability(rs.getBoolean("availability"));
                // You might need to fetch the Car object separately and set it here
                return driver;
            }
            return null;
        }
    }
}