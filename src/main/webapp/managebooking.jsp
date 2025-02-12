<%@ page import="java.util.List" %>
<%@ page import="com.example.mega_city_cab.model.Booking" %>
<%@ page import="com.example.mega_city_cab.service.BookingService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BookingService bookingService = new BookingService();
    List<Booking> bookings = bookingService.getAllBookings();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Bookings</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Manage Bookings</h1>
<table>
    <thead>
    <tr>
        <th>Booking ID</th>
        <th>Customer Name</th>
        <th>Driver Name</th>
        <th>Car</th>
        <th>Destination</th>
        <th>Payment Method</th>
        <th>Distance (km)</th>
        <th>Total Price</th>
        <th>Booking Date</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Booking booking : bookings) {
    %>
    <tr>
        <td><%= booking.getBookingID() %></td>
        <td><%= booking.getCustomer().getName() %></td>
        <td><%= booking.getDriver().getName() %></td>
        <td><%= booking.getCar().getModel() %></td>
        <td><%= booking.getDestination() %></td>
        <td><%= booking.getPaymentMethod() %></td>
        <td><%= booking.getDistanceKm() %></td>
        <td><%= booking.getTotalPrice() %></td>
        <td><%= booking.getBookingDate() %></td>
        <td>
            <form action="editBooking.jsp" method="get" style="display:inline;">
                <input type="hidden" name="bookingID" value="<%= booking.getBookingID() %>">
                <button type="submit">Edit</button>
            </form>
            <form action="deleteBooking" method="post" style="display:inline;">
                <input type="hidden" name="bookingID" value="<%= booking.getBookingID() %>">
                <button type="submit" onclick="return confirm('Are you sure you want to delete this booking?');">Delete</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>
</html>