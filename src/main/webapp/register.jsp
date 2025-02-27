<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="styles/register.css">
</head>
<body>
<h1>Register</h1>
<form action="customer" method="post">
    <input type="hidden" name="action" value="register">
    <label for="name">Name</label>
    <input type="text" id="name" name="name" required>
    <br/>
    <label for="address">Address</label>
    <input type="text" id="address" name="address" required>
    <br/>
    <label for="nic">NIC</label>
    <input type="text" id="nic" name="nic" required>
    <br/>
    <label for="phone">Phone</label>
    <input type="text" id="phone" name="phone" required>
    <br/>
    <label for="email">Email</label>
    <input type="email" id="email" name="email" required>
    <br/>
    <label for="password">Password</label>
    <input type="password" id="password" name="password" required>
    <br/>
    <button type="submit">Register</button>
</form>
<br/>
<a href="login.jsp">Already have an account? Login here.</a>
<% String error = request.getParameter("error"); %>
<% if (error != null) { %>
<p style="color: red;"><%= error %></p>
<% } %>
</body>
</html>