<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="model.Lesson" %>
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
        <style>.lesson-item {
                background-color: #f9f9f9;
                padding: 15px;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }

            .lesson-name {
                font-size: 1.25rem;
                font-weight: bold;
                color: #0056b3;
                transition: color 0.3s, text-decoration 0.3s;
            }

            .lesson-name:hover {
                color: #003366;
                text-decoration: underline;
            }

            .lesson-list {
                max-height: 400px;
                overflow-y: auto;
                margin-top: 10px;
                padding-right: 15px;
            }

            .lesson-item + .lesson-item {
                margin-top: 15px;
            }</style>
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
                    <h3><c:out value="${course.name}"/></h3>
                    <p><strong>Description:</strong> <c:out value="${course.description}"/></p>

                    <p class="card-status mb-2" style="font-size: 1rem;">
                        <c:choose>
                            <c:when test="${course.status == 1}">
                                <span class="text-danger">Not started</span>
                            </c:when>
                            <c:when test="${course.status == 2}">
                                <span class="text-warning">In Progress</span>
                            </c:when>
                            <c:when test="${course.status == 3}">
                                <span class="text-success">Completed</span>
                            </c:when>
                            <c:otherwise>
                                <span class="text-muted">Status Unknown</span>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p><strong>Number of Quizzes:</strong> ${quizCount}</p>
                    <p><strong>Number of Lessons:</strong> ${lessonCount}</p>


                    <p><strong>Registration Date:</strong> <c:out value="${course.registrationDate}"/></p>
                    <a href="ListMyCourses" class="btn btn-secondary">Back to Courses</a>
                </div>

            </div>
            <div class="mt-4">
                <h4>Lessons</h4>
                <div class="lesson-list" style="max-height: 300px; overflow-y: auto; border: 1px solid #ddd; padding: 10px; border-radius: 5px;">
                    <c:forEach var="lesson" items="${lessons}">
                        <div class="lesson-item mb-3">
                            <h5 class="lesson-name" style="font-size: 1.2rem; color: #007bff; cursor: pointer; transition: color 0.3s;">
                                <c:out value="${lesson.name}"/>
                            </h5>
                            <p><strong>Date:</strong> <c:out value="${lesson.date}"/></p>
                            <p><strong>Description:</strong> <c:out value="${lesson.description}"/></p>
                            <form action="ListLessonVideo" method="GET">
                                
                                <input type="hidden" name="lessonId" value="${lesson.lessonid}" />
                                <button type="submit" class="btn btn-primary">Learn</button>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="mt-2">
                <h4>Exam</h4>
                <div class="lesson-list" style="max-height: 300px; overflow-y: auto; border: 1px solid #ddd; padding: 10px; border-radius: 5px;">
                    <c:forEach var="quiz" items="${quizzes}">
                        <div class="lesson-item mb-3">
                            <h5 class="lesson-name" style="font-size: 1.2rem; color: #007bff; cursor: pointer; transition: color 0.3s;">
                                <c:out value="${quiz.name}"/>
                            </h5>
                            <p><strong>Description:</strong> <c:out value="${quiz.description}"/></p>
                            <p><strong>Minimum Score:</strong> <c:out value="${quiz.miniumscore}"/></p>
                            <p><strong>Content:</strong> <c:out value="${quiz.content}"/></p>
                        </div>
                    </c:forEach>
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
