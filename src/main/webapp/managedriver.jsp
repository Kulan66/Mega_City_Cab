<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.mega_city_cab.model.Driver" %>
<%@ page import="com.example.mega_city_cab.model.Car" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Drivers</title>
</head>
<body>
<h1>Manage Drivers</h1>
<form action="driver" method="post">
    <input type="hidden" name="action" value="add">
    <label for="add-name">Name:</label>
    <input type="text" id="add-name" name="name" required>
    <br/>
    <label for="add-contact">Contact:</label>
    <input type="text" id="add-contact" name="contact" required>
    <br/>
    <label for="add-licenseNumber">License Number:</label>
    <input type="text" id="add-licenseNumber" name="licenseNumber" required>
    <br/>
    <label for="add-carID">Car ID:</label>
    <input type="number" id="add-carID" name="carID" required>
    <br/>
    <label for="add-availability">Availability:</label>
    <select id="add-availability" name="availability">
        <option value="true">Available</option>
        <option value="false">Not Available</option>
    </select>
    <br/>
    <button type="submit">Add Driver</button>
</form>

<h2>Existing Drivers</h2>
<table border="1">
    <tr>
        <th>Driver ID</th>
        <th>Name</th>
        <th>Contact</th>
        <th>License Number</th>
        <th>Car</th>
        <th>Availability</th>
        <th>Actions</th>
    </tr>
    <%
        List<Driver> drivers = (List<Driver>) request.getAttribute("drivers");
        if (drivers != null) {
            for (Driver driver : drivers) {
    %>
    <tr>
        <td><%= driver.getDriverID() %></td>
        <td><%= driver.getName() %></td>
        <td><%= driver.getContact() %></td>
        <td><%= driver.getLicenseNumber() %></td>
        <td><%= driver.getCar().getModel() %></td>
        <td><%= driver.isAvailability() %></td>
        <td>
            <form action="driver" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="driverID" value="<%= driver.getDriverID() %>">
                <button type="submit">Delete</button>
            </form>
            <form action="driver" method="post" style="display:inline;">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="driverID" value="<%= driver.getDriverID() %>">
                <label for="update-name-<%= driver.getDriverID() %>">Name:</label>
                <input type="text" id="update-name-<%= driver.getDriverID() %>" name="name" value="<%= driver.getName() %>" required>
                <label for="update-contact-<%= driver.getDriverID() %>">Contact:</label>
                <input type="text" id="update-contact-<%= driver.getDriverID() %>" name="contact" value="<%= driver.getContact() %>" required>
                <label for="update-licenseNumber-<%= driver.getDriverID() %>">License Number:</label>
                <input type="text" id="update-licenseNumber-<%= driver.getDriverID() %>" name="licenseNumber" value="<%= driver.getLicenseNumber() %>" required>
                <label for="update-carID-<%= driver.getDriverID() %>">Car ID:</label>
                <input type="number" id="update-carID-<%= driver.getDriverID() %>" name="carID" value="<%= driver.getCar().getCarID() %>" required>
                <label for="update-availability-<%= driver.getDriverID() %>">Availability:</label>
                <select id="update-availability-<%= driver.getDriverID() %>" name="availability">
                    <option value="true" <%= driver.isAvailability() ? "selected" : "" %>>Available</option>
                    <option value="false" <%= !driver.isAvailability() ? "selected" : "" %>>Not Available</option>
                </select>
                <button type="submit">Update</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
<a href="admin.jsp">Go to Dashboard</a>
</body>
</html>