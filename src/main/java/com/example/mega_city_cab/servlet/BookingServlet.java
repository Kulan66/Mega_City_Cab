package com.example.mega_city_cab.servlet;

import com.example.mega_city_cab.model.Booking;
import com.example.mega_city_cab.model.Customer;
import com.example.mega_city_cab.model.Driver;
import com.example.mega_city_cab.service.BookingService;
import com.example.mega_city_cab.service.CarService;
import com.example.mega_city_cab.service.CustomerService;
import com.example.mega_city_cab.service.DriverService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
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
            if ("prepareAdd".equals(action)) {
                prepareAddBooking(request, response);
            } else if ("getAll".equals(action)) {
                getAllBookings(request, response);
            } else if ("customerBookings".equals(action)) {
                getCustomerBookings(request, response);
            } else {
                response.sendRedirect("customer.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void prepareAddBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Driver> drivers = driverService.getAllDrivers();
        request.setAttribute("drivers", drivers); // Set drivers as a request attribute
        request.getRequestDispatcher("addbooking.jsp").forward(request, response);
    }

    private void getAllBookings(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Booking> bookings = bookingService.getAllBookings();
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("managebooking.jsp").forward(request, response);
    }

    private void getCustomerBookings(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        if (customer != null) {
            int customerId = customer.getCustomerID();
            List<Booking> bookings = bookingService.getBookingsByCustomerId(customerId);
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("viewbookings.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                addBooking(request, response);
            } else if ("delete".equals(action)) {
                deleteBooking(request, response);
            } else {
                response.sendRedirect("customer.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        int driverID = Integer.parseInt(request.getParameter("driverID"));
        String destination = request.getParameter("destination");
        String paymentMethod = request.getParameter("paymentMethod");
        double distanceKm = Double.parseDouble(request.getParameter("distanceKm"));
        double totalPrice = calculateTotalPrice(distanceKm); // Simplified for this example

        // Assuming you have a method to get the car ID by driver ID
        int carID = driverService.getDriver(driverID).getCar().getCarID();

        Booking booking = new Booking(0, customerID, driverID, carID, destination, paymentMethod, distanceKm, totalPrice, new java.sql.Timestamp(new Date().getTime()));
        bookingService.addBooking(booking);

        // Show popup with total bill details
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("distanceKm", distanceKm);
        request.getRequestDispatcher("billDetails.jsp").forward(request, response);
    }

    private double calculateTotalPrice(double distanceKm) {
        double pricePerKm = 100.0; // Price per km in LKR
        double taxPerKm = 5.5; // Tax per km in LKR
        return distanceKm * (pricePerKm + taxPerKm);
    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        bookingService.deleteBooking(bookingID);
        response.sendRedirect("booking?action=getAll");
    }
}