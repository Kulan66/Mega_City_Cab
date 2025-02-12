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
        String userType = request.getParameter("userType");
        String emailOrUsername = request.getParameter("emailOrUsername");
        String password = request.getParameter("password");

        try {
            if ("admin".equals(userType)) {
                loginAdmin(request, response, emailOrUsername, password);
            } else if ("customer".equals(userType)) {
                loginCustomer(request, response, emailOrUsername, password);
            } else {
                response.sendRedirect("login.jsp?error=Invalid user type");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void loginAdmin(HttpServletRequest request, HttpServletResponse response, String username, String password) throws SQLException, IOException {
        Admin admin = adminService.authenticateAdmin(username, password);
        if (admin != null) {
            request.getSession().setAttribute("admin", admin);
            response.sendRedirect("admin.jsp");
        } else {
            response.sendRedirect("login.jsp?error=Invalid username or password");
        }
    }

    private void loginCustomer(HttpServletRequest request, HttpServletResponse response, String email, String password) throws SQLException, IOException {
        Customer customer = customerService.authenticateCustomer(email, password);
        if (customer != null) {
            request.getSession().setAttribute("customer", customer);
            response.sendRedirect("customer.jsp");
        } else {
            response.sendRedirect("login.jsp?error=Invalid email or password");
        }
    }
}