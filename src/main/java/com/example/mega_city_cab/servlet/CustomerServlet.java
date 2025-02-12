package com.example.mega_city_cab.servlet;

import com.example.mega_city_cab.model.Customer;
import com.example.mega_city_cab.service.CustomerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/customer"})
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() {
        customerService = new CustomerService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "register":
                    registerCustomer(request, response);
                    break;
                case "login":
                    loginCustomer(request, response);
                    break;
                case "update":
                    updateCustomer(request, response);
                    break;
                case "delete":
                    deleteCustomer(request, response);
                    break;
                default:
                    response.sendRedirect("customer.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void registerCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setNic(nic);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setPassword(password);

        customerService.addCustomer(customer);
        response.sendRedirect("login.jsp");
    }

    private void loginCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Customer customer = customerService.authenticateCustomer(email, password);
        if (customer != null) {
            request.getSession().setAttribute("customer", customer);
            response.sendRedirect("customer.jsp");
        } else {
            response.sendRedirect("login.jsp?error=Invalid email or password");
        }
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Customer customer = new Customer();
        customer.setCustomerID(customerID);
        customer.setName(name);
        customer.setAddress(address);
        customer.setNic(nic);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setPassword(password);

        customerService.updateCustomer(customer);
        response.sendRedirect("managecustomer.jsp");
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        customerService.deleteCustomer(customerID);
        response.sendRedirect("managecustomer.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}