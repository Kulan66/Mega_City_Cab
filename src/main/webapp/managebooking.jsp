<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.mega_city_cab.model.Booking" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Bookings</title>
</head>
<body>
<h1>Manage Bookings</h1>
<table border="1">
    <tr>
        <th>Booking ID</th>
        <th>Customer ID</th>
        <th>Driver ID</th>
        <th>Car ID</th>
        <th>Destination</th>
        <th>Payment Method</th>
        <th>Distance (km)</th>
        <th>Total Price</th>
        <th>Booking Date</th>
        <th>Action</th>
    </tr>
    <%
        List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
        if (bookings != null) {
            for (Booking booking : bookings) {
    %>
    <tr>
        <td><%= booking.getBookingId() %></td>
        <td><%= booking.getCustomerId() %></td>
        <td><%= booking.getDriverId() %></td>
        <td><%= booking.getCarId() %></td>
        <td><%= booking.getDestination() %></td>
        <td><%= booking.getPaymentMethod() %></td>
        <td><%= booking.getDistanceKm() %></td>
        <td><%= booking.getTotalPrice() %></td>
        <td><%= booking.getBookingDate() %></td>
        <td>
            <form action="booking" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="bookingID" value="<%= booking.getBookingId() %>">
                <button type="submit">Delete</button>
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