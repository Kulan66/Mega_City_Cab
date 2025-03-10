package com.example.mega_city_cab.service;

import com.example.mega_city_cab.dao.CarDAO;
import com.example.mega_city_cab.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    private CarService carService;
    private CarDAO carDAO;
    private Car car;

    @BeforeEach
    void setUp() {
        carDAO = new CarDAO() {
            private List<Car> cars = new ArrayList<>();

            @Override
            public void addCar(Car car) throws SQLException {
                cars.add(car);
            }

            @Override
            public Car getCar(int carID) throws SQLException {
                return cars.stream()
                        .filter(c -> c.getCarID() == carID)
                        .findFirst()
                        .orElse(null);
            }

            @Override
            public List<Car> getAllCars() throws SQLException {
                return new ArrayList<>(cars);
            }

            @Override
            public void updateCar(Car car) throws SQLException {
                int index = cars.indexOf(getCar(car.getCarID()));
                if (index >= 0) {
                    cars.set(index, car);
                }
            }

            @Override
            public void deleteCar(int carID) throws SQLException {
                cars.removeIf(c -> c.getCarID() == carID);
            }
        };
        carService = new CarService();
        carService.setCarDAO(carDAO);

        car = new Car();
        car.setCarID(1);
        car.setModel("Toyota");
        car.setLicensePlate("ABC123");
        car.setAvailability(true);
    }

    @Test
    void addCar() throws SQLException {
        carService.addCar(car);
        Car retrievedCar = carService.getCar(1);
        assertNotNull(retrievedCar);
        assertEquals(car.getCarID(), retrievedCar.getCarID());
    }

    @Test
    void getCar() throws SQLException {
        carService.addCar(car);
        Car retrievedCar = carService.getCar(1);
        assertNotNull(retrievedCar);
        assertEquals(car.getCarID(), retrievedCar.getCarID());
    }

    @Test
    void getAllCars() throws SQLException {
        carService.addCar(car);
        List<Car> retrievedCars = carService.getAllCars();
        assertNotNull(retrievedCars);
        assertEquals(1, retrievedCars.size());
    }

    @Test
    void updateCar() throws SQLException {
        carService.addCar(car);
        car.setModel("Honda");
        carService.updateCar(car);
        Car retrievedCar = carService.getCar(1);
        assertNotNull(retrievedCar);
        assertEquals("Honda", retrievedCar.getModel());
    }

    @Test
    void deleteCar() throws SQLException {
        carService.addCar(car);
        carService.deleteCar(1);
        Car retrievedCar = carService.getCar(1);
        assertNull(retrievedCar);
    }
}