
<%-- 
    Document   : test
    Created on : Nov 29, 2024, 3:04:39 AM
    Author     : Admin
--%>


<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="model.CountCourse" %>
<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Home</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

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

        <style>
            .fixed-size {
                width: 100%;
                height: 200px; /* Adjust as needed */
                object-fit: cover; /* Ensures the image scales while maintaining aspect ratio */
                background-color: #f4f4f4; /* Fallback background color for images */
            }
            .title-truncate {
                white-space: nowrap;        /* Prevents text from wrapping to the next line */
                overflow: hidden;           /* Hides overflowed text */
                text-overflow: ellipsis;    /* Adds "..." to indicate truncated text */
                display: block;             /* Ensures block-level behavior for proper truncation */
                max-width: 100%;            /* Ensures the truncation works based on the container size */
            }

        </style>
    </head>

    <body>
        <!-- Navbar Start  -->
        <jsp:include page="templates/navbar.jsp" />
        <!-- Navbar End  -->


        <!-- Slider Start -->
        <jsp:include page="partials/slider.jsp" />
        <!-- Slider End -->

        <!-- Popular Courses -->
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h6 class="section-title bg-white text-center text-primary px-3">Post</h6>
                    <h1 class="mb-5">List Post</h1>
                </div>
                <form method="get" action="post" class="mb-4">
                    <div class="row">
                        <div class="col-md-4">
                            <select name="c" class="form-select">
                                <option  <c:if test="${param.c == '' || param.c == null}">selected</c:if> value="">All Categories</option>
                                <c:forEach var="category" items="${categories}">
                                    <option value="${category.postCatPostidegoryId}" 
                                            <c:if test="${category.postCatPostidegoryId == param.c}">selected</c:if>>
                                        ${category.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <input type="text" name="search" class="form-control" placeholder="Search posts" 
                                   value="${searchParam}">
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-primary">Filter</button>
                        </div>
                    </div>
                </form>
                <div class="text-start mb-4">
                    <c:if test="${acc == null }">
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#notLoginModal">Add New Post</button>
                    </c:if>
                    <c:if test="${acc != null }">
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#newPostModal">Add New Post</button>
                    </c:if>

                </div>
                <c:if test="${not empty sessionScope.notification}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert" style="text-align: center;">
                        ${sessionScope.notification}
                        <button type="button" class="close" data-bs-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        session.removeAttribute("notification");
                    %>
                </c:if>
                <c:if test="${not empty sessionScope.notificationErr}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert" style="text-align: center;">
                        ${sessionScope.notificationErr}
                        <button type="button" class="close" data-bs-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        session.removeAttribute("notificationErr");
                    %>
                </c:if>
                <div class="row g-4">
                    <c:forEach var="post" items="${posts}" varStatus="status">
                        <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                            <div class="course-item bg-light">
                                <div class="position-relative overflow-hidden">
                                    <!-- Check if the post has an image -->
                                    <c:choose>
                                        <c:when test="${post.img != null}">
                                            <img class="img-fluid fixed-size" src="img/${post.img}" alt="${post.title}">
                                        </c:when>
                                        <c:otherwise>
                                            <img class="img-fluid fixed-size" src="img/placeholder.png" alt="${post.title}">
                                        </c:otherwise>
                                    </c:choose>

                                    <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
                                        <a href="post-detail?postId=${post.postid}" class="flex-shrink-0 btn btn-sm btn-primary px-3 border-end" 
                                           style="border-radius: 30px 0 0 30px;">Read More</a>
                                    </div>
                                </div>
                                <div class="text-center p-4 pb-0">
                                    <h5 class="mb-4 title-truncate">${post.title}</h5>
                                </div>

                                <div class="d-flex border-top">
                                    <small class="flex-fill text-center border-end py-2">
                                        <i class="fa fa-clock text-primary me-2"></i>
                                        <fmt:formatDate value="${post.createtime}" pattern="dd/MM/yyyy" />
                                    </small>
                                    <small class="flex-fill text-center py-2">
                                        <i class="fa fa-user text-primary me-2"></i>
                                        ${post.author.name}
                                    </small>
                                </div>
                            </div>
                        </div>
                        <c:if test="${status.index % 3 == 2}">
                        </div><div class="row g-4">
                        </c:if>
                    </c:forEach>
                </div>

                <!-- Pagination -->

            </div>

        </div>
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:forEach begin="1" end="${totalPages}" var="pageNumber">
                    <li class="page-item <c:if test='${pageNumber == currentPage}'>active</c:if>">
                        <a class="page-link" href="post?page=${pageNumber}&search=${searchParam}&c=${c}">${pageNumber}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>


        <!-- New Post Modal -->
        <div class="modal fade" id="newPostModal" tabindex="-1" aria-labelledby="newPostModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="newPostForm" enctype="multipart/form-data" method="POST">
                        <div class="modal-header">
                            <h5 class="modal-title" id="newPostModalLabel">Add New Post</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <!-- Category -->
                            <div class="mb-3">
                                <label for="category" class="form-label">Category</label>
                                <select name="categoryId" id="category" class="form-select">
                                    <option value="">Select Category</option>
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.postCatPostidegoryId}">${category.name}</option>
                                    </c:forEach>
                                </select>
                                <small class="text-danger" id="categoryError"></small>
                            </div>
                            <!-- Title -->
                            <div class="mb-3">
                                <label for="title" class="form-label">Title</label>
                                <input type="text" name="title" id="title" class="form-control">
                                <input type="hidden" name="action" value="add" class="form-control">
                                <small class="text-danger" id="titleError"></small>
                            </div>
                            <!-- Image -->
                            <div class="mb-3">
                                <label for="image" class="form-label">Image</label>
                                <input type="file" name="image" id="image" class="form-control">
                                <small class="text-danger" id="imageError"></small>
                            </div>
                            <!-- Content -->
                            <div class="mb-3">
                                <label for="content" class="form-label">Content</label>
                                <textarea name="content" id="content" class="form-control" rows="5"></textarea>
                                <small class="text-danger" id="contentError"></small>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save Post</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>

        <div class="modal fade" id="notLoginModal" tabindex="-1" aria-labelledby="notLoginModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title text-center" id="newPostModalLabel">Please Login First</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <span>Please <a class="fw-bold" href="Login"> Login</a> to add new post</span>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="templates/footer.jsp" />
            <!-- Footer End -->


            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>


            <!-- JavaScript Libraries -->
            <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
            <script src="lib/wow/wow.min.js"></script>
            <script src="lib/easing/easing.min.js"></script>
            <script src="lib/waypoints/waypoints.min.js"></script>
            <script src="lib/owlcarousel/owl.carousel.min.js"></script>

            <!-- Template Javascript -->
            <script src="js/main.js"></script>
            <script src="https://cdn.ckeditor.com/4.20.2/standard/ckeditor.js"></script>

            <!-- Initialize CKEditor -->
            <script>
                CKEDITOR.replace('content', {
                    toolbar: [
                        {name: 'basicstyles', items: ['Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript']},
                        {name: 'paragraph', items: ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent']},
                        {name: 'insert', items: ['Link', 'Unlink']},
                        {name: 'styles', items: ['Format']},
                        {name: 'tools', items: ['Maximize']}
                    ],
                    removeButtons: '',
                    height: 200
                });
                document.getElementById("newPostForm").onsubmit = function (event) {
                    // Prevent form submission for validation
                    let isValid = true;

                    // Get form fields
                    const category = document.getElementById("category");
                    const title = document.getElementById("title");
                    const content = document.getElementById("content");
                    const image = document.getElementById("image");

                    // Clear previous errors
                    document.getElementById("categoryError").innerText = "";
                    document.getElementById("titleError").innerText = "";
                    document.getElementById("contentError").innerText = "";
                    document.getElementById("imageError").innerText = "";

                    // Validate category
                    if (category.value.trim() === "") {
                        document.getElementById("categoryError").innerText = "Please select a category.";
                        isValid = false;
                    }

                    // Validate title
                    if (title.value.trim() === "") {
                        document.getElementById("titleError").innerText = "Title is required.";
                        isValid = false;
                    }

                    // Validate content
                    if (content.value.trim() === "") {
                        document.getElementById("contentError").innerText = "Content is required.";
                        isValid = false;
                    }

                    // Validate image
                    if (image.files.length === 0) {
                        document.getElementById("imageError").innerText = "Please upload an image.";
                        isValid = false;
                    } else {
                        const file = image.files[0];
                        const allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;
                        if (!allowedExtensions.exec(file.name)) {
                            document.getElementById("imageError").innerText = "Invalid image format. Allowed formats: .jpg, .jpeg, .png, .gif.";
                            isValid = false;
                        }
                    }

                    // If form is not valid, prevent submission
                    if (!isValid) {
                        event.preventDefault();
                    }
                }
                ;
            </script>

    </body>

</html>