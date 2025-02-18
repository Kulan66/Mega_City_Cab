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
import java.util.List;

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
                    response.sendRedirect("managebooking.jsp");
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
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));

        Booking booking = new Booking();
        booking.setCustomerID(customerID);
        booking.setDriverID(driverID);
        booking.setCarID(carID);
        booking.setDestination(destination);
        booking.setPaymentMethod(paymentMethod);
        booking.setDistanceKm(distanceKm);
        booking.setTotalPrice(totalPrice);
        booking.setBookingDate(new java.util.Date());

        bookingService.addBooking(booking);
        response.sendRedirect("viewbookings.jsp");
    }

    private void updateBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        int driverID = Integer.parseInt(request.getParameter("driverID"));
        int carID = Integer.parseInt(request.getParameter("carID"));
        String destination = request.getParameter("destination");
        String paymentMethod = request.getParameter("paymentMethod");
        double distanceKm = Double.parseDouble(request.getParameter("distanceKm"));
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));

        Booking booking = new Booking();
        booking.setBookingID(bookingID);
        booking.setCustomerID(customerID);
        booking.setDriverID(driverID);
        booking.setCarID(carID);
        booking.setDestination(destination);
        booking.setPaymentMethod(paymentMethod);
        booking.setDistanceKm(distanceKm);
        booking.setTotalPrice(totalPrice);
        booking.setBookingDate(new java.util.Date());

        bookingService.updateBooking(booking);
        response.sendRedirect("managebooking.jsp");
    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        bookingService.deleteBooking(bookingID);
        response.sendRedirect("managebooking.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "view":
                    getBooking(request, response);
                    break;
                case "viewByCustomer":
                    getBookingsByCustomer(request, response);
                    break;
                default:
                    listAllBookings(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void getBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        Booking booking = bookingService.getBooking(bookingID);
        request.setAttribute("booking", booking);
        request.getRequestDispatcher("viewBooking.jsp").forward(request, response);
    }

    private void getBookingsByCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        List<Booking> bookings = bookingService.getBookingsByCustomer(customerID);
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("viewbookings.jsp").forward(request, response);
    }

    private void listAllBookings(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Booking> bookings = bookingService.getAllBookings();
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("managebooking.jsp").forward(request, response);
    }
}