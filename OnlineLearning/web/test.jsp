<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Course</title>
    <link rel="stylesheet" href="styles.css"> <!-- Optional: Link your CSS -->
</head>
<body>

    <h2>Add New Course</h2>

    <form action="AddCourses" method="post">

        <!-- Course Name -->
        <label for="name">Course Name:</label>
        <input type="text" id="name" name="name" required>

        <br><br>

        <!-- Subject Dropdown -->
        <label for="subjectid">Subject:</label>
        <select id="subjectid" name="subjectid" required>
            <c:forEach var="subject" items="${subjects}">
                <option value="${subject.subjectid}">${subject.name}</option>
            </c:forEach>
        </select>

        <br><br>

        <!-- Price -->
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" required min="0" step="0.01">

        <br><br>

        <!-- Author ID (Pre-populated from session) -->
        <label for="authorid">Author ID:</label>
        <input type="number" id="authorid" name="authorid" value="${authorid}" readonly>

        <br><br>

        <!-- Description -->
        <label for="description">Description:</label>
        <textarea id="description" name="description" required></textarea>

        <br><br>

        <!-- Tag -->
        <label for="tag">Tag:</label>
        <input type="text" id="tag" name="tag">

        <br><br>

        <!-- Submit Button -->
        <input type="submit" value="Add Course">

    </form>

    <!-- Error message -->
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>

</body>
</html>
