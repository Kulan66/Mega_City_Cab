package com.example.mega_city_cab.servlet;

import com.example.mega_city_cab.service.CustomerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteCustomer", urlPatterns = {"/deleteCustomer"})
public class DeleteCustomer extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() {
        customerService = new CustomerService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerID = request.getParameter("customerID");

        try {
            customerService.deleteCustomer(Integer.parseInt(customerID));
            response.sendRedirect("managecustomer.jsp");
        } catch (SQLException e) {
            throw new ServletException("Error deleting customer", e);
        }
    }
}