package com.example.mega_city_cab.servlet;

import com.example.mega_city_cab.model.Booking;
import com.example.mega_city_cab.model.Car;
import com.example.mega_city_cab.model.Customer;
import com.example.mega_city_cab.model.Driver;
import com.example.mega_city_cab.service.BookingService;
import com.example.mega_city_cab.service.CarService;
import com.example.mega_city_cab.service.CustomerService;
import com.example.mega_city_cab.service.DriverService;
import com.example.mega_city_cab.service.BillCalculator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookingServlet", urlPatterns = {"/booking"})
public class BookingServlet extends HttpServlet {
    private BookingService bookingService;
    private CustomerService customerService;
    private DriverService driverService;
    private CarService carService;

    @Override
    public void init() {
        bookingService = new BookingService();
        customerService = new CustomerService();
        driverService = new DriverService();
        carService = new CarService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "prepareAdd":
                    prepareAddBooking(request, response);
                    break;
                default:
                    response.sendRedirect("customer.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void prepareAddBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Driver> drivers = driverService.getAllDrivers();
        request.setAttribute("drivers", drivers);
        request.getRequestDispatcher("addbooking.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    addBooking(request, response);
                    break;
                case "update":
                    updateBooking(request, response);
                    break;
                case "delete":
                    deleteBooking(request, response);
                    break;
                default:
                    response.sendRedirect("customer.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        int driverID = Integer.parseInt(request.getParameter("driverID"));
        int carID = Integer.parseInt(request.getParameter("carID"));
        String destination = request.getParameter("destination");
        String paymentMethod = request.getParameter("paymentMethod");
        double distanceKm = Double.parseDouble(request.getParameter("distanceKm"));
        double discountRate = Double.parseDouble(request.getParameter("discountRate"));

        Customer customer = customerService.getAllCustomers().stream().filter(c -> c.getCustomerID() == customerID).findFirst().orElse(null);
        Driver driver = driverService.getAllDrivers().stream().filter(d -> d.getDriverID() == driverID).findFirst().orElse(null);
        Car car = carService.getAllCars().stream().filter(c -> c.getCarID() == carID).findFirst().orElse(null);
        double pricePerKm = 100.0; // Price per km in LKR
        double taxPerKm = 5.5; // Tax per km in LKR
        double totalPrice = BillCalculator.calculateTotalPrice(distanceKm, pricePerKm, taxPerKm, discountRate);

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setDriver(driver);
        booking.setCar(car);
        booking.setDestination(destination);
        booking.setPaymentMethod(paymentMethod);
        booking.setDistanceKm(distanceKm);
        booking.setTotalPrice(totalPrice);
        booking.setDiscountRate(discountRate);

        bookingService.addBooking(booking);
        response.sendRedirect("customer.jsp");
    }

    private void updateBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        int driverID = Integer.parseInt(request.getParameter("driverID"));
        int carID = Integer.parseInt(request.getParameter("carID"));
        String destination = request.getParameter("destination");
        String paymentMethod = request.getParameter("paymentMethod");
        double distanceKm = Double.parseDouble(request.getParameter("distanceKm"));
        double discountRate = Double.parseDouble(request.getParameter("discountRate"));

        Customer customer = customerService.getAllCustomers().stream().filter(c -> c.getCustomerID() == customerID).findFirst().orElse(null);
        Driver driver = driverService.getAllDrivers().stream().filter(d -> d.getDriverID() == driverID).findFirst().orElse(null);
        Car car = carService.getAllCars().stream().filter(c -> c.getCarID() == carID).findFirst().orElse(null);
        double pricePerKm = 100.0; // Price per km in LKR
        double taxPerKm = 5.5; // Tax per km in LKR
        double totalPrice = BillCalculator.calculateTotalPrice(distanceKm, pricePerKm, taxPerKm, discountRate);

        Booking booking = new Booking();
        booking.setBookingID(bookingID);
        booking.setCustomer(customer);
        booking.setDriver(driver);
        booking.setCar(car);
        booking.setDestination(destination);
        booking.setPaymentMethod(paymentMethod);
        booking.setDistanceKm(distanceKm);
        booking.setTotalPrice(totalPrice);
        booking.setDiscountRate(discountRate);

        bookingService.updateBooking(booking);
        response.sendRedirect("customer.jsp");
    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        bookingService.deleteBooking(bookingID);
        response.sendRedirect("customer.jsp");
    }
}