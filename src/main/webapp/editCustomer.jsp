<%@ page import="com.example.mega_city_cab.model.Customer" %>
<%@ page import="com.example.mega_city_cab.service.CustomerService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String customerID = request.getParameter("customerID");
  CustomerService customerService = new CustomerService();
  Customer customer = customerService.getCustomer(Integer.parseInt(customerID));
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Edit Customer</title>
  <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Edit Customer</h1>
<form action="customer" method="post">
  <input type="hidden" name="action" value="update">
  <input type="hidden" name="customerID" value="<%= customer.getCustomerID() %>">
  <div>
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="<%= customer.getName() %>" required>
  </div>
  <div>
    <label for="address">Address:</label>
    <input type="text" id="address" name="address" value="<%= customer.getAddress() %>" required>
  </div>
  <div>
    <label for="nic">NIC:</label>
    <input type="text" id="nic" name="nic" value="<%= customer.getNic() %>" required>
  </div>
  <div>
    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" value="<%= customer.getPhone() %>" required>
  </div>
  <div>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="<%= customer.getEmail() %>" required>
  </div>
  <div>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
  </div>
  <button type="submit">Update</button>
</form>
<br>
<a href="managecustomer.jsp">Back to Manage Customers</a>
</body>
</html>