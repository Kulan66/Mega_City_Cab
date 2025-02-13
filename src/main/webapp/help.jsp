<%@ page import="java.util.List" %>
<%@ page import="com.example.mega_city_cab.model.Help" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    List<Help> guidelines = (List<Help>) request.getAttribute("guidelines");
    if (guidelines != null) {
        for (Help help : guidelines) {
%>
<p><%= help.getGuideline() %></p>
<%
    }
} else {
%>
<p>No guidelines available.</p>
<%
    }
%>
</body>
</html>