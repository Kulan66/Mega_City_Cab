<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Driver</title>
    <link rel="stylesheet" type="text/css" href="styles/adddriver.css">
</head>
<body>
<h1>Add New Driver</h1>
<form action="driver" method="post">
    <input type="hidden" name="action" value="add">
    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
    </div>
    <div>
        <label for="contact">Contact:</label>
        <input type="text" id="contact" name="contact" required>
    </div>
    <div>
        <label for="licenseNumber">License Number:</label>
        <input type="text" id="licenseNumber" name="licenseNumber" required>
    </div>
    <div>
        <label for="carID">Car ID:</label>
        <input type="number" id="carID" name="carID" required>
    </div>
    <div>
        <label for="availability">Availability:</label>
        <select id="availability" name="availability" required>
            <option value="true">Available</option>
            <option value="false">Not Available</option>
        </select>
    </div>
    <button type="submit">Add Driver</button>
</form>
<br>
<a href="managedriver.jsp">Back to Manage Drivers</a>
</body>
</html>