package com.example.mega_city_cab.service;

import com.example.mega_city_cab.dao.DriverDAO;
import com.example.mega_city_cab.model.Car;
import com.example.mega_city_cab.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverServiceTest {

    private DriverService driverService;
    private DriverDAO driverDAO;
    private Driver driver;
    private Car car;

    @BeforeEach
    void setUp() {
        driverDAO = new DriverDAO() {
            private List<Driver> drivers = new ArrayList<>();

            @Override
            public void addDriver(Driver driver) throws SQLException {
                drivers.add(driver);
            }

            @Override
            public Driver getDriver(int driverID) throws SQLException {
                return drivers.stream()
                        .filter(d -> d.getDriverID() == driverID)
                        .findFirst()
                        .orElse(null);
            }

            @Override
            public List<Driver> getAllDrivers() throws SQLException {
                return new ArrayList<>(drivers);
            }

            @Override
            public void updateDriver(Driver driver) throws SQLException {
                int index = drivers.indexOf(getDriver(driver.getDriverID()));
                if (index >= 0) {
                    drivers.set(index, driver);
                }
            }

            @Override
            public void deleteDriver(int driverID) throws SQLException {
                drivers.removeIf(d -> d.getDriverID() == driverID);
            }
        };
        driverService = new DriverService();
        driverService.setDriverDAO(driverDAO);

        car = new Car();
        car.setCarID(1);
        car.setModel("Toyota");
        car.setLicensePlate("ABC123");
        car.setAvailability(true);

        driver = new Driver();
        driver.setDriverID(1);
        driver.setName("John Doe");
        driver.setContact("0123456789");
        driver.setLicenseNumber("LIC12345");
        driver.setCar(car);
        driver.setAvailability(true);
    }

    @Test
    void addDriver() throws SQLException {
        driverService.addDriver(driver);
        Driver retrievedDriver = driverService.getDriver(1);
        assertNotNull(retrievedDriver);
        assertEquals(driver.getDriverID(), retrievedDriver.getDriverID());
    }

    @Test
    void getDriver() throws SQLException {
        driverService.addDriver(driver);
        Driver retrievedDriver = driverService.getDriver(1);
        assertNotNull(retrievedDriver);
        assertEquals(driver.getDriverID(), retrievedDriver.getDriverID());
    }

    @Test
    void getAllDrivers() throws SQLException {
        driverService.addDriver(driver);
        List<Driver> retrievedDrivers = driverService.getAllDrivers();
        assertNotNull(retrievedDrivers);
        assertEquals(1, retrievedDrivers.size());
    }

    @Test
    void updateDriver() throws SQLException {
        driverService.addDriver(driver);
        driver.setName("Jane Doe");
        driverService.updateDriver(driver);
        Driver retrievedDriver = driverService.getDriver(1);
        assertNotNull(retrievedDriver);
        assertEquals("Jane Doe", retrievedDriver.getName());
    }

    @Test
    void deleteDriver() throws SQLException {
        driverService.addDriver(driver);
        driverService.deleteDriver(1);
        Driver retrievedDriver = driverService.getDriver(1);
        assertNull(retrievedDriver);
    }
}