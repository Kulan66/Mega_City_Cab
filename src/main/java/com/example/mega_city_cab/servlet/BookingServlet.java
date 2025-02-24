package com.example.mega_city_cab.servlet;

import com.example.mega_city_cab.model.Booking;
import com.example.mega_city_cab.service.BookingService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "BookingServlet", urlPatterns = {"/booking"})
public class BookingServlet extends HttpServlet {
    private BookingService bookingService;

    @Override
    public void init() {
        bookingService = new BookingService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "calculate":
                    calculateBill(request, response);
                    break;
                case "confirm":
                    confirmBooking(request, response);
                    break;
                case "update":
                    updateBooking(request, response);
                    break;
                case "delete":
                    deleteBooking(request, response);
                    break;
                case "add":
                    addBooking(request, response);
                    break;
                default:
                    response.sendRedirect("managebooking.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }



    private void calculateBill(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        int driverID = Integer.parseInt(request.getParameter("driverID"));
        int carID = Integer.parseInt(request.getParameter("carID"));
        String destination = request.getParameter("destination");
        String paymentMethod = request.getParameter("paymentMethod");
        double distanceKm = Double.parseDouble(request.getParameter("distanceKm"));
        int discount = Integer.parseInt(request.getParameter("discount"));

        // Calculate base price and tax
        double basePrice = distanceKm * 100;
        double tax = distanceKm * 5;
        double totalPrice = basePrice + tax;

        // Apply discount
        if (discount > 0) {
            totalPrice -= totalPrice * (discount / 100.0);
        }

        Booking booking = new Booking();
        booking.setCustomerID(customerID);
        booking.setDriverID(driverID);
        booking.setCarID(carID);
        booking.setDestination(destination);
        booking.setPaymentMethod(paymentMethod);
        booking.setDistanceKm(distanceKm);
        booking.setBasePrice(basePrice);
        booking.setTax(tax);
        booking.setDiscount(discount);
        booking.setTotalPrice(totalPrice);
        booking.setBookingDate(new java.util.Date());

        request.setAttribute("booking", booking);
        request.getRequestDispatcher("billdetails.jsp").forward(request, response);
    }

    private void confirmBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        int driverID = Integer.parseInt(request.getParameter("driverID"));
        int carID = Integer.parseInt(request.getParameter("carID"));
        String destination = request.getParameter("destination");
        String paymentMethod = request.getParameter("paymentMethod");
        double distanceKm = Double.parseDouble(request.getParameter("distanceKm"));
        double basePrice = Double.parseDouble(request.getParameter("basePrice"));
        double tax = Double.parseDouble(request.getParameter("tax"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));

        Booking booking = new Booking();
        booking.setCustomerID(customerID);
        booking.setDriverID(driverID);
        booking.setCarID(carID);
        booking.setDestination(destination);
        booking.setPaymentMethod(paymentMethod);
        booking.setDistanceKm(distanceKm);
        booking.setBasePrice(basePrice);
        booking.setTax(tax);
        booking.setDiscount(discount);
        booking.setTotalPrice(totalPrice);
        booking.setBookingDate(new java.util.Date());

        bookingService.addBooking(booking);
        response.sendRedirect("customer.jsp");
    }

    private void updateBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        try {
            int bookingID = Integer.parseInt(request.getParameter("bookingID"));
            int customerID = Integer.parseInt(request.getParameter("customerID"));
            int driverID = Integer.parseInt(request.getParameter("driverID"));
            int carID = Integer.parseInt(request.getParameter("carID"));
            String destination = request.getParameter("destination");
            String paymentMethod = request.getParameter("paymentMethod");
            double distanceKm = Double.parseDouble(request.getParameter("distanceKm"));
            double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
            java.util.Date bookingDate = new java.util.Date();

            Booking booking = new Booking();
            booking.setBookingID(bookingID);
            booking.setCustomerID(customerID);
            booking.setDriverID(driverID);
            booking.setCarID(carID);
            booking.setDestination(destination);
            booking.setPaymentMethod(paymentMethod);
            booking.setDistanceKm(distanceKm);
            booking.setTotalPrice(totalPrice);
            booking.setBookingDate(bookingDate);

            bookingService.updateBooking(booking);
            response.sendRedirect("managebooking.jsp");
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid input format", e);
        }
    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        bookingService.deleteBooking(bookingID);
        response.sendRedirect("managebooking.jsp");
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        int driverID = Integer.parseInt(request.getParameter("driverID"));
        int carID = Integer.parseInt(request.getParameter("carID"));
        String destination = request.getParameter("destination");
        String paymentMethod = request.getParameter("paymentMethod");
        double distanceKm = Double.parseDouble(request.getParameter("distanceKm"));
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        java.util.Date bookingDate = new java.util.Date();

        Booking booking = new Booking();
        booking.setCustomerID(customerID);
        booking.setDriverID(driverID);
        booking.setCarID(carID);
        booking.setDestination(destination);
        booking.setPaymentMethod(paymentMethod);
        booking.setDistanceKm(distanceKm);
        booking.setTotalPrice(totalPrice);
        booking.setBookingDate(bookingDate);

        bookingService.addBooking(booking);
        response.sendRedirect("managebooking.jsp");
    }
}