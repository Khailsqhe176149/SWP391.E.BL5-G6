<%-- 
    Document   : login
    Created on : Nov 28, 2024, 2:13:39 PM
    Author     : Khải
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Đăng Nhập</h2>
    <form action="Login" method="post">
        <label>Email:</label>
        <input type="text" name="email" required>
        <br>
        <label>Password:</label>
        <input type="password" name="password" required>
        <br>
        <button type="submit">Đăng Nhập</button>
    </form>
    <p style="color:red">${error}</p>
</body>
</html>
