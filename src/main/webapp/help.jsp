<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.mega_city_cab.model.Help" %>
<%@ page import="java.util.List" %>
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
    @SuppressWarnings("unchecked")
    List<Help> guidelines = (List<Help>) request.getAttribute("guidelines");
%>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Guideline</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (guidelines != null) {
            for (Help help : guidelines) {
    %>
    <tr>
        <td><%= help.getHelpID() %></td>
        <td><%= help.getGuideline() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="2">No guidelines found.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<br>
<a href="admin.jsp">Go to Dashboard</a>
</body>
</html>