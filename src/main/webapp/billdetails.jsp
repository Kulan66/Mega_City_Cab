<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.mega_city_cab.model.Booking" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Bill Details</title>
  <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Booking Bill Details</h1>
<%
  Booking booking = (Booking) request.getAttribute("booking");
  if (booking != null) {
%>
<div>
  <p>Customer ID: <%= booking.getCustomerID() %></p>
  <p>Driver ID: <%= booking.getDriverID() %></p>
  <p>Car ID: <%= booking.getCarID() %></p>
  <p>Destination: <%= booking.getDestination() %></p>
  <p>Payment Method: <%= booking.getPaymentMethod() %></p>
  <p>Distance (km): <%= booking.getDistanceKm() %></p>
  <p>Base Price: LKR <%= booking.getBasePrice() %></p>
  <p>Tax: LKR <%= booking.getTax() %></p>
  <p>Discount: <%= booking.getDiscount() %> %</p>
  <p>Total Price: LKR <%= booking.getTotalPrice() %></p>
</div>
<form action="booking" method="post">
  <input type="hidden" name="action" value="confirm">
  <input type="hidden" name="customerID" value="<%= booking.getCustomerID() %>">
  <input type="hidden" name="driverID" value="<%= booking.getDriverID() %>">
  <input type="hidden" name="carID" value="<%= booking.getCarID() %>">
  <input type="hidden" name="destination" value="<%= booking.getDestination() %>">
  <input type="hidden" name="paymentMethod" value="<%= booking.getPaymentMethod() %>">
  <input type="hidden" name="distanceKm" value="<%= booking.getDistanceKm() %>">
  <input type="hidden" name="basePrice" value="<%= booking.getBasePrice() %>">
  <input type="hidden" name="tax" value="<%= booking.getTax() %>">
  <input type="hidden" name="discount" value="<%= booking.getDiscount() %>">
  <input type="hidden" name="totalPrice" value="<%= booking.getTotalPrice() %>">
  <button type="submit">Make the Booking</button>
</form>
<%
} else {
%>
<p>No booking details found.</p>
<%
  }
%>
<br>
<a href="customer.jsp">Back to Dashboard</a>
</body>
</html>