<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.mega_city_cab.model.Customer" %>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
</head>
<body>
<h1>Hi <%= customer.getName() %></h1>
<br/>
<a href="addbooking.jsp">Make a Booking</a>
<br/>
<a href="viewbookings.jsp">View Past Bookings</a>
<br/>
<a href="viewhelp.jsp">Help</a>
<br/>
<a href="logout.jsp">Logout</a>
</body>
</html>