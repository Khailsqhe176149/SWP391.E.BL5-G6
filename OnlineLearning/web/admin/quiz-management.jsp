<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Subject management</title>
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
            .table {
                width: 100%;
                table-layout: fixed;
            }
            .feature-filters {
                display: flex;
                justify-content: flex-end; /* Aligns the buttons to the right */
                margin-top: 10px;
            }

            .feature-filters .filters {
                list-style: none;
                margin: 0;
                padding: 0;
                display: flex;
                gap: 10px; /* Space between buttons */
            }

            .feature-filters .filters li {
                display: inline-block;
            }

            .feature-filters .filters .btn {
                padding: 8px 16px;
                border: 2px solid #ddd;
                border-radius: 4px;
                font-size: 14px;
                color: #555;
                cursor: pointer;
                transition: all 0.3s ease;
            }

            .feature-filters .filters .btn:hover {
                background-color: #f8f9fa;
                border-color: #ccc;
            }

            .feature-filters .filters .btn.active {
                background-color: #007bff;
                border-color: #007bff;
                color: white;
            }

            .feature-filters .filters .btn a {
                text-decoration: none;
                color: inherit;
            }

            .feature-filters .filters .btn a:hover {
                text-decoration: underline;
            }
            .required-color{
                color: red
            }

        </style>
    </head>

    <body>
        <!-- Navbar Start  -->
        <jsp:include page="templates/navbar.jsp" />
        <!-- Navbar End  -->

        <!-- Sidebar and Main Content Start -->
        <div class="container-fluid" style="display: flex; min-height: 100vh;">

            <!-- Main Content -->
            <div class="main-content flex-grow-1 p-5">
                <h2>Quiz Management</h2>

                <!-- Thanh tìm kiếm -->
                <div class="mb-4">
                    <form action="quiz-management" method="get">
                        <div class="row">
                            <div class="col-md-10">
                                <div class="form-group">
                                    <div class="input-group" style="width: 80%">
                                        <input type="text" class="form-control" name="search" placeholder="Enter name to search" value="${searchParam}">
                                        <button class="btn btn-primary ms-2" type="submit">Search</button>
                                    </div>
                                </div>
                            </div>
                            <button type="button" class=" btn-outline-primary btn col-md-2" data-bs-toggle="modal" data-bs-target="#add">Add new</button>
                        </div>

                        <input type="hidden" name="status" value="${param.status}">
                        <input type="hidden" name="page" value="1">
                    </form>
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
                <div class="row">
                    <div class="col-12" >
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Name</th>
                                    <th>Min Score</th>
                                    <th>Description</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="s" items="${posts}" varStatus="status">
                                    <tr>
                                        <td>${status.index + 1}</td>
                                        <td>${s.getName()}</td>
                                        <td>${s.miniumscore}</td>
                                        <td>${s.getDescription()}</td>
                                        <td>

                                            <!-- Details Button -->
                                            <button onclick="window.location.href = 'quiz-detail?id=${s.getQuizid()}'" type="button" class="btn-outline-warning">
                                                <li class="fa fa-eye"></li>
                                            </button>
                                            <button  type="button" class="btn-outline-info" data-bs-toggle="modal" data-bs-target="#edit${s.getQuizid()}">
                                                <li class="fa fa-pen"></li>
                                            </button>
                                        </td>

                                    </tr>

                                <div class="modal fade" id="edit${s.getQuizid()}" tabindex="-1" aria-labelledby="edit${s.getQuizid()}" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <div class="modal-header bg-primary text-white">
                                                <h5 class="modal-title" id="edit${s.getQuizid()}">Edit Quiz</h5>
                                                <button type="button" class="btn-close text-white" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="${pageContext.request.contextPath}/admin/quiz-management" method="post" onsubmit="return validateForm(this);">
                                                    <input type="hidden" name="action" value="edit">
                                                    <input type="hidden" name="id" value="${s.getQuizid()}">
                                                    <div class="form-group">
                                                        <label>Quiz Name <span class="required-color">*</span></label>
                                                        <input class="form-control" name="name" type="text" value="${s.getName()}" placeholder="Enter quiz name">
                                                        <span class="text-danger"></span><br>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Min Score <span class="required-color">*</span></label>
                                                        <input class="form-control" name="minScore" type="number" step="0.1" value="${s.miniumscore}" placeholder="Enter Min Score">
                                                        <span class="text-danger"></span><br>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Description <span class="required-color">*</span></label>
                                                        <textarea class="form-control" name="description" placeholder="Enter Description">${s.getDescription()}</textarea>
                                                        <span class="text-danger"></span><br>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Content <span class="required-color">*</span></label>
                                                        <textarea name="content" class="form-control" rows="5">${s.content}</textarea>
                                                        <span class="text-danger"></span><br>
                                                    </div>
                                                    <div class="modal-footer d-flex justify-content-center">
                                                        <button type="submit" class="btn-outline-primary">Save</button>
                                                        <button type="button" class="btn-outline-secondary" data-bs-dismiss="modal" aria-label="Close">Close</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Pagination -->
                <nav class="mt-3" aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="btn btn-primary" href="?search=${param.search}&status=${param.status}&page=${currentPage - 1}">Previous</a>
                            </li>
                        </c:if>
                        <c:forEach var="pageNum" begin="1" end="${totalPages}">
                            <li class="page-item ${pageNum == currentPage ? 'active' : ''}">
                                <a class="btn btn-primary" href="?search=${param.search}&status=${param.status}&page=${pageNum}">${pageNum}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="btn btn-primary" href="?search=${param.search}&status=${param.status}&page=${currentPage + 1}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>

            </div>
        </div>
        <div class="modal fade" id="add" tabindex="-1" aria-labelledby="add" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header bg-primary text-white">
                        <h5 class="modal-title" id="add">Add new Quiz</h5>
                        <button type="button" class="btn-close text-white" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="${pageContext.request.contextPath}/admin/quiz-management" method="post" onsubmit="return validateForm(this);">
                            <input type="hidden" name="action" value="add">
                            <div class="form-group">
                                <label>Quiz Name <span class="required-color">*</span></label>
                                <input class="form-control" name="name" type="text" placeholder="Enter quiz name">
                                <span class="text-danger"></span><br>
                            </div>
                            <div class="form-group">
                                <label>Min Score <span class="required-color">*</span></label>
                                <input class="form-control" name="minScore" type="number" step="0.1" placeholder="Enter Min Score">
                                <span class="text-danger"></span><br>
                            </div>
                            <div class="form-group">
                                <label>Description <span class="required-color">*</span></label>
                                <textarea class="form-control" name="description" placeholder="Enter Description"></textarea>
                                <span class="text-danger"></span><br>
                            </div>
                            <div class="form-group">
                                <label>Content <span class="required-color">*</span></label>
                                <textarea name="content" class="form-control" rows="5"></textarea>
                                <span class="text-danger"></span><br>
                            </div>
                            <div class="modal-footer d-flex justify-content-center">
                                <button type="submit" class="btn-outline-primary">Add</button>
                                <button type="button" class="btn-outline-secondary" data-bs-dismiss="modal" aria-label="Close">Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer Start -->
        <jsp:include page="templates/footer.jsp" />
        <!-- Footer End -->


    </body>
    <script>
        function validateForm(form) {
            let isValid = true;

            // Clear previous errors
            form.querySelectorAll('.text-danger').forEach(e => e.textContent = '');

            // Get form-specific fields
            const quizNameField = form.querySelector('[name="name"]');
            const minScoreField = form.querySelector('[name="minScore"]');
            const descriptionField = form.querySelector('[name="description"]');
            const contentField = form.querySelector('[name="content"]');

            // Extract values and perform validation
            const quizName = quizNameField.value.trim();
            const minScore = minScoreField.value.trim();
            const description = descriptionField.value.trim();
            const content = contentField.value.trim();

            // Validation rules
            const namePattern = /^[a-zA-Z0-9\s\-–',.()]{5,255}$/;

            // Validate Quiz Name
            if (!quizName) {
                quizNameField.nextElementSibling.textContent = 'Quiz name cannot be empty.';
                isValid = false;
            } else if (quizName.length < 5 || quizName.length > 255) {
                quizNameField.nextElementSibling.textContent = 'Quiz name must be between 5 and 255 characters.';
                isValid = false;
            } else if (!namePattern.test(quizName)) {
                quizNameField.nextElementSibling.textContent = 'Quiz name contains invalid characters.';
                isValid = false;
            }

            // Validate Min Score
            if (!minScore) {
                minScoreField.nextElementSibling.textContent = 'Min score cannot be empty.';
                isValid = false;
            } else if (isNaN(minScore)) {
                minScoreField.nextElementSibling.textContent = 'Min score must be a number.';
                isValid = false;
            }

            // Validate Description
            if (!description) {
                descriptionField.nextElementSibling.textContent = 'Description cannot be empty.';
                isValid = false;
            }

            // Validate Content
            if (!content) {
                contentField.nextElementSibling.textContent = 'Content cannot be empty.';
                isValid = false;
            }

            return isValid;
        }
    </script>

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
    </script>
</html>
