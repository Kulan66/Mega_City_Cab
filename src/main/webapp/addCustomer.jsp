<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add Customer</title>
  <link rel="stylesheet" type="text/css" href="styles/addCustomer.css">
</head>
<body>
<h1>Add New Customer</h1>
<form action="customer" method="post">
  <input type="hidden" name="action" value="register">
  <div>
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required>
  </div>
  <div>
    <label for="address">Address:</label>
    <input type="text" id="address" name="address" required>
  </div>
  <div>
    <label for="nic">NIC:</label>
    <input type="text" id="nic" name="nic" required>
  </div>
  <div>
    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required>
  </div>
  <div>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
  </div>
  <div>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
  </div>
  <button type="submit">Add Customer</button>
</form>
<br>
<a href="managecustomer.jsp">Back to Manage Customers</a>
</body>
</html>