<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mega_city_cab.model.Booking" %>
<%@ page import="com.example.mega_city_cab.service.BookingService" %>
<%@ page import="com.example.mega_city_cab.model.Customer" %>
<%
  Customer customer = (Customer) session.getAttribute("customer");
  if (customer == null) {
    response.sendRedirect("login.jsp");
    return;
  }
  BookingService bookingService = new BookingService();
  List<Booking> bookings = bookingService.getAllBookings();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>View past Bookings</title>
  <link rel="stylesheet" type="text/css" href="styles/viewbooking.css">
</head>
<body>
<h1>View Bookings</h1>
<table border="1">
  <thead>
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
  </tr>
  </thead>
  <tbody>
  <%
    for (Booking booking : bookings) {
      if (booking.getCustomerID() == customer.getCustomerID()) {
  %>
  <tr>
    <td><%= booking.getBookingID() %></td>
    <td><%= booking.getCustomerID() %></td>
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
  </tbody>
</table>
<br>
<a href="customer.jsp">Back to Dashboard</a>
</body>
</html>