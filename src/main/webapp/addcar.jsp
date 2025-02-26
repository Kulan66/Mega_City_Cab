<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Car</title>
    <link rel="stylesheet" type="text/css" href="styles/addcar.css">
</head>
<body>
<h1>Add New Car</h1>
<form action="car" method="post">
    <input type="hidden" name="action" value="add">
    <div>
        <label for="model">Model:</label>
        <input type="text" id="model" name="model" required>
    </div>
    <div>
        <label for="licensePlate">License Plate:</label>
        <input type="text" id="licensePlate" name="licensePlate" required>
    </div>
    <div>
        <label for="availability">Availability:</label>
        <select id="availability" name="availability" required>
            <option value="true">Available</option>
            <option value="false">Not Available</option>
        </select>
    </div>
    <button type="submit">Add Car</button>
</form>
<br>
<a href="managecar.jsp">Back to Manage Cars</a>
</body>
</html>