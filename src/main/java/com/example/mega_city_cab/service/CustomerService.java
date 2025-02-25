package com.example.mega_city_cab.service;

import com.example.mega_city_cab.dao.CustomerDAO;
import com.example.mega_city_cab.model.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private CustomerDAO customerDAO;
    private OtpService otpService;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
        this.otpService = new OtpService();
    }

    public void addCustomer(Customer customer) throws SQLException {
        customerDAO.addCustomer(customer);
        otpService.generateOtp(customer.getEmail()); // Generate OTP when customer registers
    }

    public Customer getCustomer(int customerID) throws SQLException {
        return customerDAO.getCustomer(customerID);
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }

    public void updateCustomer(Customer customer) throws SQLException {
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int customerID) throws SQLException {
        customerDAO.deleteCustomer(customerID);
    }

    public Customer authenticateCustomer(String email, String password) throws SQLException {
        Customer customer = customerDAO.getCustomerByEmailAndPassword(email, password);
        if (customer != null) {
            otpService.generateOtp(email); // Generate OTP when customer logs in
        }
        return customer;
    }


    public Customer getCustomerByEmail(String email) throws SQLException {
        return customerDAO.getCustomerByEmail(email);
    }

    public boolean validateOtp(String email, String otp) {
        return otpService.validateOtp(email, otp);
    }

    public void clearOtp(String email) {
        otpService.clearOtp(email);
    }
}