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

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <!-- Navbar Start -->
    <jsp:include page="templates/navbar.jsp" />
    <!-- Navbar End -->

    <div class="container my-5">
        <h1 class="h3 mb-4">Chi tiết tài khoản</h1>

        <!-- Display message if there's an error -->
        <c:if test="${not empty message}">
            <div class="alert alert-danger">${message}</div>
        </c:if>

        <c:if test="${not empty account}">
            <form action="accountDetails" method="post">
                <input type="hidden" name="accountId" value="${account.acc_id}">
                <input type="hidden" name="userID" value="${account.userID}">
                
                <!-- Email - readonly -->
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" name="email" id="email" value="${account.email}" class="form-control" readonly>
                </div>
                
                <!-- Username - readonly -->
                <div class="mb-3">
                    <label for="username" class="form-label">Username:</label>
                    <input type="text" name="username" id="username" value="${account.getName()}" class="form-control" readonly>
                </div>
                
                <!-- Phone - readonly -->
                <div class="mb-3">
                    <label for="phone" class="form-label">Phone:</label>
                    <input type="text" name="phone" id="phone" value="${account.getPhone()}" class="form-control" readonly>
                </div>
                
                <!-- Created Time - readonly -->
                <div class="mb-3">
                    <label for="createdtime" class="form-label">Created Time:</label>
                    <input type="text" name="createdtime" id="createdtime" value="${account.getCreatedtime()}" class="form-control" readonly>
                </div>
                
                <!-- Role - editable -->
                <div class="mb-3">
                    <label for="role" class="form-label">Role:</label>
                    <select name="role" id="role" class="form-select" required>
                        <option value="1" ${account.role_id == 1 ? 'selected' : ''}>Admin</option>
                        <option value="2" ${account.role_id == 2 ? 'selected' : ''}>Customer</option>
                        <option value="3" ${account.role_id == 3 ? 'selected' : ''}>Staff</option>
                    </select>
                </div>
                
                <!-- Status - editable -->
                <div class="mb-3">
                    <label for="status" class="form-label">Status:</label>
                    <select name="status" id="status" class="form-select" required>
                        <option value="1" ${account.status == 1 ? 'selected' : ''}>Active</option>
                        <option value="0" ${account.status == 0 ? 'selected' : ''}>Inactive</option>
                    </select>
                </div>
                
                <button type="submit" name="action" value="update" class="btn btn-primary">Update</button>
            </form>
        </c:if>

        <a href="accountList" class="btn btn-secondary mt-3">Back to account list</a>
    </div>
     <!-- Footer Start -->
            <jsp:include page="templates/footer.jsp" />
        <!-- Footer End -->
</body>
</html>
