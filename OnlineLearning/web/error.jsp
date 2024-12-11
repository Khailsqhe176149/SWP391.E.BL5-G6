<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-danger">Error</h2>
        <div class="alert alert-danger">
            <strong>Error:</strong> ${error}
        </div>
        <a href="ListCourses.jsp" class="btn btn-primary">Back to Courses List</a>
    </div>
</body>
</html>
