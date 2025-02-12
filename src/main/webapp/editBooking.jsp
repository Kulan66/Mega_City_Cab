<%@ page import="com.example.mega_city_cab.model.Booking" %>
<%@ page import="com.example.mega_city_cab.service.BookingService" %>
<%@ page import="com.example.mega_city_cab.model.Customer" %>
<%@ page import="com.example.mega_city_cab.model.Driver" %>
<%@ page import="com.example.mega_city_cab.model.Car" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String bookingID = request.getParameter("bookingID");
  BookingService bookingService = new BookingService();
  Booking booking = bookingService.getBooking(Integer.parseInt(bookingID));
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Edit Booking</title>
  <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Edit Booking</h1>
<form action="updateBooking" method="post">
  <input type="hidden" name="bookingID" value="<%= booking.getBookingID() %>">
  <div>
    <label for="customer">Customer:</label>
    <input type="text" id="customer" name="customer" value="<%= booking.getCustomer().getName() %>" required>
  </div>
  <div>
    <label for="driver">Driver:</label>
    <input type="text" id="driver" name="driver" value="<%= booking.getDriver().getName() %>" required>
  </div>
  <div>
    <label for="car">Car:</label>
    <input type="text" id="car" name="car" value="<%= booking.getCar().getModel() %>" required>
  </div>
  <div>
    <label for="destination">Destination:</label>
    <input type="text" id="destination" name="destination" value="<%= booking.getDestination() %>" required>
  </div>
  <div>
    <label for="paymentMethod">Payment Method:</label>
    <input type="text" id="paymentMethod" name="paymentMethod" value="<%= booking.getPaymentMethod() %>" required>
  </div>
  <div>
    <label for="distanceKm">Distance (km):</label>
    <input type="number" id="distanceKm" name="distanceKm" value="<%= booking.getDistanceKm() %>" required>
  </div>
  <div>
    <label for="totalPrice">Total Price:</label>
    <input type="number" id="totalPrice" name="totalPrice" value="<%= booking.getTotalPrice() %>" required>
  </div>
  <div>
    <label for="bookingDate">Booking Date:</label>
    <input type="date" id="bookingDate" name="bookingDate" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(booking.getBookingDate()) %>" required>
  </div>
  <button type="submit">Update</button>
</form>
<br>
<a href="managebooking.jsp">Back to Manage Bookings</a>
</body>
</html>