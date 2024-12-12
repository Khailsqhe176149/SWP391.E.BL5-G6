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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        <!-- JavaScript for delete confirmation -->
        <script>
            function confirmDelete() {
                return confirm('Are you sure you want to delete this account?');
            }
        </script>
    </head>

    <body>
        <!-- Navbar Start -->
        <jsp:include page="templates/navbar.jsp" />
        <!-- Navbar End -->

        <div class="container my-5">
            <h1 class="h3 mb-4">Danh sách tài khoản</h1>

            <!-- Display message if there's no account or any other message -->
            <c:if test="${not empty message}">
                <div class="alert alert-danger">${message}</div>
            </c:if>

            <!-- Add Account Button -->
            <div class="mb-3">
                <a href="addAccount" class="btn btn-success">
                    <i class="bi bi-person-plus"></i> Add New Account
                </a>
            </div>
            <!-- Search Form -->
            <form action="accountList" method="get" class="mb-4">
                <div class="row g-3">
                    <div class="col-md-4">
                        <label for="keyword" class="form-label">Search (by email):</label>
                        <input type="text" name="keyword" id="keyword" class="form-control" placeholder="Nhập email...">
                    </div>
                    <div class="col-md-3">
                        <label for="role" class="form-label">Choose role:</label>
                        <select name="role" id="role" class="form-select">
                            <option value="">All</option>
                            <option value="1">Customer</option>
                            <option value="2">Admin</option>
                            <option value="3">Staff</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <label for="status" class="form-label">Choose status:</label>
                        <select name="status" id="status" class="form-select">
                            <option value="">All</option>
                            <option value="1">Active</option>
                            <option value="0">Inactive</option>
                        </select>
                    </div>
                    <div class="col-md-2"  >
                        <button type="submit" class="btn btn-primary w-100"style="margin-top: 32px;" >Search</button>
                    </div>
                </div>
            </form>

            <!-- Display account list -->
            <c:if test="${not empty accountList}">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Email</th>
                            <th>Username</th>
                            <th>Phone</th>
                            <th>Created Time</th>
                            <th>Role</th>
                            <th>Status</th>
                            <th>Details</th>
                            <th>Update Role</th>
                            <th>Update Status</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="account" items="${accountList}">
                            <tr>
                                <td>${account.acc_id}</td>
                                <td>${account.email}</td>
                                <td>${account.getName()}</td>
                                <td>${account.getPhone()}</td>
                                <td><fmt:formatDate value="${account.getCreatedtime()}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
                                            <button type="submit" class="btn btn-info btn-sm"> <i class="fas fa-eye"></i> </button>
                                    </form>
                                </td>
                                <td>
                                    <!-- Update Role -->
                                    <form action="accountList" method="post">
                                        <input type="hidden" name="accountId" value="${account.acc_id}">
                                        <select name="role" class="form-select" required>
                                            <option value="1" ${account.role_id == 1 ? 'selected' : ''}>Admin</option>
                                            <option value="2" ${account.role_id == 2 ? 'selected' : ''}>Customer</option>
                                            <option value="3" ${account.role_id == 3 ? 'selected' : ''}>Staff</option>
                                        </select>
                                        <button type="submit" name="action" value="updaterole" class="btn btn-warning btn-sm" style="margin-top: 10px;"> <i class="fas fa-edit"></i> </button>
                                        
                                    </form>
                                </td>
                                <td>
                                    <!-- Update Status -->
                                    <form action="accountList" method="post">
                                        <input type="hidden" name="accountId" value="${account.acc_id}">
                                        <select name="status" class="form-select" required>
                                            <option value="1" ${account.status == 1 ? 'selected' : ''}>Active</option>
                                            <option value="0" ${account.status == 0 ? 'selected' : ''}>Inactive</option>
                                        </select>
                                        <button type="submit" name="action" value="updatestatus" class="btn btn-warning btn-sm" style="margin-top: 10px;"> <i class="fas fa-edit"></i> </button>
                                    </form>
                                </td>
                                <td>


                                    <!-- Delete Button with Confirmation -->
                                    <form action="accountList" method="post" style="display:inline;" onsubmit="return confirmDelete();">
                                        <input type="hidden" name="accountId" value="${account.acc_id}">
                                        <button type="submit" name="action" value="deleteaccount" class="btn btn-danger btn-sm"><i class="fas fa-trash-alt"></i></button>
                                    </form>



                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>
