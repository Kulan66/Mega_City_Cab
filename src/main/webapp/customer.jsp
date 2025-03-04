<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.mega_city_cab.model.Customer" %>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String successMessage = (String) session.getAttribute("successMessage");
    if (successMessage != null) {
        session.removeAttribute("successMessage");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles/customer.css">
    <script type="text/javascript">
        function confirmLogout() {
            if (confirm("You are going to be logged out. Are you sure?")) {
                window.location.href = "logout";
            }
        }

        // JavaScript to add background image
        document.addEventListener("DOMContentLoaded", function() {
            document.body.style.backgroundImage = "url('car_images/background.png')";
            document.body.style.backgroundSize = "cover";
            document.body.style.backgroundRepeat = "no-repeat";
            document.body.style.backgroundPosition = "center center";
        });

        // Function to open the manage account popup
        function openManageAccountPopup() {
            document.getElementById('manageAccountPopup').style.display = 'block';
        }

        // Function to close the manage account popup
        function closeManageAccountPopup() {
            document.getElementById('manageAccountPopup').style.display = 'none';
        }

        // Function to validate the manage account form
        function validateManageAccountForm() {
            var name = document.getElementById('name').value;
            var address = document.getElementById('address').value;
            var nic = document.getElementById('nic').value;
            var phone = document.getElementById('phone').value;
            var email = document.getElementById('email').value;
            var password = document.getElementById('password').value;
            var confirmPassword = document.getElementById('confirmPassword').value;

            if (name == "" || address == "" || nic == "" || phone == "" || email == "" || password == "" || confirmPassword == "") {
                alert("All fields are required.");
                return false;
            }

            var nicPattern = /^\d{12}$/;
            if (!nicPattern.test(nic)) {
                alert("Invalid NIC. It must be 12 digits.");
                return false;
            }

            var phonePattern = /^\d{10}$/;
            if (!phonePattern.test(phone)) {
                alert("Invalid phone number. It must be 10 digits.");
                return false;
            }

            var emailPattern = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;
            if (!emailPattern.test(email)) {
                alert("Invalid email address. It must be a valid gmail.com address.");
                return false;
            }

            if (password != confirmPassword) {
                alert("Passwords do not match.");
                return false;
            }

            // Show success message popup
            showSuccessMessage();
            return true;
        }

        // Function to show the success message popup
        function showSuccessMessage() {
            document.getElementById('successMessagePopup').style.display = 'block';
            setTimeout(function() {
                document.getElementById('successMessagePopup').style.display = 'none';
            }, 3000);
        }

        // Show success message if it exists
        document.addEventListener("DOMContentLoaded", function() {
            var successMessage = "<%= successMessage %>";
            if (successMessage && successMessage.length > 0) {
                showSuccessMessage();
            }
        });
    </script>
    <style>
        /* Styles for manage account popup */
        .popup {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 400px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            z-index: 1000;
            border-radius: 10px;
        }

        .popup-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .popup-header h2 {
            margin: 0;
        }

        .popup-close {
            cursor: pointer;
            font-size: 20px;
        }

        .popup-body {
            margin-top: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 15px;
        }

        .popup-body input {
            width: 90%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        .popup-footer {
            display: flex;
            justify-content: flex-end;
            margin-top: 20px;
            width: 90%;
        }

        .popup-footer button {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            background-color: #5cb85c;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .popup-footer button:hover {
            background-color: #4cae4c;
        }

        /* Styles for user logo circle */
        .user-logo {
            position: fixed;
            top: 10px;
            right: 10px;
            width: 60px;
            height: 60px;
            border-radius: 50%;
            background-color: #007bff;
            display: flex;
            justify-content: center;
            align-items: center;
            cursor: pointer;
        }

        .user-logo img {
            width: 80%;
            height: 80%;
            border-radius: 50%;
        }

    </style>
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
<a href="javascript:void(0);" onclick="confirmLogout();">Logout</a>
<br/>

<!-- User logo circle -->
<div class="user-logo" onclick="openManageAccountPopup();">
    <img src="car_images/user_icon.png" alt="User Logo">
</div>

<!-- Manage Account Popup -->
<div id="manageAccountPopup" class="popup">
    <div class="popup-header">
        <h2>Manage Account</h2>
        <span class="popup-close" onclick="closeManageAccountPopup();">&times;</span>
    </div>
    <div class="popup-body">
        <form action="customer?action=update" method="post" onsubmit="return validateManageAccountForm();">
            <input type="hidden" name="customerID" value="<%= customer.getCustomerID() %>">
            <input type="text" id="name" name="name" placeholder="Name" value="<%= customer.getName() %>">
            <input type="text" id="address" name="address" placeholder="Address" value="<%= customer.getAddress() %>">
            <input type="text" id="nic" name="nic" placeholder="NIC" value="<%= customer.getNic() %>">
            <input type="text" id="phone" name="phone" placeholder="Phone" value="<%= customer.getPhone() %>">
            <input type="email" id="email" name="email" placeholder="Email" value="<%= customer.getEmail() %>">
            <input type="password" id="password" name="password" placeholder="Password">
            <input type="password" id="confirmPassword" placeholder="Confirm Password">
            <div class="popup-footer">
                <button type="submit">Save Changes</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>