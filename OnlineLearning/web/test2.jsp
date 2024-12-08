<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lesson Videos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .video-list {
            list-style-type: none;
            padding: 0;
        }
        .video-item {
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .video-title {
            font-size: 1.5em;
            color: #333;
        }
        .video-description {
            font-size: 1em;
            color: #666;
        }
        .video-link {
            font-size: 1em;
            color: #007bff;
            text-decoration: none;
        }
        .video-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Lesson Videos</h1>
    
    <!-- Kiểm tra xem có video nào không -->
    <c:if test="${empty videos}">
        <p>No videos found for this lesson.</p>
    </c:if>

    <!-- Lặp qua danh sách video và hiển thị thông tin -->
    <ul class="video-list">
        <c:forEach var="video" items="${videos}">
            <li class="video-item">
                <h3 class="video-title">${video.videoTitle}</h3>
                <p class="video-description">${video.description}</p>
                <a class="video-link" href="${video.videoURL}" target="_blank">Watch Video</a>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
