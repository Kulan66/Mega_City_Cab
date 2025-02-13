<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.mega_city_cab.model.Car" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Cars</title>
</head>
<body>
<h1>Manage Cars</h1>
<form action="car" method="post">
    <input type="hidden" name="action" value="add">
    <label for="add-model">Model:</label>
    <input type="text" id="add-model" name="model" required>
    <br/>
    <label for="add-licensePlate">License Plate:</label>
    <input type="text" id="add-licensePlate" name="licensePlate" required>
    <br/>
    <label for="add-availability">Availability:</label>
    <select id="add-availability" name="availability">
        <option value="true">Available</option>
        <option value="false">Not Available</option>
    </select>
    <br/>
    <button type="submit">Add Car</button>
</form>

<h2>Existing Cars</h2>
<table border="1">
    <tr>
        <th>Car ID</th>
        <th>Model</th>
        <th>License Plate</th>
        <th>Availability</th>
        <th>Actions</th>
    </tr>
    <%
        List<Car> cars = (List<Car>) request.getAttribute("cars");
        if (cars != null) {
            for (Car car : cars) {
    %>
    <tr>
        <td><%= car.getCarID() %></td>
        <td><%= car.getModel() %></td>
        <td><%= car.getLicensePlate() %></td>
        <td><%= car.isAvailability() %></td>
        <td>
            <form action="car" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="carID" value="<%= car.getCarID() %>">
                <button type="submit">Delete</button>
            </form>
            <form action="car" method="post" style="display:inline;">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="carID" value="<%= car.getCarID() %>">
                <label for="update-model-<%= car.getCarID() %>">Model:</label>
                <input type="text" id="update-model-<%= car.getCarID() %>" name="model" value="<%= car.getModel() %>" required>
                <label for="update-licensePlate-<%= car.getCarID() %>">License Plate:</label>
                <input type="text" id="update-licensePlate-<%= car.getCarID() %>" name="licensePlate" value="<%= car.getLicensePlate() %>" required>
                <label for="update-availability-<%= car.getCarID() %>">Availability:</label>
                <select id="update-availability-<%= car.getCarID() %>" name="availability">
                    <option value="true" <%= car.isAvailability() ? "selected" : "" %>>Available</option>
                    <option value="false" <%= !car.isAvailability() ? "selected" : "" %>>Not Available</option>
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