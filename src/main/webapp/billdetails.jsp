<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Bill Details</title>
  <style>
    .bill-popup {
      width: 300px;
      padding: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
      background-color: #f9f9f9;
      position: fixed;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
  </style>
</head>
<body>
<div class="bill-popup">
  <h2>Bill Details</h2>
  <p>Distance (km): ${distanceKm}</p>
  <h3>Total Price: LKR ${totalPrice}</h3>
  <a href="customer.jsp">Go to Dashboard</a>
</div>
</body>
</html>