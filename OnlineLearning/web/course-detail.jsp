<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Courses Detail</title>
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

    </head>
    <body>
        <!-- Navbar Start  -->
        <jsp:include page="templates/navbar.jsp" />
        <!-- Navbar End  -->
<div class="container mt-5">
    <h2>Course Detail</h2>

    <div class="row">
        <!-- Hiển thị hình ảnh khóa học -->
        <div class="col-md-6">
            <img src="<c:out value='img/${course.img}' />" class="img-fluid" alt="${course.name}">
        </div>

        <!-- Hiển thị thông tin khóa học -->
        <div class="col-md-6">
            <h3><c:out value="${course.name}" /></h3>

            <p><strong>Created on:</strong> <c:out value="${course.createdTime}" /></p>
            <p><strong>Status:</strong> <c:out value="${course.status}" /></p>
            <p><strong>Price:</strong> <span class="text-success"><c:out value="${course.price}" /></span></p>
            <p><strong>Description:</strong> <c:out value="${course.description}" /></p>
            <p><strong>Tag:</strong> <c:out value="${course.tag}" /></p>

            <div class="d-flex justify-content-between">
                <!-- Form để gửi courseId khi người dùng click vào Enroll Now -->
                <form action="RegisterCourseServlet" method="GET">
                    <!-- Thêm hidden input để lưu courseId -->
                    <input type="hidden" name="courseId" value="${course.courseId}" />

                    <!-- Nút đăng ký khóa học -->
                    <button type="submit" class="btn btn-primary">Enroll Now</button>
                </form>

                <!-- Nút quay lại -->
                <a href="listCourses" class="btn btn-secondary">Back to Courses</a>
            </div>
        </div>
    </div>
</div>


            <div class="container-xxl py-5 wow fadeInUp" data-wow-delay="0.1s">
                <div class="container">    
                    <h2>Lesson</h2>
                    <div class="owl-carousel testimonial-carousel position-relative">
                        <!-- Loop through the posts and display them in carousel items -->
                        <c:forEach var="lesson" items="${lessons}">
                            <div class="testimonial-item text-center">

                                <!-- Post Title -->
                                <h5 class="mb-0">${lesson.name}</h5>
                                <p>not yet</p>
                                <div class="testimonial-text bg-light text-center p-4">
                                    <!-- Post Content -->
                                    <p class="mb-0">${lesson.description}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>  


            <div class="container-xxl py-5 wow fadeInUp" data-wow-delay="0.1s">
                <div class="container">    
                    <h2>Exam</h2>
                    <div class="owl-carousel testimonial-carousel position-relative">
                        <!-- Loop through the posts and display them in carousel items -->
                        <c:forEach var="quiz" items="${quizzes}">
                            <div class="testimonial-item text-center">

                                <!-- Post Title -->
                                <h5 class="mb-0">${quiz.name}</h5>

                                <p>not yet</p> 
                                <div class="testimonial-text bg-light text-center p-4">
                                    <!-- Post Content -->
                                    <p class="mb-0">${quiz.description}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer Start -->
        <jsp:include page="templates/footer.jsp" />
        <!-- Footer End -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/wow/wow.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>
</html>
