package com.example.mega_city_cab.service;

import com.example.mega_city_cab.dao.CustomerDAO;
import com.example.mega_city_cab.model.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
    }

    public void addCustomer(Customer customer) throws SQLException {
        customerDAO.addCustomer(customer);
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
        return customerDAO.getCustomerByEmailAndPassword(email, password);
    }

    public Customer getCustomerByName(String name) throws SQLException {
        return customerDAO.getCustomerByName(name);
    }
}