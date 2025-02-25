package com.example.mega_city_cab.servlet;

import com.example.mega_city_cab.model.Admin;
import com.example.mega_city_cab.model.Customer;
import com.example.mega_city_cab.service.AdminService;
import com.example.mega_city_cab.service.CustomerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private AdminService adminService;
    private CustomerService customerService;

    @Override
    public void init() {
        adminService = new AdminService();
        customerService = new CustomerService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailOrUsername = request.getParameter("emailOrUsername");
        String password = request.getParameter("password");

        try {
            Admin admin = adminService.authenticateAdmin(emailOrUsername, password);
            if (admin != null) {
                request.getSession().setAttribute("admin", admin);
                response.sendRedirect("admin.jsp");
            } else {
                Customer customer = customerService.authenticateCustomer(emailOrUsername, password);
                if (customer != null) {
                    request.getSession().setAttribute("pendingCustomer", customer);
                    response.sendRedirect("otp_verification.jsp?email=" + emailOrUsername);
                } else {
                    response.sendRedirect("login.jsp?error=Invalid email/username or password");
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}