<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Users" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thay đổi mật khẩu</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
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
</head>

<body>
    <!-- Navbar Start -->
    <jsp:include page="templates/navbar.jsp" />
    <!-- Navbar End -->

    <div class="container profile-container">
        <div class="row">
            <div class="col-lg-6 mb-4 mb-sm-5">
                <div class="card card-style1 border-0">
                    <div class="card-body p-4">
                        <h3 class="h2">Thay đổi mật khẩu</h3>

                        <!-- Form thay đổi mật khẩu -->
                        <form action="changepassword" method="POST">
                            <div class="mb-3">
                                <label for="oldpassword" class="form-label">Mật khẩu cũ</label>
                                <input type="password" id="oldpassword" name="oldpassword" class="form-control" value="${param.oldpassword}" required />
                                <c:if test="${not empty errorOldPassword}">
                                    <small class="text-danger">${errorOldPassword}</small>
                                </c:if>
                            </div>
                            <div class="mb-3">
                                <label for="newpassword" class="form-label">Mật khẩu mới</label>
                                <input type="password" id="newpassword" name="newpassword" class="form-control" value="${param.newpassword}" required />
                            </div>
                            <div class="mb-3">
                                <label for="confirmpassword" class="form-label">Xác nhận mật khẩu mới</label>
                                <input type="password" id="confirmpassword" name="confirmpassword" class="form-control" value="${param.confirmpassword}" required />
                                <c:if test="${not empty errorConfirmPassword}">
                                    <small class="text-danger">${errorConfirmPassword}</small>
                                </c:if>
                            </div>
                            <button type="submit" class="btn btn-primary">Cập nhật mật khẩu</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer Start -->
    <jsp:include page="templates/footer.jsp" />
    <!-- Footer End -->

    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
