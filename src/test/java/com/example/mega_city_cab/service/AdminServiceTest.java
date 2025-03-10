package com.example.mega_city_cab.service;

import com.example.mega_city_cab.dao.AdminDAO;
import com.example.mega_city_cab.model.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest {

    private AdminService adminService;
    private AdminDAO adminDAO;
    private Admin admin;

    @BeforeEach
    void setUp() {
        adminDAO = new AdminDAO() {
            private List<Admin> admins = new ArrayList<>();

            @Override
            public Admin getAdminByUsernameAndPassword(String username, String password) throws SQLException {
                return admins.stream()
                        .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
                        .findFirst()
                        .orElse(null);
            }

            @Override
            public void addAdmin(Admin admin) throws SQLException {
                admins.add(admin);
            }

            @Override
            public void updateAdmin(Admin admin) throws SQLException {
                int index = admins.indexOf(getAdminByUsernameAndPassword(admin.getUsername(), admin.getPassword()));
                if (index >= 0) {
                    admins.set(index, admin);
                }
            }

            @Override
            public void deleteAdmin(int adminID) throws SQLException {
                admins.removeIf(a -> a.getAdminID() == adminID);
            }
        };
        adminService = new AdminService(adminDAO);

        admin = new Admin();
        admin.setAdminID(1);
        admin.setUsername("admin");
        admin.setPassword("password");
    }

    @Test
    void authenticateAdmin() throws SQLException {
        adminService.addAdmin(admin);
        Admin authenticatedAdmin = adminService.authenticateAdmin("admin", "password");
        assertNotNull(authenticatedAdmin);
        assertEquals(admin.getAdminID(), authenticatedAdmin.getAdminID());
    }

    @Test
    void addAdmin() throws SQLException {
        adminService.addAdmin(admin);
        Admin retrievedAdmin = adminService.authenticateAdmin("admin", "password");
        assertNotNull(retrievedAdmin);
        assertEquals(admin.getAdminID(), retrievedAdmin.getAdminID());
    }

    @Test
    void updateAdmin() throws SQLException {
        adminService.addAdmin(admin);
        admin.setPassword("newpassword");
        adminService.updateAdmin(admin);
        Admin updatedAdmin = adminService.authenticateAdmin("admin", "newpassword");
        assertNotNull(updatedAdmin);
        assertEquals(admin.getAdminID(), updatedAdmin.getAdminID());
    }

    @Test
    void deleteAdmin() throws SQLException {
        adminService.addAdmin(admin);
        adminService.deleteAdmin(1);
        Admin deletedAdmin = adminService.authenticateAdmin("admin", "password");
        assertNull(deletedAdmin);
    }
}