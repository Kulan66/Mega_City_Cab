package com.example.mega_city_cab.servlet;

import com.example.mega_city_cab.model.Admin;
import com.example.mega_city_cab.service.AdminService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {
    private AdminService adminService;

    @Override
    public void init() {
        adminService = new AdminService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "login":
                    loginAdmin(request, response);
                    break;
                case "add":
                    addAdmin(request, response);
                    break;
                case "update":
                    updateAdmin(request, response);
                    break;
                case "delete":
                    deleteAdmin(request, response);
                    break;
                default:
                    response.sendRedirect("admin.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void loginAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Admin admin = adminService.authenticateAdmin(username, password);
        if (admin != null) {
            request.getSession().setAttribute("admin", admin);
            response.sendRedirect("admin.jsp");
        } else {
            response.sendRedirect("login.jsp?error=Invalid username or password");
        }
    }

    private void addAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);

        adminService.addAdmin(admin);
        response.sendRedirect("admin.jsp");
    }

    private void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int adminID = Integer.parseInt(request.getParameter("adminID"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Admin admin = new Admin();
        admin.setAdminID(adminID);
        admin.setUsername(username);
        admin.setPassword(password);

        adminService.updateAdmin(admin);
        response.sendRedirect("admin.jsp");
    }

    private void deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int adminID = Integer.parseInt(request.getParameter("adminID"));
        adminService.deleteAdmin(adminID);
        response.sendRedirect("admin.jsp");
    }
}