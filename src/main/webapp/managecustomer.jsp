<%@ page import="java.util.List" %>
<%@ page import="com.example.mega_city_cab.model.Customer" %>
<%@ page import="com.example.mega_city_cab.service.CustomerService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CustomerService customerService = new CustomerService();
    List<Customer> customers = customerService.getAllCustomers();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Customers</title>
    <link rel="stylesheet" type="text/css" href="styles/managecustomer.css">
</head>
<body>
<h1>Manage Customers</h1>
<table>
    <thead>
    <tr>
        <th>Customer ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>NIC</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Customer customer : customers) {
    %>
    <tr>
        <td><%= customer.getCustomerID() %></td>
        <td><%= customer.getName() %></td>
        <td><%= customer.getAddress() %></td>
        <td><%= customer.getNic() %></td>
        <td><%= customer.getPhone() %></td>
        <td><%= customer.getEmail() %></td>
        <td>
            <form action="editCustomer.jsp" method="get" style="display:inline;">
                <input type="hidden" name="customerID" value="<%= customer.getCustomerID() %>">
                <button type="submit">Edit</button>
            </form>
            <form action="deleteCustomer" method="post" style="display:inline;">
                <input type="hidden" name="customerID" value="<%= customer.getCustomerID() %>">
                <button type="submit" onclick="return confirm('Are you sure you want to delete this customer?');">Delete</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<br>
<a href="addCustomer.jsp">Add New Customer</a>
<a href="admin.jsp">Go to Dashboard</a>
</body>
</html>