<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Post management</title>
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
                <h2>Post Management</h2>

                <!-- Thanh tìm kiếm -->
                <div class="mb-4">
                    <form action="post-management" method="get">
                        <div class="row">
                            <div class="col-md-7">
                                <div class="form-group">
                                    <div class="input-group" style="width: 80%">
                                        <input type="text" class="form-control" name="search" placeholder="Enter name to search" value="${searchParam}">

                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
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
                            <div class="col-md-2">
                                <button class="btn btn-primary ms-2" type="submit">Filter</button>
                                <button class="btn btn-dark ms-2" onclick="window.location.href = 'post-management'"type="button">Clear</button>
                            </div>


                        </div>

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
                                    <th>Image</th>
                                    <th>Category</th>
                                    <th>Author</th>
                                    <th>Create at</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="s" items="${posts}" varStatus="status">
                                    <tr>
                                        <td>${status.index + 1}</td>
                                        <td>${s.getTitle()}</td>
                                        <td><img style="max-width: 100px" src="${pageContext.request.contextPath}/img/${s.img}"></td>
                                        <td>${s.category.getName()}</td>
                                        <td>${s.author.getName()}</td>
                                        <td> <fmt:formatDate value="${s.createtime}" pattern="dd/MM/yyyy" /></td>
                                        <td>
                                            <button onclick="window.location.href = '${pageContext.request.contextPath}/post-detail?postId=${s.getPostid()}'" type="button" class="btn-outline-warning">
                                                Details
                                            </button>
                                            <button 
                                                class="btn-outline-danger" 
                                                data-bs-toggle="modal" 
                                                data-bs-target="#changeStatusModal_${s.getPostid()}">
                                                Delete
                                            </button>
                                        </td>

                                    </tr>
                                    
                                    <div class="modal fade" id="changeStatusModal_${s.getPostid()}" tabindex="-1" aria-labelledby="changeStatusModalLabel_${s.getPostid()}" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <!-- Modal Header -->
                                            <div class="modal-header bg-primary text-white">
                                                <h5 style="text-align: center" class="modal-title d-flex align-items-center" id="changeStatusModalLabel_${s.getPostid()}">
                                                    Delete Post
                                                </h5>
                                                <button type="button" class="btn-close text-white" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <!-- Modal Body -->
                                            <div class="modal-body text-center">
                                                <i class="fas fa-info-circle text-warning fa-3x mb-3"></i>
                                                <p class="mb-2">Are you sure you want to delete this post?</p>
                                            </div>
                                            <!-- Modal Footer -->
                                            <div class="modal-footer d-flex justify-content-center">
                                                <button type="button" class="btn-outline-secondary" data-bs-dismiss="modal">
                                                    <i class="fas fa-times-circle"></i> Cancel
                                                </button>
                                                <form action="${pageContext.request.contextPath}/admin/post-management" method="post" class="d-inline">
                                                    <input type="hidden" name="id" value="${s.getPostid()}">
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


        <!-- Footer Start -->
        <jsp:include page="templates/footer.jsp" />
        <!-- Footer End -->
    </body>
</html>
