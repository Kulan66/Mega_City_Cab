package com.example.mega_city_cab.dao;

import com.example.mega_city_cab.model.Car;
import com.example.mega_city_cab.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    public void addCar(Car car) throws SQLException {
        String query = "INSERT INTO car (model, license_plate, availability) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, car.getModel());
            stmt.setString(2, car.getLicensePlate());
            stmt.setBoolean(3, car.isAvailability());
            stmt.executeUpdate();
        }
    }

    public Car getCar(int carID) throws SQLException {
        String query = "SELECT * FROM car WHERE car_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, carID);
            ResultSet rs = stmt.executeQuery();

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

    public List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM car";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Car car = new Car();
                car.setCarID(rs.getInt("car_id"));
                car.setModel(rs.getString("model"));
                car.setLicensePlate(rs.getString("license_plate"));
                car.setAvailability(rs.getBoolean("availability"));
                cars.add(car);
            }
        }
        return cars;
    }

    public void updateCar(Car car) throws SQLException {
        String query = "UPDATE car SET model = ?, license_plate = ?, availability = ? WHERE car_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, car.getModel());
            stmt.setString(2, car.getLicensePlate());
            stmt.setBoolean(3, car.isAvailability());
            stmt.setInt(4, car.getCarID());
            stmt.executeUpdate();
        }
    }

    public void deleteCar(int carID) throws SQLException {
        String query = "DELETE FROM car WHERE car_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, carID);
            stmt.executeUpdate();
        }
    }

    public Car getCarByModel(String model) throws SQLException {
        String query = "SELECT * FROM car WHERE model = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, model);
            ResultSet rs = stmt.executeQuery();

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