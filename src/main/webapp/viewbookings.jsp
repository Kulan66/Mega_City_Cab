<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.mega_city_cab.model.Booking" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <title>View Past Bookings</title>
</head>
<body>
<h1>View Past Bookings</h1>
<table border="1">
  <tr>
    <th>Booking ID</th>
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
    <td><%= booking.getBookingId() %></td>
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
<br/>
<a href="customer.jsp">Back to Dashboard</a>
</body>
</html>