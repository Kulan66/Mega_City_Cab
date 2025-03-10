package com.example.mega_city_cab.service;

import com.example.mega_city_cab.dao.CarDAO;
import com.example.mega_city_cab.model.Car;

import java.sql.SQLException;
import java.util.List;

public class CarService {
    private CarDAO carDAO;

    public CarService() {
        this.carDAO = new CarDAO();
    }

    public void setCarDAO(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public void addCar(Car car) throws SQLException {
        carDAO.addCar(car);
    }

    public Car getCar(int carID) throws SQLException {
        return carDAO.getCar(carID);
    }

    public List<Car> getAllCars() throws SQLException {
        return carDAO.getAllCars();
    }

    public void updateCar(Car car) throws SQLException {
        carDAO.updateCar(car);
    }

    public void deleteCar(int carID) throws SQLException {
        carDAO.deleteCar(carID);
    }
}