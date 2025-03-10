package com.example.mega_city_cab.service;

import com.example.mega_city_cab.dao.CustomerDAO;
import com.example.mega_city_cab.model.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomerService {
    private CustomerDAO customerDAO;
    private OtpService otpService;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
        this.otpService = new OtpService();
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void setOtpService(OtpService otpService) {
        this.otpService = otpService;
    }

    public void addCustomer(Customer customer) throws SQLException {
        validateCustomerDetails(customer);
        if (customerDAO.getCustomerByEmail(customer.getEmail()) != null) {
            throw new SQLException("Email already in use.");
        }
        customer.setPassword(hashPassword(customer.getPassword()));
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
        validateCustomerDetails(customer);
        customer.setPassword(hashPassword(customer.getPassword()));
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int customerID) throws SQLException {
        customerDAO.deleteCustomer(customerID);
    }

    public Customer authenticateCustomer(String email, String password) throws SQLException {
        Customer customer = customerDAO.getCustomerByEmailAndPassword(email, hashPassword(password));
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

    private void validateCustomerDetails(Customer customer) throws SQLException {
        if (!Pattern.matches("\\d{10}", customer.getPhone())) {
            throw new SQLException("Invalid phone number. It must be 10 digits.");
        }
        if (!Pattern.matches("\\d{12}", customer.getNic())) {
            throw new SQLException("Invalid NIC. It must be 12 digits.");
        }
        if (!isValidGmailAddress(customer.getEmail())) {
            throw new SQLException("Invalid email address. It must be a valid gmail.com address.");
        }
    }

    private boolean isValidGmailAddress(String email) {
        return email.endsWith("@gmail.com");
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}