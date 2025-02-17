<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mega_city_cab.model.Help" %>
<%@ page import="com.example.mega_city_cab.service.HelpService" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Help Guidelines</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Manage Help Guidelines</h1>
<%
    HelpService helpService = new HelpService();
    List<Help> guidelines = helpService.getAllGuidelines();
%>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Guideline</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Help help : guidelines) {
    %>
    <tr>
        <td><%= help.getHelpID() %></td>
        <td><%= help.getGuideline() %></td>
        <td>
            <form action="edithelp.jsp" method="get" style="display:inline;">
                <input type="hidden" name="helpID" value="<%= help.getHelpID() %>">
                <button type="submit">Edit</button>
            </form>
            <form action="help" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="helpID" value="<%= help.getHelpID() %>">
                <button type="submit" onclick="return confirm('Are you sure you want to delete this guideline?');">Delete</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<br>
<a href="addhelp.jsp">Add New Guideline</a>
<a href="admin.jsp">Go to Dashboard</a>
</body>
</html>