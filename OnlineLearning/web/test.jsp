<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page with Sidebar and Content</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Styles -->
    <style>
        /* Layout for Sidebar and Content */
        .container-fluid {
            display: flex;
            min-height: 100vh;
        }

        /* Sidebar styles */
        .sidebar {
            width: 250px;
            background-color: #343a40;
            color: #fff;
            padding-top: 20px;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
        }

        .sidebar a {
            display: block;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
        }

        .sidebar a:hover {
            background-color: #495057;
        }

        /* Content styles */
        .content {
            flex-grow: 1;
            padding: 20px;
        }

        /* Footer */
        footer {
            background-color: #212529;
            color: white;
            text-align: center;
            padding: 15px 0;
        }
    </style>
</head>

<body>

    <!-- Main Container -->
    <div class="container-fluid">
        <!-- Sidebar Start -->
        <div class="sidebar">
            <h4 class="text-center text-white">Sidebar</h4>
            <a href="#">Dashboard</a>
            <a href="#">Posts</a>
            <a href="#">Settings</a>
            <a href="#">Profile</a>
        </div>
        <!-- Sidebar End -->

        <!-- Content Start -->
        <div class="content">
            <h1>Welcome to the Content Section</h1>
            <p>This is the main content area of the page. Here you can display any content you want like posts, articles, or images.</p>

            <div>
                <h3>Latest Posts</h3>
                <ul>
                    <c:forEach var="post" items="${latestPosts}">
                        <li>
                            <h5>${post.title}</h5>
                            <p>By: ${post.authorName}</p>
                            <p>${post.content}</p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <!-- Content End -->
    </div>
    <!-- Main Container End -->

   

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
