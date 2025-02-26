<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.mega_city_cab.model.Help" %>
<%@ page import="com.example.mega_city_cab.service.HelpService" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Help Guideline</title>
    <link rel="stylesheet" type="text/css" href="styles/edithelp.css">
</head>
<body>
<%
    int helpID = Integer.parseInt(request.getParameter("helpID"));
    HelpService helpService = new HelpService();
    Help help = helpService.getHelp(helpID);
%>
<h1>Edit Help Guideline</h1>
<form action="help" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="helpID" value="<%= help.getHelpID() %>">
    <div>
        <label for="guideline">Guideline:</label>
        <textarea id="guideline" name="guideline" required><%= help.getGuideline() %></textarea>
    </div>
    <button type="submit">Update Guideline</button>
</form>
<br>
<a href="helpmanage.jsp">Back to Manage Help Guidelines</a>
</body>
</html>