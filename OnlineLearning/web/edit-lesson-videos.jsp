<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Edit Video</title>
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

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">

        <style>
            .form-group {
                margin-bottom: 1rem;
            }
        </style>
    </head>

    <body>
        <!-- Navbar Start -->
        <jsp:include page="templates/navbar.jsp" />
        <!-- Navbar End -->

        <!-- Sidebar and Main Content Start -->
        <div class="container-fluid" style="display: flex; min-height: 100vh;">
            <!-- Sidebar -->
            <jsp:include page="templates/sidebar.jsp" />

            <!-- Main Content -->
            <div class="col-md-9 ps-4">
                <h2>Edit Video</h2>

                <!-- Form for editing video -->
                <form action="EditLessonVideo" method="POST">
                    <input type="hidden" name="videoId" value="${lessonVideo.videoId}">
                    <input type="hidden" name="lessonId" value="${lessonId}">

                    <div class="form-group">
                        <label for="videoTitle">Video Title</label>
                        <input type="text" class="form-control" id="videoTitle" name="videoTitle" value="${lessonVideo.videoTitle}" required>
                    </div>

                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea class="form-control" id="description" name="description" required>${lessonVideo.description}</textarea>
                    </div>

                    <div class="form-group">
                        <label for="videoURL">Video URL</label>
                        <input type="url" class="form-control" id="videoURL" name="videoURL" value="${lessonVideo.videoURL}" required>
                    </div>

                    <a href="ListLesson" class="btn btn-secondary mt-3">Back to List</a>
                    <button type="submit" class="btn btn-primary mt-3">Update Video</button>
                </form>
                     <img src="img/guilde1.jpg" width="width" height="height" alt="alt"/>
                     <img src="img/guilde2.jpg" width="width" height="height" alt="alt"/>
            </div>
        </div>

        <!-- Footer Start -->
        <jsp:include page="templates/footer.jsp" />
        <!-- Footer End -->
    </body>
</html>
