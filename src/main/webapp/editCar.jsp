<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.mega_city_cab.model.Car" %>
<%@ page import="com.example.mega_city_cab.service.CarService" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Car</title>
  <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<%
  int carID = Integer.parseInt(request.getParameter("carID"));
  CarService carService = new CarService();
  Car car = carService.getCar(carID);
%>
<h1>Edit Car</h1>
<form action="car" method="post">
  <input type="hidden" name="action" value="update">
  <input type="hidden" name="carID" value="<%= car.getCarID() %>">
  <div>
    <label for="model">Model:</label>
    <input type="text" id="model" name="model" value="<%= car.getModel() %>" required>
  </div>
  <div>
    <label for="licensePlate">License Plate:</label>
    <input type="text" id="licensePlate" name="licensePlate" value="<%= car.getLicensePlate() %>" required>
  </div>
  <div>
    <label for="availability">Availability:</label>
    <select id="availability" name="availability" required>
      <option value="true" <%= car.isAvailability() ? "selected" : "" %>>Available</option>
      <option value="false" <%= !car.isAvailability() ? "selected" : "" %>>Not Available</option>
    </select>
  </div>
  <button type="submit">Update Car</button>
</form>
<br>
<a href="managecar.jsp">Back to Manage Cars</a>
</body>
</html>