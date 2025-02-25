<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mega_city_cab.model.Help" %>
<%@ page import="com.example.mega_city_cab.service.HelpService" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Help Guidelines</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Help Guidelines</h1>
<%
    HelpService helpService = new HelpService();
    List<Help> guidelines = helpService.getAllGuidelines();
%>
<%
    if (!guidelines.isEmpty()) {
        for (Help help : guidelines) {
%>
<div class="guideline">
    <p><%= help.getGuideline() %></p>
</div>
<%
    }
} else {
%>
<p>No help guidelines found.</p>
<%
    }
%>
<br>
<a href="customer.jsp">Back to Dashboard</a>
</body>
</html>