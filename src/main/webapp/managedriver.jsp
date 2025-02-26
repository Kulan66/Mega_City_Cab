<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mega_city_cab.model.Driver" %>
<%@ page import="com.example.mega_city_cab.service.DriverService" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Drivers</title>
    <link rel="stylesheet" type="text/css" href="styles/managedriver.css">
</head>
<body>
<h1>Manage Drivers</h1>
<%
    DriverService driverService = new DriverService();
    List<Driver> drivers = driverService.getAllDrivers();
%>
<table border="1">
    <thead>
    <tr>
        <th>Driver ID</th>
        <th>Name</th>
        <th>Contact</th>
        <th>License Number</th>
        <th>Car</th>
        <th>Availability</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
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
            <form action="editdriver.jsp" method="get" style="display:inline;">
                <input type="hidden" name="driverID" value="<%= driver.getDriverID() %>">
                <button type="submit">Edit</button>
            </form>
            <form action="driver" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="driverID" value="<%= driver.getDriverID() %>">
                <button type="submit" onclick="return confirm('Are you sure you want to delete this driver?');">Delete</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<br>
<a href="adddriver.jsp">Add New Driver</a>
<a href="admin.jsp">Go to Dashboard</a>
</body>
</html>