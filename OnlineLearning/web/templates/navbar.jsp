<!-- navbar.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
    <a href="index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
        <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>eLEARNING</h2>
    </a>
    <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <div class="navbar-nav ms-auto p-4 p-lg-0">
            <a href="home" class="nav-item nav-link ">Home</a>
            
            <a href="about.html" class="nav-item nav-link">About</a>
            <a href="listCourses" class="nav-item nav-link">Courses</a>
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                <div class="dropdown-menu fade-down m-0">
                    <a href="team.html" class="dropdown-item">Our Team</a>
                    <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                    <a href="post" class="dropdown-item">Post</a>
                    <a href="404.html" class="dropdown-item">404 Page</a>

                    <!-- Chỉ hiển thị "Subject Management" nếu user là admin -->
                    <c:if test="${acc.getRole_id() == 1}">
                        <a href="admin/subject-management" class="dropdown-item">Subject Management</a>
                        <a href="ListMyCourses" class="dropdown-item">My Courses</a>
                    </c:if>
                </div>
            </div>

            <a href="contact.html" class="nav-item nav-link">Contact</a>
        </div>



        <% if (session.getAttribute("username") != null) { %>
        <a href="profile" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block"><%= session.getAttribute("username") %></a>
        <% } else { %>
        <a href="login.jsp" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">Join Now<i class="fa fa-arrow-right ms-3"></i></a>
            <% } %>
    </div>
</nav>
