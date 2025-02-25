package com.example.mega_city_cab.dao;

import com.example.mega_city_cab.model.Help;
import com.example.mega_city_cab.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelpDAO {

    public void addGuideline(Help help) throws SQLException {
        String query = "INSERT INTO help (guideline) VALUES (?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, help.getGuideline());
            stmt.executeUpdate();
        }
    }

    public void updateGuideline(Help help) throws SQLException {
        String query = "UPDATE help SET guideline = ? WHERE help_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, help.getGuideline());
            stmt.setInt(2, help.getHelpID());
            stmt.executeUpdate();
        }
    }

    public void deleteGuideline(int helpID) throws SQLException {
        String query = "DELETE FROM help WHERE help_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, helpID);
            stmt.executeUpdate();
        }
    }

    public List<Help> getAllGuidelines() throws SQLException {
        List<Help> guidelines = new ArrayList<>();
        String query = "SELECT * FROM help";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Help help = new Help();
                help.setHelpID(rs.getInt("help_id"));
                help.setGuideline(rs.getString("guideline"));
                guidelines.add(help);
            }
        }
        return guidelines;
    }

    public Help getHelp(int helpID) throws SQLException {
        String query = "SELECT * FROM help WHERE help_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, helpID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Help help = new Help();
                help.setHelpID(rs.getInt("help_id"));
                help.setGuideline(rs.getString("guideline"));
                return help;
            }
            return null;
        }
    }
}