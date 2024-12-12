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
        <!-- DataTables CSS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/jquery.dataTables.min.css">
        <!-- DataTables JS -->
        <script src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>



        <style>
            .sidebar {
                width: 300px; /* Set width for sidebar */
                height: 100vh; /* Set height to full viewport height */
                overflow-y: auto; /* Enable vertical scroll if content exceeds height */
                padding-top: 20px;
            }
            .badge-success {
                background-color: #28a745 !important; /* Màu xanh cho Active */
                color: white !important;             /* Chữ màu trắng */
            }

            .badge-danger {
                background-color: #dc3545 !important; /* Màu đỏ cho Inactive */
                color: white !important;              /* Chữ màu trắng */
            }

            .badge {
                padding: 0.5em 0.75em;  /* Khoảng cách bên trong */
                border-radius: 0.25rem; /* Góc bo tròn */
                font-size: 0.875rem;    /* Kích thước chữ */
                font-weight: bold;      /* Đậm chữ */
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

            <jsp:include page="templates/sidebar.jsp" />




            <!-- Main Content -->
            <div class="col-md-9 ps-4">
                <h1 class="mb-4">Manage Courses</h1>
                <table class="table table-striped" id="coursesTable">
                    <thead>
                        <tr>
                            <th>Course ID</th>
                            <th>Course Name</th>
                            <th>Subject</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="course" items="${courses}">
                            <tr>
                                <td>${course.courseId}</td>
                                <td>${course.name}</td>
                                <td>${course.subjectName}</td>
                                <td>
                                    <span class="badge ${course.status == 1 ? 'badge-success' : 'badge-danger'}">
                                        ${course.status == 1 ? 'Active' : 'Inactive'}
                                    </span>
                                </td>
                                <td>
                                    <a href="addLesson?courseId=${course.courseId}" class="btn btn-primary">Add Lesson</a>
                                    <button class="btn btn-info" data-bs-toggle="modal" data-bs-target="#lessonModal${course.courseId}">View Lessons</button>
                                </td>



                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <c:forEach var="course" items="${courses}">
                    <!-- Modal -->
                    <div class="modal fade" id="lessonModal${course.courseId}" tabindex="-1" aria-labelledby="lessonModalLabel${course.courseId}" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="lessonModalLabel${course.courseId}">Lessons in Course: ${course.name}</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>Lesson ID</th>
                                                <th>Lesson Name</th>
                                                <th>Description</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="lesson" items="${course.lessons}">
                                                <tr>
                                                    <td>${lesson.lessonid}</td>
                                                    <td>${lesson.name}</td>
                                                    <td>${lesson.description}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>


        <!-- Footer Start -->
        <jsp:include page="templates/footer.jsp" />
        <!-- Footer End -->
        <!-- jQuery script to handle button actions -->

     
        <script>
            $(document).ready(function () {

                $('.table').DataTable({
                    paging: true,
                    searching: true,
                    ordering: true,
                    lengthChange: true,
                    info: true,
                    language: {
                        search: "Search Course:",
                        lengthMenu: "Show _MENU_ courses per page",
                        info: "Showing _START_ to _END_ of _TOTAL_ courses",
                        paginate: {
                            previous: "Previous",
                            next: "Next"
                        }
                    }
                });
            });
        </script>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
