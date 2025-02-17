<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.mega_city_cab.model.Booking" %>
<%@ page import="com.example.mega_city_cab.service.BookingService" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Booking</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<%
    int bookingID = Integer.parseInt(request.getParameter("bookingID"));
    BookingService bookingService = new BookingService();
    Booking booking = bookingService.getBooking(bookingID);
%>
<h1>Edit Booking</h1>
<form action="booking" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="bookingID" value="<%= booking.getBookingID() %>">
    <div>
        <label for="customerID">Customer ID:</label>
        <input type="number" id="customerID" name="customerID" value="<%= booking.getCustomerID() %>" required>
    </div>
    <div>
        <label for="driverID">Driver ID:</label>
        <input type="number" id="driverID" name="driverID" value="<%= booking.getDriverID() %>" required>
    </div>
    <div>
        <label for="carID">Car ID:</label>
        <input type="number" id="carID" name="carID" value="<%= booking.getCarID() %>" required>
    </div>
    <div>
        <label for="destination">Destination:</label>
        <input type="text" id="destination" name="destination" value="<%= booking.getDestination() %>" required>
    </div>
    <div>
        <label for="paymentMethod">Payment Method:</label>
        <select id="paymentMethod" name="paymentMethod" required>
            <option value="cash" <%= booking.getPaymentMethod().equals("cash") ? "selected" : "" %>>Cash</option>
            <option value="card" <%= booking.getPaymentMethod().equals("card") ? "selected" : "" %>>Card</option>
        </select>
    </div>
    <div>
        <label for="distanceKm">Distance (km):</label>
        <input type="number" step="0.01" id="distanceKm" name="distanceKm" value="<%= booking.getDistanceKm() %>" required>
    </div>
    <div>
        <label for="totalPrice">Total Price:</label>
        <input type="number" step="0.01" id="totalPrice" name="totalPrice" value="<%= booking.getTotalPrice() %>" required>
    </div>
    <button type="submit">Update Booking</button>
</form>
<br>
<a href="managebooking.jsp">Back to Manage Bookings</a>
</body>
</html>