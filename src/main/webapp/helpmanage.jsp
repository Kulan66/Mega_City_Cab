<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.mega_city_cab.model.Help" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Help Guidelines</title>
</head>
<body>
<h1>Manage Help Guidelines</h1>
<form action="help" method="post">
    <input type="hidden" name="action" value="add">
    <label for="guideline">Guideline:</label>
    <textarea id="guideline" name="guideline" required></textarea>
    <br/>
    <button type="submit">Add Guideline</button>
</form>

<h2>Existing Help Guidelines</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Guideline</th>
        <th>Actions</th>
    </tr>
    <%
        List<Help> guidelines = (List<Help>) request.getAttribute("guidelines");
        if (guidelines != null) {
            for (Help help : guidelines) {
    %>
    <tr>
        <td><%= help.getHelpID() %></td>
        <td><%= help.getGuideline() %></td>
        <td>
            <form action="help" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="helpID" value="<%= help.getHelpID() %>">
                <button type="submit">Delete</button>
            </form>
            <form action="help" method="post" style="display:inline;">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="helpID" value="<%= help.getHelpID() %>">
                <textarea name="guideline"><%= help.getGuideline() %></textarea>
                <button type="submit">Update</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>