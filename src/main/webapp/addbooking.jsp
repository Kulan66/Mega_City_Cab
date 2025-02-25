<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mega_city_cab.model.Car" %>
<%@ page import="com.example.mega_city_cab.model.Driver" %>
<%@ page import="com.example.mega_city_cab.model.Customer" %>
<%@ page import="com.example.mega_city_cab.service.CarService" %>
<%@ page import="com.example.mega_city_cab.service.DriverService" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Booking</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        .card {
            display: inline-block;
            border: 1px solid #ccc;
            padding: 10px;
            margin: 10px;
            text-align: center;
            width: 150px;
            cursor: pointer;
            transition: transform 0.2s;
        }
        .card img {
            width: 100%;
            height: auto;
        }
        .card.selected {
            border-color: #007bff;
            box-shadow: 0 0 10px rgba(0, 123, 255, 0.5);
            transform: scale(1.05);
        }
    </style>
    <script>
        function selectCard(cardType, value) {
            document.getElementById(cardType).value = value;
            var cards = document.querySelectorAll('.' + cardType + '-card');
            cards.forEach(function(card) {
                card.classList.remove('selected');
            });
            document.getElementById(cardType + '-card-' + value).classList.add('selected');
        }

        function validateForm() {
            var driverID = document.getElementById("driverID").value;
            var carID = document.getElementById("carID").value;
            var paymentMethod = document.getElementById("paymentMethod").value;
            var discount = document.getElementById("discount").value;

            if (!driverID || !carID || !paymentMethod || !discount) {
                alert("Please fill in all details before submitting the form.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<h1>Make a New Booking</h1>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    CarService carService = new CarService();
    DriverService driverService = new DriverService();
    List<Car> cars = carService.getAllCars();
    List<Driver> drivers = driverService.getAllDrivers();
%>
<form action="booking" method="post" onsubmit="return validateForm()">
    <input type="hidden" name="action" value="calculate">
    <input type="hidden" id="customerID" name="customerID" value="<%= customer.getCustomerID() %>">
    <div>
        <label for="driverID">Driver:</label>
        <input type="hidden" id="driverID" name="driverID" required>
        <div>
            <% for (Driver driver : drivers) { %>
            <div class="card driver-card" id="driver-card-<%= driver.getDriverID() %>" onclick="selectCard('driverID', <%= driver.getDriverID() %>)">
                <p><%= driver.getName() %></p>
            </div>
            <% } %>
        </div>
    </div>
    <div>
        <label for="carID">Car:</label>
        <input type="hidden" id="carID" name="carID" required>
        <div>
            <% for (Car car : cars) { %>
            <div class="card car-card" id="car-card-<%= car.getCarID() %>" onclick="selectCard('carID', <%= car.getCarID() %>)">
                <img src="car_images/carID<%= car.getCarID() %>.jpg" alt="<%= car.getModel() %>">
                <p><%= car.getModel() %></p>
            </div>
            <% } %>
        </div>
    </div>
    <div>
        <label for="destination">Destination:</label>
        <input type="text" id="destination" name="destination" required>
    </div>
    <div>
        <label for="paymentMethod">Payment Method:</label>
        <input type="hidden" id="paymentMethod" name="paymentMethod" required>
        <div>
            <div class="card paymentMethod-card" id="paymentMethod-card-cash" onclick="selectCard('paymentMethod', 'cash')">
                <p>Cash</p>
            </div>
            <div class="card paymentMethod-card" id="paymentMethod-card-card" onclick="selectCard('paymentMethod', 'card')">
                <p>Card</p>
            </div>
        </div>
    </div>
    <div>
        <label for="distanceKm">Distance (km):</label>
        <input type="number" step="0.01" id="distanceKm" name="distanceKm" required>
    </div>
    <div>
        <label for="discount">Discount:</label>
        <input type="hidden" id="discount" name="discount" required>
        <div>
            <div class="card discount-card" id="discount-card-0" onclick="selectCard('discount', '0')">
                <p>No Discount</p>
            </div>
            <div class="card discount-card" id="discount-card-2" onclick="selectCard('discount', '2')">
                <p>2% Discount</p>
            </div>
            <div class="card discount-card" id="discount-card-5" onclick="selectCard('discount', '5')">
                <p>5% Discount</p>
            </div>
            <div class="card discount-card" id="discount-card-10" onclick="selectCard('discount', '10')">
                <p>10% Discount</p>
            </div>
        </div>
    </div>
    <button type="submit">Calculate Bill</button>
</form>
<br>
<a href="customer.jsp">Back to Dashboard</a>
</body>
</html>