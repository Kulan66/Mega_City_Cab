package com.example.mega_city_cab.service;

import com.example.mega_city_cab.dao.DriverDAO;
import com.example.mega_city_cab.model.Driver;

import java.sql.SQLException;
import java.util.List;

public class DriverService {
    private DriverDAO driverDAO;

    public DriverService() {
        this.driverDAO = new DriverDAO();
    }

    public void addDriver(Driver driver) throws SQLException {
        driverDAO.addDriver(driver);
    }

    public Driver getDriver(int driverID) throws SQLException {
        return driverDAO.getDriver(driverID);
    }

    public List<Driver> getAllDrivers() throws SQLException {
        return driverDAO.getAllDrivers();
    }

    public void updateDriver(Driver driver) throws SQLException {
        driverDAO.updateDriver(driver);
    }

    public void deleteDriver(int driverID) throws SQLException {
        driverDAO.deleteDriver(driverID);
    }

    public Driver getDriverByName(String name) throws SQLException {
        return driverDAO.getDriverByName(name);
    }
}