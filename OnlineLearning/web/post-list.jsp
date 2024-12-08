<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Post Management</title>

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
        <h1 class="h3 mb-4">Post Management</h1>

        <form action="post-management" method="get" class="mb-4">
            <div class="row g-3">
                <div class="col-md-6">
                    <input type="text" name="search" placeholder="Search by title" value="${param.search}" class="form-control" />
                </div>
                <div class="col-md-4">
                    <select name="status" class="form-select">
                        <option value="">Select Status</option>
                        <option value="1" ${param.status == '1' ? 'selected' : ''}>Active</option>
                        <option value="0" ${param.status == '0' ? 'selected' : ''}>Inactive</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary w-100">Search</button>
                </div>
            </div>
        </form>

        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Content</th>
                    <th>Image</th>
                    <th>Status</th>
                    <th>Slider Code</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="post" items="${posts}">
                    <tr>
                        <td>${post.getTitle()}</td>
                        <td>${post.getContent()}</td>
                        <td>
                            <img src="${post.getImg()}" alt="Post Image" class="img-fluid" width="50" height="50" />
                        </td>
                        <td>${post.getStatus() == 1 ? 'Active' : 'Inactive'}</td>
                        <td>${post.getSliderid()}</td>
                        <td>
                            <a href="post-management?action=edit&postId=${post.getPostid()}" class="btn btn-warning btn-sm">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
