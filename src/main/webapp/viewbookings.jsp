<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mega_city_cab.model.Booking" %>
<!DOCTYPE html>
<html>
<head>
  <title>View Bookings</title>
  <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Your Past Bookings</h1>
<table border="1">
  <tr>
    <th>Booking ID</th>
    <th>Driver ID</th>
    <th>Car ID</th>
    <th>Destination</th>
    <th>Payment Method</th>
    <th>Distance (km)</th>
    <th>Total Price</th>
    <th>Booking Date</th>
  </tr>
  <%
    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
    if (bookings != null) {
      for (Booking booking : bookings) {
  %>
  <tr>
    <td><%= booking.getBookingID() %></td>
    <td><%= booking.getDriverID() %></td>
    <td><%= booking.getCarID() %></td>
    <td><%= booking.getDestination() %></td>
    <td><%= booking.getPaymentMethod() %></td>
    <td><%= booking.getDistanceKm() %></td>
    <td><%= booking.getTotalPrice() %></td>
    <td><%= booking.getBookingDate() %></td>
  </tr>
  <%
      }
    }
  %>
</table>
<a href="customer.jsp">Back to Dashboard</a>
</body>
</html>