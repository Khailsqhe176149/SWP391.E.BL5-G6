<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Post Management</title>

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
        <h1 class="h3 mb-4">Post Management</h1>

        <!-- Add New Post Button -->
        <div class="mb-4">
            <a href="add-post.jsp" class="btn btn-success">Add New Post</a>
        </div>
        <!-- Display Message if Exists -->
            <c:if test="${not empty message}">
                <div class="alert alert-warning" role="alert" id="alert-message">
                    ${message}
                </div>
                 <c:remove var="message" />
            </c:if>

        <!-- Search Form -->
        <form action="post-management" method="get" class="mb-4">
            <div class="row g-3">
                <div class="col-md-6">
                    <input type="text" name="search" placeholder="Search by title" value="${param.search}" class="form-control" />
                </div>
                <div class="col-md-4">
                    <select name="status" class="form-select">
                        <option value="">Select Status</option>
                        <option value="1" ${param.status == '1' ? 'selected' : ''}>Active</option>
                        <option value="0" ${param.status == '0' ? 'selected' : ''}>Inactive</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary w-100">Search</button>
                </div>
            </div>
                     <input type="hidden" name="page" value="${param.page}" />
        </form>

        <!-- Posts Table -->
        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Content</th>
                    <th>Image</th>
                    <th>Status</th>
                    
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="post" items="${posts}">
                    <tr>
                        <td>${post.getTitle()}</td>
                        <td>${post.getContent()}</td>
                        <td>
                            <img src="${post.getImg()}" alt="Post Image" class="img-fluid" width="50" height="50" />
                        </td>
                        <td>${post.getStatus() == 1 ? 'Active' : 'Inactive'}</td>
                        
                        <td>
                            <a href="post-management?action=edit&postId=${post.getPostid()}" class="btn btn-warning btn-sm"> <i class="fas fa-edit"></i> </a>
                            <a href="javascript:void(0);" class="btn btn-danger btn-sm"
                                   onclick="confirmDelete('${post.getPostid()}')" style="margin-top: 10px;">
                                    <i class="fas fa-trash-alt"></i>
                                </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
        <!-- Hiển thị các nút phân trang -->
        <c:if test="${totalPages > 1}">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <!-- Trang đầu -->
                    <c:if test="${currentPage > 1}">
                        <li class="page-item">
                            <a class="page-link" href="?page=1&search=${param.search}&status=${param.status}" aria-label="First">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <!-- Trang trước -->
                    <c:if test="${currentPage > 1}">
                        <li class="page-item">
                            <a class="page-link" href="?page=${currentPage - 1}&search=${param.search}&status=${param.status}" aria-label="Previous">
                                <span aria-hidden="true">&lsaquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <!-- Trang hiện tại -->
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                            <a class="page-link" href="?page=${i}&search=${param.search}&status=${param.status}">${i}</a>
                        </li>
                    </c:forEach>

                    <!-- Trang sau -->
                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item">
                            <a class="page-link" href="?page=${currentPage + 1}&search=${param.search}&status=${param.status}" aria-label="Next">
                                <span aria-hidden="true">&rsaquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <!-- Trang cuối -->
                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item">
                            <a class="page-link" href="?page=${totalPages}&search=${param.search}&status=${param.status}" aria-label="Last">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </c:if>

 <!-- Footer Start -->
            <jsp:include page="templates/footer.jsp" />
        <!-- Footer End -->
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript">
                                       function confirmDelete(postId) {
                                           if (confirm("Are you sure you want to delete this slider?")) {
                                               window.location.href = "post-management?action=delete&postId=" + postId;
                                           }
                                       }
        </script>
        <script>
                                        // Kiểm tra nếu có thông báo
                                        window.onload = function () {
                                            var alertMessage = document.getElementById("alert-message");
                                            if (alertMessage) {
                                                // Ẩn thông báo sau 2 giây
                                                setTimeout(function () {
                                                    alertMessage.style.display = 'none';
                                                }, 2000);  // Thời gian là 2000ms (2 giây)
                                            }
                                        };
        </script>
</body>

</html>
