<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <div class="row g-4 justify-content-center">
          
            <c:forEach var="course" items="${courses}">
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="course-item bg-light">
                        <div class="position-relative overflow-hidden">
                           
                            <img class="img-fluid" src="img/${course.img}" alt="${course.courseName}">
                            <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
                                <a href="CourseDetailServlet?courseId=<c:out value='${course.courseId}' />"  class="flex-shrink-0 btn btn-sm btn-primary px-3 border-end"
                                   style="border-radius: 30px 0 0 30px;">Read More</a>
                                <a href="#" class="flex-shrink-0 btn btn-sm btn-primary px-3"
                                   style="border-radius: 0 30px 30px 0;">Join Now</a>
                            </div>
                        </div>
                        <div class="text-center p-4 pb-0">
                           
                            <h5 class="mb-4">${course.courseName}</h5>
                        </div>
                        <div class="d-flex border-top">
                           
                            <small class="flex-fill text-center border-end py-2">
                                <i class="fa fa-user-tie text-primary me-2"></i>${course.numberOfRegistrations} Subscriptions
                            </small>
                            
                            <small class="flex-fill text-center py-2">
                                <i class="fa fa-user text-primary me-2"></i>
                                <fmt:formatNumber value="${course.price}" type="currency" currencySymbol="$" />
                            </small>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <br>
       
       <div class="text-center">
    <c:if test="${currentPage > 1}">
        <a href="#" class="btn btn-primary pagination-button" data-page="${currentPage - 1}">Previous</a>
    </c:if>
    <c:if test="${currentPage > 0}">
        <a href="#" class="btn btn-primary pagination-button" data-page="${currentPage + 1}">Next</a>
    </c:if>
</div>

  