package com.example.mega_city_cab.service;

import com.example.mega_city_cab.dao.AdminDAO;
import com.example.mega_city_cab.model.Admin;

import java.sql.SQLException;

public class AdminService {
    private AdminDAO adminDAO;

    public AdminService() {
        this.adminDAO = new AdminDAO();
    }

    public Admin authenticateAdmin(String username, String password) throws SQLException {
        return adminDAO.getAdminByUsernameAndPassword(username, password);
    }

    public void addAdmin(Admin admin) throws SQLException {
        adminDAO.addAdmin(admin);
    }

    public void updateAdmin(Admin admin) throws SQLException {
        adminDAO.updateAdmin(admin);
    }

    public void deleteAdmin(int adminID) throws SQLException {
        adminDAO.deleteAdmin(adminID);
    }
}