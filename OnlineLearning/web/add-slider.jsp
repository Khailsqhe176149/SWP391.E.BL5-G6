<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Slider</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
    <!-- Navbar Start -->
    <jsp:include page="templates/navbar.jsp" />
    <!-- Navbar End -->

    <div class="container my-5">
        <h1 class="h3 mb-4">Add New Slider</h1>

        <form action="slider-management" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="addSlider">
            <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="4" required></textarea>
            </div>
            <div class="mb-3">
                <label for="img" class="form-label">Image</label>
                <input type="file" class="form-control" id="img" name="img">
            </div>
            <div class="mb-3">
                <label for="status" class="form-label">Status</label>
                <select class="form-select" id="status" name="status">
                    <option value="1">Active</option>
                    <option value="0">Inactive</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="slidercategoryid" class="form-label">Slider Category:</label>
                <select name="slidercategoryid" class="form-select">
                    <option value="1" ${slider.getSlidercategoryid() == 1 ? 'selected' : ''}>Introduction</option>
                    <option value="2" ${slider.getSlidercategoryid() == 2 ? 'selected' : ''}>News Course</option>
                    <option value="3" ${slider.getSlidercategoryid() == 3 ? 'selected' : ''}>News</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Add Slider</button>
        </form>
    </div>
 <!-- Footer Start -->
            <jsp:include page="templates/footer.jsp" />
        <!-- Footer End -->
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
