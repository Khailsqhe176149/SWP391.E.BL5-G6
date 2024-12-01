<%-- 
    Document   : resetPassword
    Created on : Nov 30, 2024, 7:28:45 PM
    Author     : Khải
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Mật Khẩu</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Reset Mật Khẩu</h2>
        <form action="reset-password" method="post">
            <input type="hidden" name="token" value="${param.token}">
            <label for="password">Mật khẩu mới:</label>
            <input type="password" id="password" name="password" required><br>

            <label for="confirmPassword">Xác nhận mật khẩu:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required><br>

            <input type="submit" value="Reset Mật Khẩu">
        </form>

        <c:if test="${not empty messageErr}">
            <p style="color:red;">${messageErr}</p>
        </c:if>
        <c:if test="${not empty message}">
            <p style="color:green;">${message}</p>
        </c:if>
    </div>
</body>
</html>
