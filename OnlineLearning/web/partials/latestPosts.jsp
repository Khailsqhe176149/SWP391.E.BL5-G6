
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container-xxl py-5 wow fadeInUp" data-wow-delay="0.1s">
    <div class="container">
        <div class="text-center">
            <h6 class="section-title bg-white text-center text-primary px-3">Latest Posts</h6>
            <h1 class="mb-5">New Posts</h1>
        </div>
        <div class="owl-carousel testimonial-carousel position-relative">
            <!-- Loop through the posts and display them in carousel items -->
            <c:forEach var="post" items="${latestPosts}">
                <div class="testimonial-item text-center">
                    <!-- Post Image -->
                    <img class="border rounded-circle p-2 mx-auto mb-3" src="${post.img}" style="width: 80px; height: 80px;">
                    <!-- Post Title -->
                    <h5 class="mb-0">${post.title}</h5>
                    <!-- Post Author -->
                    <p>By ${post.authorName}</p> <!-- Author's name from the LatestPost model -->
                    <div class="testimonial-text bg-light text-center p-4">
                        <!-- Post Content -->
                        <p class="mb-0">${post.content}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>