<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container-fluid p-0 mb-5 slider-container">
    <div class="owl-carousel header-carousel position-relative">
        <c:forEach var="slider" items="${sliders}">
            <div class="owl-carousel-item position-relative">
                <img class="slider-img" src="${slider.img}" alt="${slider.title}">
                <div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center" style="background: rgba(24, 29, 56, .7);">
                    <div class="container">
                        <div class="row justify-content-start">
                            <div class="col-sm-10 col-lg-8">
                                <h5 class="text-primary text-uppercase mb-3 animated slideInDown">${slider.title}</h5>
                                <h1 class="display-3 text-white animated slideInDown">${slider.description}</h1>
                                <p class="fs-5 text-white mb-4 pb-2">${slider.description}</p>
                                <a href="#" class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">Read More</a>
                                <a href="#" class="btn btn-light py-md-3 px-md-5 animated slideInRight">Join Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!-- Thêm CSS tr?c ti?p vào ?ây -->
<style>
    .slider-img {
        width: 100%;
        height: 500px;
        object-fit: cover;
    }

    .owl-carousel-item {
        position: relative;
    }

    .slider-container .text-primary {
        font-size: 1.2rem;
    }

    .slider-container .display-3 {
        font-size: 2rem;
    }

    .slider-container .fs-5 {
        font-size: 1rem;
    }

    .btn {
        font-size: 0.9rem;
        padding: 8px 15px;
    }
</style>
