<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Post Details</title>

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

    </head>
    <body>
        <!-- Navbar -->
        <jsp:include page="templates/navbar.jsp" />

        <!-- Post Detail -->
        <div class="container mt-5">
            <div class="row">
                <c:if test="${uid != null && uid == post.author.userID}">
                    <div class=" text-end">
                        <div class="text-end">
                            <button class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#editPostModal">Edit</button>
                            <button class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deletePostModal">Delete</button>
                        </div>

                    </div>
                </c:if> 
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
                <div class="col-lg-8 mx-auto">

                    <div class="card">
                        <!-- Post Image -->

                        <c:choose>
                            <c:when test="${not empty post.img}">
                                <img src="img/${post.img}" class="card-img-top" alt="${post.title}">
                            </c:when>
                            <c:otherwise>
                                <img src="img/placeholder.png" class="card-img-top" alt="${post.title}">
                            </c:otherwise>
                        </c:choose>
                        <!-- Post Content -->
                        <div class="card-body">
                            <h1 class="card-title text-center">${post.title}</h1>
                            <p class="text-muted text-center">
                                <i class="fa fa-user"></i> ${post.author.name} |
                                <i class="fa fa-calendar"></i> 
                                <fmt:formatDate value="${post.createtime}" pattern="dd/MM/yyyy" /> |
                                <i class="fa fa-tag"></i> ${post.category.name}
                            </p>
                            <hr>
                            <p class="card-text">${post.content}</p>
                        </div>
                    </div>
                    <!-- Back Button -->
                    <div class="mt-4 text-center">
                        <a href="post" class="btn btn-primary">Back to Posts</a>
                    </div>
                    <div class="mt-4 text-center">
                        <c:if test="${acc.role_id == 1}">
                            <a href="${pageContext.request.contextPath}/admin/post-management" class="btn btn-primary">Back to Posts Management</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <!-- Edit Modal -->
        <div class="modal fade" id="editPostModal" tabindex="-1" aria-labelledby="editPostModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="editPostForm" enctype="multipart/form-data" method="POST" action="post-detail">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="postId" value="${post.postid}">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editPostModalLabel">Edit Post</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <!-- Category -->
                            <div class="mb-3">
                                <label for="editCategory" class="form-label">Category</label>
                                <select name="categoryId" id="editCategory" class="form-select">
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.postCatPostidegoryId}" 
                                                <c:if test="${category.postCatPostidegoryId == post.category.postCatPostidegoryId}">selected</c:if>>
                                            ${category.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <!-- Title -->
                            <div class="mb-3">
                                <label for="editTitle" class="form-label">Title</label>
                                <input type="text" name="title" id="editTitle" class="form-control" value="${post.title}">
                            </div>
                            <!-- Image -->
                            <div class="mb-3">
                                <label for="editImage" class="form-label">Image</label>
                                <img src="img/${post.img}" class="img-thumbnail mb-3" alt="Current Image" width="200">
                                <input type="file" name="image" id="editImage" class="form-control">
                            </div>
                            <!-- Content -->
                            <div class="mb-3">
                                <label for="editContent" class="form-label">Content</label>
                                <textarea name="content" id="editContent" class="form-control" rows="5">${post.content}</textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Edit Modal -->
        <div class="modal fade" id="editPostModal" tabindex="-1" aria-labelledby="editPostModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="editPostForm" enctype="multipart/form-data" method="POST" action="post-detail">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="postId" value="${post.postid}">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editPostModalLabel">Edit Post</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <!-- Category -->
                            <div class="mb-3">
                                <label for="editCategory" class="form-label">Category</label>
                                <select name="categoryId" id="editCategory" class="form-select">
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.postCatPostidegoryId}" 
                                                <c:if test="${category.postCatPostidegoryId == post.category.postCatPostidegoryId}">selected</c:if>>
                                            ${category.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <!-- Title -->
                            <div class="mb-3">
                                <label for="editTitle" class="form-label">Title</label>
                                <input type="text" name="title" id="editTitle" class="form-control" value="${post.title}">
                            </div>
                            <!-- Image -->
                            <div class="mb-3">
                                <label for="editImage" class="form-label">Image</label>
                                <img src="img/${post.img}" class="img-thumbnail mb-3" alt="Current Image" width="200">
                                <input type="file" name="image" id="editImage" class="form-control">
                            </div>
                            <!-- Content -->
                            <div class="mb-3">
                                <label for="editContent" class="form-label">Content</label>
                                <textarea name="content" id="editContent" class="form-control" rows="5">${post.content}</textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Edit Modal -->
        <div class="modal fade" id="editPostModal" tabindex="-1" aria-labelledby="editPostModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="editPostForm" enctype="multipart/form-data" method="POST" action="post-detail">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="postId" value="${post.postid}">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editPostModalLabel">Edit Post</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <!-- Category -->
                            <div class="mb-3">
                                <label for="editCategory" class="form-label">Category</label>
                                <select name="categoryId" id="editCategory" class="form-select">
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.postCatPostidegoryId}" 
                                                <c:if test="${category.postCatPostidegoryId == post.category.postCatPostidegoryId}">selected</c:if>>
                                            ${category.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <!-- Title -->
                            <div class="mb-3">
                                <label for="editTitle" class="form-label">Title</label>
                                <input type="text" name="title" id="editTitle" class="form-control" value="${post.title}">
                            </div>
                            <!-- Image -->
                            <div class="mb-3">
                                <label for="editImage" class="form-label">Image</label>
                                <img src="img/${post.img}" class="img-thumbnail mb-3" alt="Current Image" width="200">
                                <input type="file" name="image" id="editImage" class="form-control">
                            </div>
                            <!-- Content -->
                            <div class="mb-3">
                                <label for="editContent" class="form-label">Content</label>
                                <textarea name="content" id="editContent" class="form-control" rows="5">${post.content}</textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Edit Modal -->
        <div class="modal fade" id="editPostModal" tabindex="-1" aria-labelledby="editPostModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="editPostForm" enctype="multipart/form-data" method="POST" action="post-detail">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="postId" value="${post.postid}">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editPostModalLabel">Edit Post</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <!-- Category -->
                            <div class="mb-3">
                                <label for="editCategory" class="form-label">Category</label>
                                <select name="categoryId" id="editCategory" class="form-select">
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.postCatPostidegoryId}" 
                                                <c:if test="${category.postCatPostidegoryId == post.category.postCatPostidegoryId}">selected</c:if>>
                                            ${category.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <!-- Title -->
                            <div class="mb-3">
                                <label for="editTitle" class="form-label">Title</label>
                                <input type="text" name="title" id="editTitle" class="form-control" value="${post.title}">
                            </div>
                            <!-- Image -->
                            <div class="mb-3">
                                <label for="editImage" class="form-label">Image</label>
                                <img src="img/${post.img}" class="img-thumbnail mb-3" alt="Current Image" width="200">
                                <input type="file" name="image" id="editImage" class="form-control">
                            </div>
                            <!-- Content -->
                            <div class="mb-3">
                                <label for="editContent" class="form-label">Content</label>
                                <textarea name="content" id="editContent" class="form-control" rows="5">${post.content}</textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Delete Modal -->
        <div class="modal fade" id="deletePostModal" tabindex="-1" aria-labelledby="deletePostModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="POST" action="post-detail">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="postId" value="${post.postid}">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deletePostModalLabel">Confirm Delete</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Are you sure you want to delete this post?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
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
        </script>
        <!-- Footer -->
        <jsp:include page="templates/footer.jsp" />

        <!-- Bootstrap JS -->
        <script src="js/bootstrap.bundle.min.js"></script>
    </body>
</html>
