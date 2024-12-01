<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Filter Courses by Price</title>
</head>
<body>

    <!-- L?c theo giá -->
    <form method="get" action="listCourseByPrice">
        <div>
            <label>Price Filter</label><br/>
            <input type="radio" name="priceRange" value="free" 
                   <c:if test="${param.priceRange == 'free'}">checked</c:if> /> Free<br/>
            <input type="radio" name="priceRange" value="paid" 
                   <c:if test="${param.priceRange == 'paid'}">checked</c:if> /> Paid<br/>
            <input type="radio" name="priceRange" value="under100" 
                   <c:if test="${param.priceRange == 'under100'}">checked</c:if> /> Under $100<br/>
            <input type="radio" name="priceRange" value="100to500" 
                   <c:if test="${param.priceRange == '100to500'}">checked</c:if> /> $100 - $500<br/>
            <input type="radio" name="priceRange" value="above500" 
                   <c:if test="${param.priceRange == 'above500'}">checked</c:if> /> Above $500<br/>
            <button type="submit">Filter</button>
        </div>
    </form>

    <!-- Hi?n th? danh sách khóa h?c -->
    <div class="courses">
        <c:forEach var="course" items="${courses}">
            <div class="course">
                <h3>${course.name}</h3>
                <p>${course.description}</p>
                <p><strong>Price:</strong> $${course.price}</p>
            </div>
        </c:forEach>
    </div>

    <!-- Phân trang -->
    <div class="pagination">
        <c:forEach var="i" begin="1" end="${totalPages}" step="1">
            <a href="?pageIndex=${i}&priceRange=${param.priceRange}">${i}</a>
        </c:forEach>
    </div>

</body>
</html>
