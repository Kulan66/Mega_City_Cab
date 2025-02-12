package com.example.mega_city_cab.servlet;

import com.example.mega_city_cab.model.Car;
import com.example.mega_city_cab.model.Driver;
import com.example.mega_city_cab.service.CarService;
import com.example.mega_city_cab.service.DriverService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DriverServlet", urlPatterns = {"/driver"})
public class DriverServlet extends HttpServlet {
    private DriverService driverService;
    private CarService carService;

    @Override
    public void init() {
        driverService = new DriverService();
        carService = new CarService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    addDriver(request, response);
                    break;
                case "update":
                    updateDriver(request, response);
                    break;
                case "delete":
                    deleteDriver(request, response);
                    break;
                default:
                    response.sendRedirect("managedriver.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addDriver(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        String licenseNumber = request.getParameter("licenseNumber");
        int carID = Integer.parseInt(request.getParameter("carID"));
        boolean availability = Boolean.parseBoolean(request.getParameter("availability"));

        Car car = carService.getCar(carID);
        Driver driver = new Driver();
        driver.setName(name);
        driver.setContact(contact);
        driver.setLicenseNumber(licenseNumber);
        driver.setCar(car);
        driver.setAvailability(availability);

        driverService.addDriver(driver);
        response.sendRedirect("managedriver.jsp");
    }

    private void updateDriver(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int driverID = Integer.parseInt(request.getParameter("driverID"));
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        String licenseNumber = request.getParameter("licenseNumber");
        int carID = Integer.parseInt(request.getParameter("carID"));
        boolean availability = Boolean.parseBoolean(request.getParameter("availability"));

        Car car = carService.getCar(carID);
        Driver driver = new Driver();
        driver.setDriverID(driverID);
        driver.setName(name);
        driver.setContact(contact);
        driver.setLicenseNumber(licenseNumber);
        driver.setCar(car);
        driver.setAvailability(availability);

        driverService.updateDriver(driver);
        response.sendRedirect("managedriver.jsp");
    }

    private void deleteDriver(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int driverID = Integer.parseInt(request.getParameter("driverID"));
        driverService.deleteDriver(driverID);
        response.sendRedirect("managedriver.jsp");
    }
}