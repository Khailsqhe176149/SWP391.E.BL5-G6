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
                <h2>Add New Video</h2>

                <!-- Form for adding video -->
                <form action="AddLessonVideo" method="POST">
                    <input type="hidden" name="lessonId" value="${lessonId}">

                    <div class="form-group">
                        <label for="videoTitle">Video Title</label>
                        <input type="text" class="form-control" id="videoTitle" name="videoTitle" required>
                    </div>

                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea class="form-control" id="description" name="description" required></textarea>
                    </div>

                    <div class="form-group">
                        <label for="videoURL">Video URL</label>
                        <input type="url" class="form-control" id="videoURL" name="videoURL" required>
                    </div>
                    <a href="ListLesson" class="btn btn-secondary mt-3">Back to List</a>
                    <button type="submit" class="btn btn-primary mt-3">Add Video</button>
                </form>
                      <img src="img/guilde1.jpg" width="width" height="height" alt="alt"/>
                     <img src="img/guilde2.jpg" width="width" height="height" alt="alt"/>
                
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
