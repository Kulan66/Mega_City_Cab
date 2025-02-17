<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mega_city_cab.model.Car" %>
<%@ page import="com.example.mega_city_cab.service.CarService" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Cars</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Manage Cars</h1>
<%
    CarService carService = new CarService();
    List<Car> cars = carService.getAllCars();
%>
<table border="1">
    <thead>
    <tr>
        <th>Car ID</th>
        <th>Model</th>
        <th>License Plate</th>
        <th>Availability</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Car car : cars) {
    %>
    <tr>
        <td><%= car.getCarID() %></td>
        <td><%= car.getModel() %></td>
        <td><%= car.getLicensePlate() %></td>
        <td><%= car.isAvailability() %></td>
        <td>
            <form action="editCar.jsp" method="get" style="display:inline;">
                <input type="hidden" name="carID" value="<%= car.getCarID() %>">
                <button type="submit">Edit</button>
            </form>
            <form action="car" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="carID" value="<%= car.getCarID() %>">
                <button type="submit" onclick="return confirm('Are you sure you want to delete this car?');">Delete</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<br>
<a href="addcar.jsp">Add New Car</a>
</body>
</html>