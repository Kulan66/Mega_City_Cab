package com.example.mega_city_cab.servlet;

import com.example.mega_city_cab.model.Car;
import com.example.mega_city_cab.service.CarService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CarServlet", urlPatterns = {"/car"})
public class CarServlet extends HttpServlet {
    private CarService carService;

    @Override
    public void init() {
        carService = new CarService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Car> cars = carService.getAllCars();
            request.setAttribute("cars", cars);
            request.getRequestDispatcher("managecar.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    addCar(request, response);
                    break;
                case "update":
                    updateCar(request, response);
                    break;
                case "delete":
                    deleteCar(request, response);
                    break;
                default:
                    response.sendRedirect("managecar.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addCar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String model = request.getParameter("model");
        String licensePlate = request.getParameter("licensePlate");
        boolean availability = Boolean.parseBoolean(request.getParameter("availability"));

        Car car = new Car();
        car.setModel(model);
        car.setLicensePlate(licensePlate);
        car.setAvailability(availability);

        carService.addCar(car);
        response.sendRedirect("car");
    }

    private void updateCar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int carID = Integer.parseInt(request.getParameter("carID"));
        String model = request.getParameter("model");
        String licensePlate = request.getParameter("licensePlate");
        boolean availability = Boolean.parseBoolean(request.getParameter("availability"));

        Car car = new Car();
        car.setCarID(carID);
        car.setModel(model);
        car.setLicensePlate(licensePlate);
        car.setAvailability(availability);

        carService.updateCar(car);
        response.sendRedirect("car");
    }

    private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int carID = Integer.parseInt(request.getParameter("carID"));
        carService.deleteCar(carID);
        response.sendRedirect("car");
    }
}