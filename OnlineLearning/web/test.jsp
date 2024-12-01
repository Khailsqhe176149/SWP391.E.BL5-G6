<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách khóa học</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Danh sách khóa học</h1>

        <div class="row">
            <!-- Hiển thị danh sách khóa học -->
            <c:forEach var="course" items="${courses}">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <img src="<c:out value="${course.img}" />" class="card-img-top" alt="${course.name}">
                        <div class="card-body">
                            <h5 class="card-title"><c:out value="${course.name}" /></h5>
                            <p class="card-text"><c:out value="${course.description}" /></p>
                            <p class="card-price text-success"><strong><c:out value="${course.price}" /> VNĐ</strong></p>
                            <a href="courseDetail?courseId=<c:out value="${course.courseId}" />" class="btn btn-primary">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Phân trang -->
        <div class="d-flex justify-content-center">
            <ul class="pagination">
                <!-- Previous page -->
                <c:if test="${pageIndex > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?pageIndex=${pageIndex - 1}">Trước</a>
                    </li>
                </c:if>

                <!-- Hiển thị các trang -->
                <c:forEach var="i" begin="1" end="${totalPages}" step="1">
                    <li class="page-item <c:if test="${i == pageIndex}">active</c:if>">
                        <a class="page-link" href="?pageIndex=${i}">${i}</a>
                    </li>
                </c:forEach>

                <!-- Next page -->
                <c:if test="${pageIndex < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?pageIndex=${pageIndex + 1}">Sau</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
