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
                <h1 class="mb-4">Edit Lesson</h1>

                <!-- Error message display -->
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">
                        <strong>Error:</strong> ${errorMessage}
                    </div>
                </c:if>

                <!-- Form to edit lesson -->
                <form action="EditLesson" method="POST">
                    <div class="mb-3">
                        <label for="lessonId" class="form-label">Lesson ID</label>
                        <input type="text" class="form-control" id="lessonId" name="lessonId" value="${lesson.lessonid}" readonly>
                    </div>

                    <div class="mb-3">
                        <label for="name" class="form-label">Lesson Name</label>
                        <input type="text" class="form-control" id="name" name="name" value="${lesson.name}" required>
                    </div>

                    <div class="mb-3">
                        <label for="content" class="form-label">Content</label>
                        <textarea class="form-control" id="content" name="content" rows="3" required>${lesson.content}</textarea>
                    </div>

                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <textarea class="form-control" id="description" name="description" rows="3" required>${lesson.description}</textarea>
                    </div>

                    <div class="mb-3">
                        <label for="date" class="form-label">Date</label>
                        <input type="date" class="form-control" id="date" name="date" value="${lesson.date != null ? lesson.date.toString().substring(0, 10) : ''}" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Update Lesson</button>
                </form>


                <!-- Back to Lessons List -->
                <a href="ListLesson" class="btn btn-secondary mt-3">Back to List</a>
            </div>

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
