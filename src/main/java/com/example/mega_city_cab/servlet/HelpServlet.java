package com.example.mega_city_cab.servlet;

import com.example.mega_city_cab.model.Help;
import com.example.mega_city_cab.service.HelpService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "HelpServlet", urlPatterns = {"/help"})
public class HelpServlet extends HttpServlet {
    private HelpService helpService;

    @Override
    public void init() {
        helpService = new HelpService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Help> guidelines = helpService.getAllGuidelines();
            request.setAttribute("guidelines", guidelines);
            request.getRequestDispatcher("help.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    addGuideline(request, response);
                    break;
                case "update":
                    updateGuideline(request, response);
                    break;
                case "delete":
                    deleteGuideline(request, response);
                    break;
                default:
                    response.sendRedirect("helpmanage.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addGuideline(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String guideline = request.getParameter("guideline");

        Help help = new Help();
        help.setGuideline(guideline);

        helpService.addGuideline(help);
        response.sendRedirect("helpmanage.jsp");
    }

    private void updateGuideline(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int helpID = Integer.parseInt(request.getParameter("helpID"));
        String guideline = request.getParameter("guideline");

        Help help = new Help();
        help.setHelpID(helpID);
        help.setGuideline(guideline);

        helpService.updateGuideline(help);
        response.sendRedirect("helpmanage.jsp");
    }

    private void deleteGuideline(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int helpID = Integer.parseInt(request.getParameter("helpID"));
        helpService.deleteGuideline(helpID);
        response.sendRedirect("helpmanage.jsp");
    }
}