<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Booking</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Make a New Booking</h1>
<form action="booking" method="post">
    <input type="hidden" name="action" value="calculate">
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
        <label for="discount">Discount:</label>
        <select id="discount" name="discount">
            <option value="0">No Discount</option>
            <option value="2">2% Discount</option>
            <option value="5">5% Discount</option>
            <option value="10">10% Discount</option>
        </select>
    </div>
    <button type="submit">Calculate Bill</button>
</form>
<br>
<a href="customer.jsp">Back to Dashboard</a>
</body>
</html>