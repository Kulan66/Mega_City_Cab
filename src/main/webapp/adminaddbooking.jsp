<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Booking</title>
    <link rel="stylesheet" type="text/css" href="styles/adminaddbooking.css">
</head>
<body>
<h1>Add New Booking</h1>
<form action="booking" method="post">
    <input type="hidden" name="action" value="add">
    <div>
        <label for="customerID">Customer ID:</label>
        <input type="number" id="customerID" name="customerID" required>
    </div>
    <div>
        <label for="driverID">Driver ID:</label>
        <input type="number" id="driverID" name="driverID" required>
    </div>
    <div>
        <label for="carID">Car ID:</label>
        <input type="number" id="carID" name="carID" required>
    </div>
    <div>
        <label for="destination">Destination:</label>
        <input type="text" id="destination" name="destination" required>
    </div>
    <div>
        <label for="paymentMethod">Payment Method:</label>
        <select id="paymentMethod" name="paymentMethod" required>
            <option value="cash">Cash</option>
            <option value="card">Card</option>
        </select>
    </div>
    <div>
        <label for="distanceKm">Distance (km):</label>
        <input type="number" step="0.01" id="distanceKm" name="distanceKm" required>
    </div>
    <div>
        <label for="totalPrice">Total Price:</label>
        <input type="number" step="0.01" id="totalPrice" name="totalPrice" required>
    </div>
    <button type="submit">Add Booking</button>
</form>
<br>
<a href="managebooking.jsp">Back to Manage Bookings</a>
</body>
</html>