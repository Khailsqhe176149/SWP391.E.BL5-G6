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
                <h2>Subject Management</h2>

                <!-- Thanh tìm kiếm -->
                <div class="mb-4">
                    <form action="subject-management" method="get">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <div class="input-group" style="width: 80%">
                                        <input type="text" class="form-control" name="search" placeholder="Enter name to search" value="${searchParam}">
                                        <button class="btn btn-primary ms-2" type="submit">Search</button>
                                    </div>
                                </div>
                            </div>
                            <div class="feature-filters clearfix center m-b40 col-md-4" style="text-align: right; margin-top: 9px;">
                                <ul class="filters">
                                    <li class="btn ${statusParam == null || statusParam == '' ? 'active' : ''}">
                                        <a href="subject-management?search=${searchParam}&status=&page=1"><span>All</span></a>
                                    </li>
                                    <li class="btn ${statusParam == 1 ? 'active' : ''}">
                                        <a href="subject-management?search=${searchParam}&status=1&page=1"><span>Active</span></a>
                                    </li>
                                    <li class="btn ${statusParam == '0' ? 'active' : ''}">
                                        <a href="subject-management?search=${searchParam}&status=0&page=1"><span>In-active</span></a>
                                    </li>
                                </ul>
                            </div>

                        </div>

                        <input type="hidden" name="status" value="${param.status}">
                        <input type="hidden" name="page" value="1">
                    </form>
                    <button type="button" class=" btn-outline-primary btn" data-bs-toggle="modal" data-bs-target="#add">Add new</button>
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
                                    <th>Total Courses</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="s" items="${subjects}" varStatus="status">
                                    <tr>
                                        <td>${status.index + 1}</td>
                                        <td>${s.getName()}</td>
                                        <td>${s.courseCount}</td>

                                        <td>
                                            <c:choose>
                                                <c:when test="${s.status  == 1}"> <span style="color: green">Active</span></c:when>
                                                <c:otherwise><span style="color: red">In-active</span></c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <!-- Change Status Button -->
                                            <button 
                                                class="btn-outline-${s.status == 1 ? 'danger' : 'success'}" 
                                                data-bs-toggle="modal" 
                                                data-bs-target="#changeStatusModal_${s.subjectid}">
                                                ${s.status == 1 ? 'Deactivate' : 'Activate'}
                                            </button>
                                            <!-- Details Button -->
                                            <button onclick="window.location.href = 'subject-detail?id=${s.subjectid}'" type="button" class="btn-outline-warning">
                                                Details
                                            </button>
                                        </td>

                                    </tr>

                                <div class="modal fade" id="changeStatusModal_${s.subjectid}" tabindex="-1" aria-labelledby="changeStatusModalLabel_${s.subjectid}" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <!-- Modal Header -->
                                            <div class="modal-header bg-primary text-white">
                                                <h5 style="text-align: center" class="modal-title d-flex align-items-center" id="changeStatusModalLabel_${s.subjectid}">
                                                    Change Subject Status
                                                </h5>
                                                <button type="button" class="btn-close text-white" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <!-- Modal Body -->
                                            <div class="modal-body text-center">
                                                <i class="fas fa-info-circle text-warning fa-3x mb-3"></i>
                                                <p class="mb-2">Are you sure you want to change the status of this subject?</p>
                                                <p>
                                                    <strong>Subject Name:</strong> <span class="text-primary">${s.getName()}</span><br>
                                                    <strong>Current Status:</strong> 
                                                    <span class="${s.status == 1 ? 'text-success' : 'text-danger'}">
                                                        <c:choose>
                                                            <c:when test="${s.status  == 1}"> <span style="color: green">Active</span></c:when>
                                                            <c:otherwise><span style="color: red">In-active</span></c:otherwise>
                                                        </c:choose>
                                                    </span>
                                                </p>
                                            </div>
                                            <!-- Modal Footer -->
                                            <div class="modal-footer d-flex justify-content-center">
                                                <button type="button" class="btn-outline-secondary" data-bs-dismiss="modal">
                                                    <i class="fas fa-times-circle"></i> Cancel
                                                </button>
                                                <form action="${pageContext.request.contextPath}/admin/subject-management" method="post" class="d-inline">
                                                    <input type="hidden" name="id" value="${s.subjectid}">
                                                    <input type="hidden" name="newStatus" value="${s.status  == 1 ? 0 : 1}">
                                                    <input type="hidden" name="action" value="change-status">
                                                    <button type="submit" class="btn-outline-primary">
                                                        <i class="fas fa-check-circle"></i> Confirm
                                                    </button>
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
        <div class="modal fade"  id="add" tabindex="-1" aria-labelledby="add" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header bg-primary text-white">
                        <h5 class="modal-title d-flex align-items-center"  id="add">Add new Subject</h5>
                        <button type="button" class="btn-close text-white" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="${pageContext.request.contextPath}/admin/subject-management" method="post" onsubmit="return validateForm();">
                            <div class="form-group">


                                <label for="subject_name">Subject Name <span class="required-color">*</span></label>
                                <input class="form-control" id="subject_name" name="name" type="text" placeholder="Enter subject name">
                                <span id="name_error" class="text-danger"></span> <br>
                            </div>
                            <div class="form-group">
                                <label for="subject_name">Status <span class="required-color">*</span></label>
                                <select class="form-control" id="status" name="status">
                                    <option  value="1" selected=""> Active </option>
                                    <option  value="0" > In-active </option>
                                </select>

                                <input type="hidden" name="action" value="add">
                            </div>
                            <div class="modal-footer d-flex justify-content-center">
                                <button  type="button" class="btn-outline-secondary" data-bs-dismiss="modal" aria-label="Close">Close</button>
                                <button type="submit" class="btn-outline-primary">Add</button>
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
        function validateForm() {
            let isValid = true;

            const name = document.getElementById('subject_name').value.trim();

            console.log('Name:', name);

            const namePattern = /^[a-zA-Z0-9\s\-–',.()]{0,255}$/;

            document.getElementById('name_error').textContent = '';


            if (!name) {
                document.getElementById('name_error').textContent = 'Subject name cannot be empty.';
                isValid = false;
            } else if (name.length > 255 || name.length < 5) {
                document.getElementById('name_error').textContent = 'Subject name must be 5 - 255 characters.';
                isValid = false;
            } else if (!namePattern.test(name)) {
                document.getElementById('name_error').textContent = 'Subject name can only contain letters, numbers, spaces, and hyphens.';
                isValid = false;
            }

            return isValid;
        }


    </script>
</html>
