<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>eLEARNING - eLearning HTML Template</title>
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
    </head>

    <body>
        <!-- navbar.jsp -->
        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
            <a href="index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
                <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>eLEARNING</h2>
            </a>
            <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto p-4 p-lg-0">
                    <a href="index.html" class="nav-item nav-link ">Home</a>
                    <a href="about.html" class="nav-item nav-link active">About</a>
                    <a href="courses.html" class="nav-item nav-link">Courses</a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                        <div class="dropdown-menu fade-down m-0">
                            <a href="team.html" class="dropdown-item">Our Team</a>
                            <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                            <a href="404.html" class="dropdown-item">404 Page</a>
                        </div>
                    </div>
                    <a href="contact.html" class="nav-item nav-link">Contact</a>
                </div>
                <a href="" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">Join Now<i class="fa fa-arrow-right ms-3"></i></a>
            </div>
        </nav>

        <!-- Sidebar and Main Content Start -->
        <div class="container-fluid" style="display: flex; min-height: 100vh;">

       <!-- Sidebar -->
<div class="sidebar bg-light" style="width: 700px; padding-top: 20px;">
<!-- Sidebar với danh sách môn học -->
<div class="accordion" id="subjectAccordion">
    <div class="accordion-item">
        <h2 class="accordion-header" id="headingOne">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseMath" aria-expanded="true" aria-controls="collapseMath">
                <i class="subject-link"></i> Subjects
            </button>
        </h2>
        <div id="collapseMath" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#subjectAccordion">
            <div class="accordion-body">
                <form action="listCourses" method="get" id="subjectFilterForm">
                    <div class="subject-items">
                        <!-- Lặp qua các môn học -->
                        <c:forEach var="subject" items="${subjects}">
                            <div class="subject-item">
                                <input class="form-check-input" 
                                       type="checkbox" 
                                       name="subject" 
                                       value="${subject}" 
                                       id="subject-${subject}"
                                       <c:if test="${param.subject != null && param.subject == subject}">checked</c:if>
                                       onchange="handleSubjectSelection(this);">
                                <label class="form-check-label" for="subject-${subject}">
                                    ${subject}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

                
                
