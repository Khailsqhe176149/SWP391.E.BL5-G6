<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh sách tài khoản</title>
    </head>
    <body>

        <h1>Danh sách tài khoản</h1>

        <!-- Hiển thị thông báo nếu không có tài khoản -->
        <c:if test="${not empty message}">
            <p style="color: red;">${message}</p>
        </c:if>

        <!-- Form tìm kiếm -->
        <form action="accountList" method="get">
            <label for="keyword">Tìm kiếm (theo email): </label>
            <input type="text" name="keyword" id="keyword" placeholder="Nhập email...">
            <label for="role">Chọn role: </label>
            <select name="role" id="role">
                <option value="">Tất cả</option>
                <option value="1">Customer</option>
                <option value="2">Admin</option>
                <option value="3">Staff</option>
            </select>
            <label for="status">Chọn status: </label>
            <select name="status" id="status">
                <option value="">Tất cả</option>
                <option value="1">Active</option>
                <option value="0">Inactive</option>
            </select>
            <button type="submit">Tìm kiếm</button>
        </form>

        <!-- Hiển thị danh sách tài khoản -->
        <c:if test="${not empty accountList}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Email</th>
                        <th>Username</th>
                        <th>Phone</th>
                        <th>Createdtime</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Chi tiết</th>
                        <th>Cập nhật</th>
                        <th>Xóa</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="account" items="${accountList}">
                        <tr>
                            <td>${account.acc_id}</td>
                            <td>${account.email}</td>
                            <td>${account.getName()}</td>
                            <td>${account.getPhone()}</td>
                            <td>${account.getCreatedtime()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${account.role_id == 1}">Customer</c:when>
                                    <c:when test="${account.role_id == 2}">Admin</c:when>
                                    <c:when test="${account.role_id == 3}">Staff</c:when>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${account.status == 1}">Active</c:when>
                                    <c:when test="${account.status == 0}">Inactive</c:when>
                                </c:choose>
                            </td>
                            <td>
                                <form action="accountList" method="post">
                                    <input type="hidden" name="action" value="accountdetails">
                                    <input type="hidden" name="accountId" value="${account.acc_id}">
                                    <button type="submit">Xem chi tiết</button>
                                </form>


                            </td>
                            <td>
                                <!-- Cập nhật role -->
                                <form action="accountList" method="post">
                                    <input type="hidden" name="accountId" value="${account.acc_id}">
                                    <select name="role" required>
                                        <option value="1" ${account.role_id == 1 ? 'selected' : ''}>Customer</option>
                                        <option value="2" ${account.role_id == 2 ? 'selected' : ''}>Admin</option>
                                        <option value="3" ${account.role_id == 3 ? 'selected' : ''}>Staff</option>
                                    </select>
                                    <button type="submit" name="action" value="updaterole">Cập nhật</button>
                                </form>
                            </td>
                            <td>
                                <!-- Cập nhật status -->
                                <form action="accountList" method="post">
                                    <input type="hidden" name="accountId" value="${account.acc_id}">
                                    <select name="status" required>
                                        <option value="1" ${account.status == 1 ? 'selected' : ''}>Active</option>
                                        <option value="0" ${account.status == 0 ? 'selected' : ''}>Inactive</option>
                                    </select>
                                    <button type="submit" name="action" value="updatestatus">Cập nhật</button>
                                </form>
                            </td>
                            <td>
                                <!-- Xóa tài khoản -->
                                <form action="accountList" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn xóa tài khoản này?');">
                                    <input type="hidden" name="accountId" value="${account.acc_id}">
                                    <button type="submit" name="action" value="deleteaccount">Xóa</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
