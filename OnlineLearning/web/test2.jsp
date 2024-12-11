<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Add New Course</title>
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
        /* Sidebar Styling */
        .sidebar {
            width: 300px; /* Set width for sidebar */
            height: 100vh; /* Set height to full viewport height */
            overflow-y: auto; /* Enable vertical scroll if content exceeds height */
            padding-top: 20px;
        }

        .badge-success {
            background-color: #28a745 !important; /* Màu xanh cho Active */
            color: white !important; /* Chữ màu trắng */
        }

        .badge-danger {
            background-color: #dc3545 !important; /* Màu đỏ cho Inactive */
            color: white !important; /* Chữ màu trắng */
        }

        .badge {
            padding: 0.5em 0.75em;  /* Khoảng cách bên trong */
            border-radius: 0.25rem; /* Góc bo tròn */
            font-size: 0.875rem;    /* Kích thước chữ */
            font-weight: bold;      /* Đậm chữ */
        }

        /* Custom Styling for the Form */
        .form-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .form-label {
            font-weight: bold;
        }

        .form-control, .form-select, .form-textarea {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ced4da;
        }

        .form-textarea {
            height: 150px;
            resize: vertical;
        }

        .btn-submit {
            width: 100%;
            padding: 10px;
            font-size: 1.1rem;
            font-weight: bold;
        }

        .error-message {
            color: red;
            font-weight: bold;
            margin-top: 15px;
        }

    </style>
</head>

<body class="bg-light">
    <!-- Navbar Start -->
    <jsp:include page="templates/navbar.jsp" />
    <!-- Navbar End -->

    <div class="container-fluid" style="display: flex; min-height: 100vh;">
        <!-- Sidebar Start -->
        <jsp:include page="templates/sidebar.jsp" />
        <!-- Sidebar End -->

        <!-- Main Content Start -->
        <div class="col-md-9 ps-4">
            <div class="form-container">
                <h2 class="text-center mb-4">Add New Course</h2>

                <!-- Form Start -->
                <form action="AddCourses" method="post">

                    <!-- Course Name -->
                    <div class="mb-3">
                        <label for="name" class="form-label">Course Name:</label>
                        <input type="text" id="name" name="name" class="form-control" required>
                    </div>

                    <!-- Subject Dropdown -->
                    <div class="mb-3">
                        <label for="subjectid" class="form-label">Subject:</label>
                        <select id="subjectid" name="subjectid" class="form-select" required>
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.subjectid}">${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Price -->
                    <div class="mb-3">
                        <label for="price" class="form-label">Price:</label>
                        <input type="number" id="price" name="price" class="form-control" required min="0" step="0.01">
                    </div>

                    <!-- Author ID (Pre-populated from session) -->
                    <div class="mb-3">
                        <label for="authorid" class="form-label">Author ID:</label>
                        <input type="number" id="authorid" name="authorid" class="form-control" value="${authorid}" readonly>
                    </div>

                    <!-- Description -->
                    <div class="mb-3">
                        <label for="description" class="form-label">Description:</label>
                        <textarea id="description" name="description" class="form-textarea" required></textarea>
                    </div>

                    <!-- Tag -->
                    <div class="mb-3">
                        <label for="tag" class="form-label">Tag:</label>
                        <input type="text" id="tag" name="tag" class="form-control">
                    </div>

                    <!-- Submit Button -->
                    <div class="mb-3">
                        <button type="submit" class="btn btn-primary btn-submit">Add Course</button>
                    </div>

                </form>

                <!-- Error message -->
                <c:if test="${not empty error}">
                    <div class="error-message">${error}</div>
                </c:if>
            </div>
        </div>
        <!-- Main Content End -->
    </div>

    <!-- Footer Start -->
    <jsp:include page="templates/footer.jsp" />
    <!-- Footer End -->
</body>
</html>
