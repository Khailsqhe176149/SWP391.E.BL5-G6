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
<div class="sidebar bg-light" style="width: 280px; padding-top: 20px;">
    <!-- Accordion for Subjects -->
    <div class="accordion" id="subjectAccordion">
        <!-- Math Category -->
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingOne">
                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseMath" aria-expanded="true" aria-controls="collapseMath">
                    <i class="bi bi-calculator me-2"></i> Math
                </button>
            </h2>
            <div id="collapseMath" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#subjectAccordion">
                <div class="accordion-body">
                    <div class="subject-items">
                        <div class="subject-item"><a href="#" class="subject-link">Algebra</a></div>
                        <div class="subject-item"><a href="#" class="subject-link">Geometry</a></div>
                        <div class="subject-item"><a href="#" class="subject-link">Calculus</a></div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Geography Category -->
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseGeography" aria-expanded="false" aria-controls="collapseGeography">
                    <i class="bi bi-globe me-2"></i> Geography
                </button>
            </h2>
            <div id="collapseGeography" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#subjectAccordion">
                <div class="accordion-body">
                    <div class="subject-items">
                        <div class="subject-item"><a href="#" class="subject-link">World Geography</a></div>
                        <div class="subject-item"><a href="#" class="subject-link">Physical Geography</a></div>
                        <div class="subject-item"><a href="#" class="subject-link">Human Geography</a></div>
                    </div>
                </div>
            </div>
        </div>

        <!-- History Category -->
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingThree">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseHistory" aria-expanded="false" aria-controls="collapseHistory">
                    <i class="bi bi-book-half me-2"></i> History
                </button>
            </h2>
            <div id="collapseHistory" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#subjectAccordion">
                <div class="accordion-body">
                    <div class="subject-items">
                        <div class="subject-item"><a href="#" class="subject-link">Ancient History</a></div>
                        <div class="subject-item"><a href="#" class="subject-link">Modern History</a></div>
                        <div class="subject-item"><a href="#" class="subject-link">World Wars</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



        <!-- Main Content -->
        <div class="main-content flex-grow-1 p-4">
            <h2>Welcome to eLearning</h2>
            <p>This is the main content area.</p>

            <!-- Example Course List -->
            <div class="row">
                <div class="col-md-4">
                    <div class="card">
                        <img src="img/course-1.jpg" class="card-img-top" alt="Course 1">
                        <div class="card-body">
                            <h5 class="card-title">Course Title 1</h5>
                            <p class="card-text">This is a short description of the course.</p>
                            <a href="#" class="btn btn-primary">View Course</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <img src="img/course-2.jpg" class="card-img-top" alt="Course 2">
                        <div class="card-body">
                            <h5 class="card-title">Course Title 2</h5>
                            <p class="card-text">This is a short description of the course.</p>
                            <a href="#" class="btn btn-primary">View Course</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <img src="img/course-3.jpg" class="card-img-top" alt="Course 3">
                        <div class="card-body">
                            <h5 class="card-title">Course Title 3</h5>
                            <p class="card-text">This is a short description of the course.</p>
                            <a href="#" class="btn btn-primary">View Course</a>
                        </div>
                    </div>
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
</body>
</html>
