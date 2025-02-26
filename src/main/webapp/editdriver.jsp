<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.mega_city_cab.model.Driver" %>
<%@ page import="com.example.mega_city_cab.service.DriverService" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Driver</title>
    <link rel="stylesheet" type="text/css" href="styles/editdriver.css">
</head>
<body>
<%
    int driverID = Integer.parseInt(request.getParameter("driverID"));
    DriverService driverService = new DriverService();
    Driver driver = driverService.getDriver(driverID);
%>
<h1>Edit Driver</h1>
<form action="driver" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="driverID" value="<%= driver.getDriverID() %>">
    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="<%= driver.getName() %>" required>
    </div>
    <div>
        <label for="contact">Contact:</label>
        <input type="text" id="contact" name="contact" value="<%= driver.getContact() %>" required>
    </div>
    <div>
        <label for="licenseNumber">License Number:</label>
        <input type="text" id="licenseNumber" name="licenseNumber" value="<%= driver.getLicenseNumber() %>" required>
    </div>
    <div>
        <label for="carID">Car ID:</label>
        <input type="number" id="carID" name="carID" value="<%= driver.getCar().getCarID() %>" required>
    </div>
    <div>
        <label for="availability">Availability:</label>
        <select id="availability" name="availability" required>
            <option value="true" <%= driver.isAvailability() ? "selected" : "" %>>Available</option>
            <option value="false" <%= !driver.isAvailability() ? "selected" : "" %>>Not Available</option>
        </select>
    </div>
    <button type="submit">Update Driver</button>
</form>
<br>
<a href="managedriver.jsp">Back to Manage Drivers</a>
</body>
</html>