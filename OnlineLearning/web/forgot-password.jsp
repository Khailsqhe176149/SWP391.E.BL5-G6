<%-- 
    Document   : forgotPassword
    Created on : Nov 30, 2024, 7:27:54 PM
    Author     : Khải
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên Mật Khẩu</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Quên Mật Khẩu</h2>
        <form action="forgot-password" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <input type="submit" value="Gửi yêu cầu reset mật khẩu">
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
