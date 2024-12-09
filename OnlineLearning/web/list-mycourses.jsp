<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>List Courses</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">

        <!-- jQuery and Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

        <style>
            .sidebar {
                width: 300px; /* Set width for sidebar */
                height: 100vh; /* Set height to full viewport height */
                overflow-y: auto; /* Enable vertical scroll if content exceeds height */
                padding-top: 20px;
            }
        </style>

    </head>

    <body>
        <!-- Navbar Start  -->
        <jsp:include page="templates/navbar.jsp" />
        <!-- Navbar End  -->

        <!-- Sidebar and Main Content Start -->
        <div class="container-fluid" style="display: flex; min-height: 100vh;">
            <!-- Sidebar -->
            <div class="sidebar bg-light" style="width: 300px; height: 100vh; display: flex; flex-direction: column; justify-content: flex-start; align-items: center; padding-top: 100px;">
                <!-- Avatar and User Info -->
                <div class="text-center mb-4">
                    <!-- Sử dụng ảnh đại diện từ đối tượng user -->
                    <img src="${user.img}" alt="User Avatar" class="rounded-circle" style="width: 120px; height: 120px;">
                    <!-- Sử dụng tên người dùng từ đối tượng user -->
                    <h3 class="mt-2 text-primary" style="font-size: 1.5rem;">${user.name}</h3>

                </div>

                <!-- User Info Links -->
                <div class="px-3">

                    <form action="ListMyCourses" method="GET" style="display: inline;">
                        <input type="hidden" name="status" value="2">
                        <button type="submit" class="d-block py-2 text-primary" style="font-size: 1.2rem; background: none; border: none;">My Courses</button>
                    </form>
                    <!-- Completed Courses và Studied Courses: Sử dụng POST -->

                    <form action="ListMyCourses" method="POST" style="display: inline;">
                        <input type="hidden" name="status" value="1">
                        <button type="submit" class="d-block py-2 text-primary" style="font-size: 1.2rem; background: none; border: none;">Not Yet</button>
                    </form>

                    <form action="ListMyCourses" method="POST" style="display: inline;">
                        <input type="hidden" name="status" value="2">
                        <button type="submit" class="d-block py-2 text-primary" style="font-size: 1.2rem; background: none; border: none;">Studied Courses</button>
                    </form>

                    <form action="ListMyCourses" method="POST" style="display: inline;">
                        <input type="hidden" name="status" value="3">
                        <button type="submit" class="d-block py-2 text-primary" style="font-size: 1.2rem; background: none; border: none;">Completed Courses</button>
                    </form>



                    <form action="ListMyCourses" method="GET" style="display: inline;">
                        <input type="hidden" name="status" value="3">
                        <button type="submit" class="d-block py-2 text-primary" style="font-size: 1.2rem; background: none; border: none;">Support</button>
                    </form>
                </div>
            </div>



            <!-- Main Content -->
            <!-- Main Content -->
            <div class="col-md-9 ps-4">
                <h2>My Courses</h2>
                <div class="row">
                    <!-- Hiển thị danh sách khóa học -->
                    <c:forEach var="course" items="${courses}">
                        <div class="col-md-2 mb-3">
                            <div class="card" style="border: 1px solid #ddd; border-radius: 8px; overflow: hidden; display: flex; flex-direction: column; height: 100%;">
                                <!-- Hiển thị ảnh khóa học -->
                                <img src="<c:out value='img/${course.img}' />" class="card-img-top" alt="${course.name}" style="max-height: 200px; object-fit: cover; width: 100%;">

                                <div class="card-body p-3" style="flex-grow: 1;">
                                    <h5 class="card-title" style="font-size: 1.1rem; font-weight: bold; height: 50px; overflow: hidden;"><c:out value="${course.name}" /></h5>
                                    <p class="card-text" style="font-size: 0.9rem; color: #555; height: 60px; overflow: hidden;"><c:out value="${course.description}" /></p>

                                    <!-- Trạng thái khóa học -->
                                    <!-- Trạng thái khóa học -->
                                    <p class="card-status mb-2" style="font-size: 1rem;">
                                        <c:choose>
                                            <c:when test="${course.status == 1}">
                                                <span class="text-danger">Not yet learned</span> <!-- Màu đỏ -->
                                            </c:when>
                                            <c:when test="${course.status == 2}">
                                                <span class="text-warning">Studying</span> <!-- Màu vàng -->
                                            </c:when>
                                            <c:when test="${course.status == 3}">
                                                <span class="text-success">Completed</span> <!-- Màu xanh lá cây -->
                                            </c:when>
                                            <c:otherwise>
                                                <span class="text-muted">Chưa xác định</span> <!-- Màu xám nếu không xác định -->
                                            </c:otherwise>
                                        </c:choose>
                                    </p>


                                    <!-- Nút View Course -->
                                    <form action="ViewMyCoursesDetail" method="get">
                                        <input type="hidden" name="courseId" value="<c:out value='${course.courseId}' />">
                                        <button type="submit" class="btn btn-primary btn-sm" style="font-size: 0.9rem;">View Course</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>


                <!-- Phân trang -->
                <div class="d-flex justify-content-center mt-4">
                    <ul class="pagination">
                        <!-- Hiển thị trang trước -->
                        <c:if test="${pageIndex > 1}">
                            <li class="page-item">
                                <a class="page-link" href="?pageIndex=${pageIndex - 1}">
                                    Previous
                                </a>
                            </li>
                        </c:if>

                        <!-- Các trang -->
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == pageIndex ? 'active' : ''}">
                                <a class="page-link" href="?pageIndex=${i}">
                                    ${i}
                                </a>
                            </li>
                        </c:forEach>

                        <!-- Hiển thị trang tiếp theo -->
                        <c:if test="${pageIndex < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="?pageIndex=${pageIndex + 1}">
                                    Next
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>

        </div>


        <!-- Footer Start -->
        <jsp:include page="templates/footer.jsp" />
        <!-- Footer End -->

    </body>
</html>
