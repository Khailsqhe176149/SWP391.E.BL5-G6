<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Slider</title>

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
        <h1 class="h3 mb-4">Edit Slider</h1>

        <form action="slider-management" method="post" enctype="multipart/form-data" class="card p-4">
            <input type="hidden" name="action" value="updateSlider" />
            <input type="hidden" name="sliderId" value="${slider.getSliderid()}" />

            <div class="mb-3">
                <label for="title" class="form-label">Title:</label>
                <input type="text" name="title" value="${slider.getTitle()}" class="form-control" required />
            </div>

            <div class="mb-3">
                <label for="description" class="form-label">Description:</label>
                <textarea name="description" rows="5" class="form-control" required>${slider.getDescription()}</textarea>
            </div>

            <div class="mb-3">
                <label for="img" class="form-label">Choose Image:</label>
                <input type="file" id="img" name="img" accept="image/*" class="form-control" />
            </div>

            <div class="mb-3">
                <label for="status" class="form-label">Status:</label>
                <select name="status" class="form-select">
                    <option value="1" ${slider.getStatus() == 1 ? 'selected' : ''}>Active</option>
                    <option value="0" ${slider.getStatus() == 0 ? 'selected' : ''}>Inactive</option>
                </select>
            </div>

            <!-- Slider Category Dropdown -->
            <div class="mb-3">
                <label for="slidercategoryid" class="form-label">Slider Category:</label>
                <select name="slidercategoryid" class="form-select">
                    <option value="1" ${slider.getSlidercategoryid() == 1 ? 'selected' : ''}>Introduction</option>
                    <option value="2" ${slider.getSlidercategoryid() == 2 ? 'selected' : ''}>News Course</option>
                    <option value="3" ${slider.getSlidercategoryid() == 3 ? 'selected' : ''}>News</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Update Slider</button>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
