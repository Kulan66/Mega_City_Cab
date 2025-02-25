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
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            if ("manage".equals(action)) {
                showManagePage(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            if ("add".equals(action)) {
                addGuideline(request, response);
            } else if ("update".equals(action)) {
                updateGuideline(request, response);
            } else if ("delete".equals(action)) {
                deleteGuideline(request, response);
            } else {
                response.sendRedirect("help");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }



    private void showManagePage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Help> guidelines = helpService.getAllGuidelines();
        request.setAttribute("guidelines", guidelines);
        request.getRequestDispatcher("helpmanage.jsp").forward(request, response);
    }

    private void addGuideline(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String guideline = request.getParameter("guideline");

        Help help = new Help();
        help.setGuideline(guideline);

        helpService.addGuideline(help);
        response.sendRedirect("help?action=manage");
    }

    private void updateGuideline(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int helpID = Integer.parseInt(request.getParameter("helpID"));
        String guideline = request.getParameter("guideline");

        Help help = new Help();
        help.setHelpID(helpID);
        help.setGuideline(guideline);

        helpService.updateGuideline(help);
        response.sendRedirect("help?action=manage");
    }

    private void deleteGuideline(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int helpID = Integer.parseInt(request.getParameter("helpID"));
        helpService.deleteGuideline(helpID);
        response.sendRedirect("help?action=manage");
    }
}