<!-- Accordion Price Filter -->
<div class="accordion" id="accordionPriceFilter">
    <div class="accordion-item">
        <h2 class="accordion-header" id="headingPrice">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapsePrice" aria-expanded="true" aria-controls="collapsePrice">
                Price Filter
            </button>
        </h2>
        <div id="collapsePrice" class="accordion-collapse collapse show" aria-labelledby="headingPrice" data-bs-parent="#accordionPriceFilter">
            <div class="accordion-body">
                <!-- Filter Form -->
                <form id="priceFilterForm" method="GET" action="YourServletURL"> <!-- Đổi 'YourServletURL' thành URL Servlet của bạn -->
                    <!-- Price Filter Options -->
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="price" id="free" value="free" onclick="applyFilter('free')" />
                        <label class="form-check-label" for="free">
                            Free (Miễn phí)
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="price" id="paid" value="paid" onclick="applyFilter('paid')" />
                        <label class="form-check-label" for="paid">
                            Paid (Có phí)
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="price" id="under100" value="under100" onclick="applyFilter('under100')" />
                        <label class="form-check-label" for="under100">
                            Under $100
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="price" id="100to500" value="100to500" onclick="applyFilter('100to500')" />
                        <label class="form-check-label" for="100to500">
                            $100 - $500
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="price" id="above500" value="above500" onclick="applyFilter('above500')" />
                        <label class="form-check-label" for="above500">
                            Above $500
                        </label>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

    <!-- Sort By Section -->
    <div class="accordion-item">
        <h2 class="accordion-header" id="headingSortBy">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSortBy" aria-expanded="true" aria-controls="collapseSortBy">
                Sort By
            </button>
        </h2>
        <div id="collapseSortBy" class="accordion-collapse collapse show" aria-labelledby="headingSortBy" data-bs-parent="#accordionFilters">
            <div class="accordion-body">
                <form action="listCourses" method="get" id="sortByForm">
                    <!-- Sort Options -->
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="sort" id="az" value="az" onclick="this.form.submit();" />
                        <label class="form-check-label" for="az">
                            A - Z
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="sort" id="latest" value="latest" onclick="this.form.submit();" />
                        <label class="form-check-label" for="latest">
                            Latest (Mới nhất)
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="sort" id="oldest" value="oldest" onclick="this.form.submit();" />
                        <label class="form-check-label" for="oldest">
                            Oldest (Cũ nhất)
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="sort" id="mostRegistrations" value="mostRegistrations" onclick="this.form.submit();" />
                        <label class="form-check-label" for="mostRegistrations">
                            Most Registrations (Đăng ký nhiều nhất)
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="sort" id="mostLessons" value="mostLessons" onclick="this.form.submit();" />
                        <label class="form-check-label" for="mostLessons">
                            Has the largest number of lessons (Có số bài học lớn nhất)
                        </label>
                    </div>
                </form>
            </div>
        </div>
    </div>
         
            </div>






           
            <!-- Main Content -->
            <div class="main-content flex-grow-1 p-5">
                <h2>Welcome to eLearning</h2>
                <p>This is the main content area.</p>

                <!-- Example Course List -->
                <div class="row">
                    <!-- Lặp qua danh sách khóa học và hiển thị từng khóa học -->
                    <c:forEach var="course" items="${courses}">
                        <div class="col-md-4">
                            <div class="card">
                                <!-- Hiển thị ảnh khóa học -->
                                <img src="<c:out value='img/${course.img}' />" class="card-img-top" alt="${course.name}">

                                <div class="card-body d-flex justify-content-between align-items-center">
                                    <div>
                                        <h5 class="card-title"><c:out value="${course.name}" /></h5>
                                        <p class="card-text"><c:out value="${course.description}" /></p>
                                        <!-- Giá khóa học -->
                                        <p class="card-price text-success me-3 mb-0"><strong><c:out value="${course.price}" /></strong></p>
                                    </div>
                                    <div class="d-flex align-items-center">
                                        <!-- Nút View Course -->
                                        <a href="courseDetail?courseId=<c:out value='${course.courseId}' />" class="btn btn-primary">View Course</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <!-- Phân trang -->
                <div class="d-flex justify-content-center mt-4">
                    <ul class="pagination">
                        <!-- Previous page -->
                        <c:if test="${pageIndex > 1}">
                            <li class="page-item">
                                <a class="page-link" href="?pageIndex=${pageIndex - 1}&subject=${param.subject}">Previous</a>
                            </li>
                        </c:if>

                        <!-- Hiển thị các trang -->
                        <c:forEach var="i" begin="1" end="${totalPages}" step="1">
                            <li class="page-item <c:if test='${i == pageIndex}'>active</c:if>">
                                <a class="page-link" href="?pageIndex=${i}&subject=${param.subject}">${i}</a>
                            </li>
                        </c:forEach>

                        <!-- Next page -->
                        <c:if test="${pageIndex < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="?pageIndex=${pageIndex + 1}&subject=${param.subject}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>






        </div>
    </div>
    <!-- Sidebar and Main Content End -->

    <!-- Footer -->
    <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container py-5">
            <div class="row g-5">
                <div class="col-lg-3 col-md-6">
                    <h4 class="text-white mb-3">Quick Link</h4>
                    <a class="btn btn-link" href="">About Us</a>
                    <a class="btn btn-link" href="">Contact Us</a>
                    <a class="btn btn-link" href="">Privacy Policy</a>
                    <a class="btn btn-link" href="">Terms & Condition</a>
                    <a class="btn btn-link" href="">FAQs & Help</a>
                </div>
                <div class="col-lg-3 col-md-6">
                    <h4 class="text-white mb-3">Contact</h4>
                    <p class="mb-2"><i class="fa fa-map-marker-alt me-3"></i>123 Street, New York, USA</p>
                    <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>+012 345 67890</p>
                    <p class="mb-2"><i class="fa fa-envelope me-3"></i>info@example.com</p>
                </div>
            </div>
        </div>
    </div>

<script>
    function handleSubjectSelection(checkbox) {
        const checkboxes = document.querySelectorAll('input[name="subject"]');
        
        // Nếu checkbox được chọn, bỏ chọn tất cả các checkbox khác
        if (checkbox.checked) {
            checkboxes.forEach(function (item) {
                if (item !== checkbox) {
                    item.disabled = true;  // Vô hiệu hóa các checkbox khác
                }
            });
        } else {
            // Nếu checkbox được bỏ chọn, kích hoạt lại tất cả các checkbox
            checkboxes.forEach(function (item) {
                item.disabled = false;
            });
        }

        // Gửi form khi thay đổi lựa chọn
        document.getElementById("subjectFilterForm").submit();
    }
</script>


</body>
</html>
