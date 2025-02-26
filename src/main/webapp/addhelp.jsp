<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Help Guideline</title>
    <link rel="stylesheet" type="text/css" href="styles/addhelp.css">
</head>
<body>
<h1>Add New Help Guideline</h1>
<form action="help" method="post">
    <input type="hidden" name="action" value="add">
    <div>
        <label for="guideline">Guideline:</label>
        <textarea id="guideline" name="guideline" required></textarea>
    </div>
    <button type="submit">Add Guideline</button>
</form>
<br>
<a href="helpmanage.jsp">Back to Manage Help Guidelines</a>
</body>
</html>