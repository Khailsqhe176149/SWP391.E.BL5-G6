<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết tài khoản</title>
</head>
<body>

<h1>Chi tiết tài khoản</h1>

<!-- Hiển thị thông báo lỗi nếu có -->
<c:if test="${not empty message}">
    <p style="color: red;">${message}</p>
</c:if>

<c:if test="${not empty account}">
    <form action="accountDetails" method="post">
        <input type="hidden" name="accountId" value="${account.acc_id}">
        <input type="hidden" name="userID" value="${account.userID}">
        
        <!-- Email - readonly -->
        <label for="email">Email: </label>
        <input type="email" name="email" id="email" value="${account.email}" readonly><br><br>
        
        <!-- Email - readonly -->
        <label for="email">Username: </label>
        <input type="email" name="email" id="email" value="${account.getName()}" readonly><br><br>
        
        <!-- Email - readonly -->
        <label for="email">Phone: </label>
        <input type="email" name="email" id="email" value="${account.getPhone()}" readonly><br><br>
        
        <!-- Email - readonly -->
        <label for="email">Createdtime </label>
        <input type="email" name="email" id="email" value="${account.getCreatedtime()}" readonly><br><br>
        
     
        <!-- Role - editable -->
        <label for="role">Role: </label>
        <select name="role" id="role" required>
            <option value="1" ${account.role_id == 1 ? 'selected' : ''}>Customer</option>
            <option value="2" ${account.role_id == 2 ? 'selected' : ''}>Admin</option>
            <option value="3" ${account.role_id == 3 ? 'selected' : ''}>Staff</option>
        </select><br><br>
        
        <!-- Status - editable -->
        <label for="status">Status: </label>
        <select name="status" id="status" required>
            <option value="1" ${account.status == 1 ? 'selected' : ''}>Active</option>
            <option value="0" ${account.status == 0 ? 'selected' : ''}>Inactive</option>
        </select><br><br>
        
        <button type="submit" name="action" value="update">Cập nhật</button>
    </form>
</c:if>


<a href="accountList">Trở lại danh sách tài khoản</a>

</body>
</html>
