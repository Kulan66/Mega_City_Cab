package com.example.mega_city_cab.service;

import com.example.mega_city_cab.dao.CustomerDAO;
import com.example.mega_city_cab.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private CustomerService customerService;
    private CustomerDAO customerDAO;
    private Customer customer;
    private OtpService otpService;

    @BeforeEach
    void setUp() {
        customerDAO = new CustomerDAO() {
            private List<Customer> customers = new ArrayList<>();

            @Override
            public void addCustomer(Customer customer) throws SQLException {
                customers.add(customer);
            }

            @Override
            public Customer getCustomer(int customerID) throws SQLException {
                return customers.stream()
                        .filter(c -> c.getCustomerID() == customerID)
                        .findFirst()
                        .orElse(null);
            }

            @Override
            public List<Customer> getAllCustomers() throws SQLException {
                return new ArrayList<>(customers);
            }

            @Override
            public void updateCustomer(Customer customer) throws SQLException {
                int index = customers.indexOf(getCustomer(customer.getCustomerID()));
                if (index >= 0) {
                    customers.set(index, customer);
                }
            }

            @Override
            public void deleteCustomer(int customerID) throws SQLException {
                customers.removeIf(c -> c.getCustomerID() == customerID);
            }

            @Override
            public Customer getCustomerByEmailAndPassword(String email, String password) throws SQLException {
                return customers.stream()
                        .filter(c -> c.getEmail().equals(email) && c.getPassword().equals(password))
                        .findFirst()
                        .orElse(null);
            }

            @Override
            public Customer getCustomerByEmail(String email) throws SQLException {
                return customers.stream()
                        .filter(c -> c.getEmail().equals(email))
                        .findFirst()
                        .orElse(null);
            }
        };
        otpService = new OtpService() {
            private String generatedOtp;

            @Override
            public String generateOtp(String email) {
                generatedOtp = "123456"; // Simulate OTP generation
                return generatedOtp;
            }

            @Override
            public boolean validateOtp(String email, String otp) {
                return "123456".equals(otp); // Simulate OTP validation
            }

            @Override
            public void clearOtp(String email) {
                generatedOtp = null; // Simulate clearing OTP
            }
        };
        customerService = new CustomerService();
        customerService.setCustomerDAO(customerDAO);
        customerService.setOtpService(otpService);

        customer = new Customer();
        customer.setCustomerID(1);
        customer.setName("John Doe");
        customer.setAddress("123 Main St");
        customer.setNic("123456789012");
        customer.setPhone("0123456789");
        customer.setEmail("john.doe@gmail.com");
        customer.setPassword("password");
    }

    @Test
    void addCustomer() throws SQLException {
        customerService.addCustomer(customer);
        Customer retrievedCustomer = customerService.getCustomer(1);
        assertNotNull(retrievedCustomer);
        assertEquals(customer.getCustomerID(), retrievedCustomer.getCustomerID());
    }

    @Test
    void getCustomer() throws SQLException {
        customerService.addCustomer(customer);
        Customer retrievedCustomer = customerService.getCustomer(1);
        assertNotNull(retrievedCustomer);
        assertEquals(customer.getCustomerID(), retrievedCustomer.getCustomerID());
    }

    @Test
    void getAllCustomers() throws SQLException {
        customerService.addCustomer(customer);
        List<Customer> retrievedCustomers = customerService.getAllCustomers();
        assertNotNull(retrievedCustomers);
        assertEquals(1, retrievedCustomers.size());
    }

    @Test
    void updateCustomer() throws SQLException {
        customerService.addCustomer(customer);
        customer.setName("Jane Doe");
        customerService.updateCustomer(customer);
        Customer retrievedCustomer = customerService.getCustomer(1);
        assertNotNull(retrievedCustomer);
        assertEquals("Jane Doe", retrievedCustomer.getName());
    }

    @Test
    void deleteCustomer() throws SQLException {
        customerService.addCustomer(customer);
        customerService.deleteCustomer(1);
        Customer retrievedCustomer = customerService.getCustomer(1);
        assertNull(retrievedCustomer);
    }

    @Test
    void authenticateCustomer() throws SQLException {
        customerService.addCustomer(customer);
        Customer authenticatedCustomer = customerService.authenticateCustomer("john.doe@gmail.com", "password");
        assertNotNull(authenticatedCustomer);
        assertEquals(customer.getCustomerID(), authenticatedCustomer.getCustomerID());
    }

    @Test
    void getCustomerByEmail() throws SQLException {
        customerService.addCustomer(customer);
        Customer retrievedCustomer = customerService.getCustomerByEmail("john.doe@gmail.com");
        assertNotNull(retrievedCustomer);
        assertEquals(customer.getCustomerID(), retrievedCustomer.getCustomerID());
    }

    @Test
    void validateOtp() {
        otpService.generateOtp("john.doe@gmail.com");
        assertTrue(customerService.validateOtp("john.doe@gmail.com", "123456"));
    }


}