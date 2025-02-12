<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.mega_city_cab.model.Driver" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Booking</title>
</head>
<body>
<h1>Add Booking</h1>
<form action="booking" method="post">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="customerID" value="${sessionScope.customer.customerID}">
    <label for="destination">Destination:</label>
    <input type="text" id="destination" name="destination" required>
    <br/>
    <label for="paymentMethod">Payment Method:</label>
    <select id="paymentMethod" name="paymentMethod">
        <option value="cash">Cash</option>
        <option value="card">Card</option>
    </select>
    <br/>
    <label for="distanceKm">Distance (km):</label>
    <input type="number" id="distanceKm" name="distanceKm" required>
    <br/>
    <label for="discountRate">Discount Rate:</label>
    <input type="radio" id="discount2" name="discountRate" value="0.02">
    <label for="discount2">2%</label>
    <input type="radio" id="discount5" name="discountRate" value="0.05">
    <label for="discount5">5%</label>
    <input type="radio" id="discount10" name="discountRate" value="0.10">
    <label for="discount10">10%</label>
    <br/>
    <label for="driverID">Choose Driver:</label>
    <select id="driverID" name="driverID">
        <%
            List<Driver> drivers = (List<Driver>) request.getAttribute("drivers");
            for (Driver driver : drivers) {
        %>
        <option value="<%= driver.getDriverID() %>"><%= driver.getName() %></option>
        <%
            }
        %>
    </select>
    <br/>
    <button type="submit">Make Booking</button>
</form>
</body>
</html>