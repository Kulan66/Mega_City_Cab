<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles/admin.css">
    <script type="text/javascript">
        function confirmLogout() {
            if (confirm("You are going to be logged out. Are you sure?")) {
                window.location.href = "logout";
            }
        }
    </script>
</head>
<body>
<h1>Admin Dashboard</h1>
<br/>
<a href="managecar.jsp">Manage Cars</a>
<br/>
<a href="managebooking.jsp">Manage Bookings</a>
<br/>
<a href="managecustomer.jsp">Manage Customers</a>
<br/>
<a href="managedriver.jsp">Manage Drivers</a>
<br/>
<a href="helpmanage.jsp">Manage Help</a>
<br/>
<a href="javascript:void(0);" onclick="confirmLogout();">Logout</a> <!-- Updated logout link -->
</body>
</html>