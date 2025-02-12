package com.example.mega_city_cab.servlet;

import com.example.mega_city_cab.service.BookingService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteBooking", urlPatterns = {"/deleteBooking"})
public class DeleteBooking extends HttpServlet {
    private BookingService bookingService;

    @Override
    public void init() {
        bookingService = new BookingService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookingID = request.getParameter("bookingID");

        try {
            bookingService.deleteBooking(Integer.parseInt(bookingID));
            response.sendRedirect("managebooking.jsp");
        } catch (SQLException e) {
            throw new ServletException("Error deleting booking", e);
        }
    }
}