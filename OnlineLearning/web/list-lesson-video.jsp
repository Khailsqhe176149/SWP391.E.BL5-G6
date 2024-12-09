<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Lesson Videos</title>
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

        <style>
            .container-fluid {
                display: flex;
                min-height: 100vh;
            }

            .sidebar {
                width: 200px; /* Giảm chiều rộng của sidebar */
                height: 100vh;
                overflow-y: auto;
                padding-top: 20px;
                border-right: 1px solid #ddd; /* Thêm đường viền bên phải */
            }

            .video-item {
                padding: 10px;
                cursor: pointer;
                border-bottom: 1px solid #ddd;
                transition: background-color 0.3s;
            }

            .video-item:hover {
                background-color: #f8f9fa;
            }

            .video-content {
                flex: 1;
                padding: 20px;
                text-align: center;
            }

            .video-content h3 {
                font-size: 24px;
                margin-bottom: 10px; /* Đặt khoảng cách dưới tiêu đề */
            }

            .video-content video {
                width: 100%; /* Đảm bảo video chiếm hết chiều rộng của container */
                height: auto; /* Tự động điều chỉnh chiều cao để giữ tỷ lệ */
                max-width: 800px; /* Giới hạn chiều rộng tối đa của video */
                max-height: 500px; /* Giới hạn chiều cao tối đa của video */
                margin: 20px 0; /* Căn giữa và tạo khoảng cách trên dưới cho video */
            }

            .video-content p {
                font-size: 16px;
                color: #555;
                line-height: 1.6;
                margin-top: 15px; /* Khoảng cách trên cho mô tả */
            }
        </style>
    </head>

    <body>
        <!-- Navbar Start -->
        <jsp:include page="templates/navbar.jsp" />
        <!-- Navbar End -->

        <!-- Sidebar and Main Content Start -->
        <!-- Sidebar and Main Content Start -->
        <div class="container-fluid">
            <!-- Sidebar (Left Column) -->
            <div class="sidebar bg-light">

                <h3> View Lesson</h3>
                <!-- Accordion for Video List -->
                <div class="accordion" id="videoAccordion">
                    <!-- Accordion Item for Video List -->
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingVideos">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseVideos" aria-expanded="true" aria-controls="collapseVideos">
                                Video List
                            </button>
                        </h2>
                        <div id="collapseVideos" class="accordion-collapse collapse show" aria-labelledby="headingVideos" data-bs-parent="#videoAccordion">
                            <div class="accordion-body">
                                <!-- List of videos -->
                                <ul class="list-unstyled">
                                    <c:forEach var="video" items="${videos}">
                                        <li class="video-item">
                                            <!-- Form to submit videoId and lessonId -->
                                            <form action="ListLessonVideo" method="post">
                                                <input type="hidden" name="videoId" value="${video.videoId}">
                                                <input type="hidden" name="lessonId" value="${lessonId}"> 
                                                <input type="hidden" name="courseId" value="${courseId}">
                                                <!-- Button with video icon and title -->
                                                <button type="submit" class="btn btn-link">
                                                    <!-- Video Icon (Font Awesome) -->
                                                    <i class="fas fa-video" style="margin-right: 10px;"></i> 
                                                    ${video.videoTitle}
                                                </button>
                                            </form>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Accordion Item for Lesson Readings -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingReadings">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseReadings" aria-expanded="false" aria-controls="collapseReadings">
                            Related Documents
                        </button>
                    </h2>
                    <div id="collapseReadings" class="accordion-collapse collapse" aria-labelledby="headingReadings" data-bs-parent="#lessonAccordion">
                        <div class="accordion-body">
                            <!-- List of lesson readings -->
                          
                            <ul class="list-unstyled">
                                <c:forEach var="reading" items="${lessonReadings}">
                                    <li>
                                        
                                        <a href="${reading.readingURL}" target="_blank"><i class="fas fa-link" style="margin-right: 10px; color: #06BBCC"></i>${reading.title}</a>
                                         
                                        <p>${reading.description}</p>
                                    </li>
                                </c:forEach>
                            </ul>

                        </div>
                    </div>
                </div>


                <form action="ViewMyCoursesDetail" method="get" style="display: flex; justify-content: center; margin-top: 10px;">
                    <input type="hidden" name="courseId" value="${courseId}">
                    <button type="submit" class="btn btn-primary" style="width: 200px;">
                        Back to Course
                    </button>
                </form>


            </div>

            <!-- Main Content (Right Column) -->
            <div class="video-content">
                <!-- Video Title above the video -->
                <h3>${videoTitle}</h3>

                <!-- Video will be displayed here -->
                <video id="videoPlayer" controls>
                    <source id="videoSource" src="video/${videoURL}" type="video/mp4">
                    Your browser does not support the video tag.
                </video>

                <!-- Video Description below the video -->
                <p style="font-size: 18px; color: #333; line-height: 1.6; margin-top: 20px;">
                    ${videoDescription}
                </p>
            </div>
        </div>
    </div>



    <!-- Footer Start -->
    <jsp:include page="templates/footer.jsp" />
    <!-- Footer End -->
</body>
</html>
