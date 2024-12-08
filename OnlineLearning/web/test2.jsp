<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quizzes</title>
        <style>
            .quiz-container {
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }
            .quiz-item {
                padding: 15px;
                margin-bottom: 15px;
                background-color: #fff;
                border-radius: 5px;
                border: 1px solid #ddd;
            }
            .quiz-item h5 {
                font-size: 1.2rem;
                color: #007bff;
                margin-bottom: 10px;
            }
            .quiz-item p {
                margin: 5px 0;
                font-size: 1rem;
            }
        </style>
    </head>
    <body>
        <div class="quiz-container">
            <h2>Quizzes</h2>
            <c:forEach var="quiz" items="${quizzes}">
                <div class="quiz-item">
                    <h5><c:out value="${quiz.name}"/></h5>
                    <p><strong>Description:</strong> <c:out value="${quiz.description}"/></p>
                    <p><strong>Minimum Score:</strong> <c:out value="${quiz.miniumscore}"/></p>
                    <p><strong>Content:</strong> <c:out value="${quiz.content}"/></p>
                </div>
            </c:forEach>

        </div>
    </body>
</html>
