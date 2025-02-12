package com.example.mega_city_cab.servlet;

import com.example.mega_city_cab.model.Booking;
import com.example.mega_city_cab.model.Customer;
import com.example.mega_city_cab.model.Driver;
import com.example.mega_city_cab.model.Car;
import com.example.mega_city_cab.service.BookingService;
import com.example.mega_city_cab.service.CustomerService;
import com.example.mega_city_cab.service.DriverService;
import com.example.mega_city_cab.service.CarService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "UpdateBooking", urlPatterns = {"/updateBooking"})
public class UpdateBooking extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        String customerName = request.getParameter("customer");
        String driverName = request.getParameter("driver");
        String carModel = request.getParameter("car");
        String destination = request.getParameter("destination");
        String paymentMethod = request.getParameter("paymentMethod");
        double distanceKm = Double.parseDouble(request.getParameter("distanceKm"));
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        String bookingDateString = request.getParameter("bookingDate");

        try {
            Customer customer = customerService.getCustomerByName(customerName);
            Driver driver = driverService.getDriverByName(driverName);
            Car car = carService.getCarByModel(carModel);
            Date bookingDate = new SimpleDateFormat("yyyy-MM-dd").parse(bookingDateString);

            Booking booking = new Booking();
            booking.setBookingID(bookingID);
            booking.setCustomer(customer);
            booking.setDriver(driver);
            booking.setCar(car);
            booking.setDestination(destination);
            booking.setPaymentMethod(paymentMethod);
            booking.setDistanceKm(distanceKm);
            booking.setTotalPrice(totalPrice);
            booking.setBookingDate(bookingDate);

            bookingService.updateBooking(booking);
            response.sendRedirect("managebooking.jsp");
        } catch (SQLException | ParseException e) {
            throw new ServletException("Error updating booking", e);
        }
    }
}