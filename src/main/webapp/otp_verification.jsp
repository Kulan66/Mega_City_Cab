<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>OTP Verification</title>
</head>
<body>
<h1>OTP Verification</h1>
<form action="customer" method="post">
    <input type="hidden" name="action" value="verifyOtp">
    <input type="hidden" name="email" value="${param.email}">
    <label for="otp">Enter OTP:</label>
    <input type="text" id="otp" name="otp" required>
    <br/>
    <button type="submit">Verify</button>
</form>
<c:if test="${param.error != null}">
    <p style="color: red;">${param.error}</p>
</c:if>
</body>
</html>