<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Custom Checkbox</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css" rel="stylesheet" />
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

    </head>
    <body>
        <!-- Navbar Start  -->
        <jsp:include page="templates/navbar.jsp" />
        <!-- Navbar End  -->
        <div class="container mt-5">
            <div class="row">
                <!-- Cột trái: Lựa chọn thanh toán (Không nằm trong card) -->
                <div class="col-md-8">
                    <h4>Payment Options</h4>
                    <!-- Các lựa chọn thanh toán -->
                    <div class="payment-options">
                        <div class="row">
                            <!-- Cột 1: Pay by Wallet -->
                            <div class="col-md-6">
                                <div class="form-check custom-checkbox">
                                    <input class="form-check-input" type="checkbox" id="payByWallet" checked disabled>
                                    <img src="img/Wallet1.jpg" alt="Wallet" class="payment-icon mr-2">
                                    <label class="form-check-label" for="payByWallet">
                                        Pay by Wallet
                                    </label>
                                </div>
                            </div>
                            <!-- Cột 2: Pay by Credit Card -->
                            <div class="col-md-6">
                                <div class="form-check custom-checkbox">
                                    <input class="form-check-input" type="checkbox" id="payByCredit" disabled>
                                    <img src="img/CreditCard.jpg" alt="Credit Card" class="payment-icon mr-2">
                                    <label class="form-check-label" for="payByCredit">
                                        Pay by Credit Card
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row mt-5">
                            <!-- Cột 3: Pay by PayPal -->
                            <div class="col-md-6">
                                <div class="form-check custom-checkbox">
                                    <input class="form-check-input" type="checkbox" id="payByPaypal" disabled>
                                    <img src="img/vietQR.jpg" alt="PayPal" class="payment-icon mr-2">
                                    <label class="form-check-label" for="payByPaypal">
                                        Pay by VietQR
                                    </label>
                                </div>
                            </div>
                            <!-- Cột 4: Pay by Bank Transfer -->
                            <div class="col-md-6">
                                <div class="form-check custom-checkbox">
                                    <input class="form-check-input" type="checkbox" id="payByBank" disabled>
                                    <img src="img/VnPay.jpg" alt="Bank Transfer" class="payment-icon mr-2">
                                    <label class="form-check-label" for="payByBank">
                                        Pay by VNPay
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Cột phải: Thông tin khóa học và ví người dùng (Trong thẻ card) -->
                <div class="col-md-4">
                    <div class="card course-info-card">
                        <div class="card-body">
                            <!-- Hiển thị hình ảnh khóa học -->
                            <img src="<c:out value="img/${course.img}" />" class="img-fluid mb-3" alt="<c:out value='${course.name}' />">

                            <!-- Hiển thị tên khóa học -->
                            <h2 class="card-title"><c:out value="${course.name}" /></h2>

                            <!-- Hiển thị thông tin người dùng -->
                            <p><strong>Customer Name:</strong> ${username}</p>


                            <!-- Hiển thị giá khóa học -->
                            <p class="course-price"><strong>Price:</strong> $<c:out value="${course.price}" /></p>

                            <!-- Thông tin ví của người dùng -->
                            <div class="wallet-info">
                                <p><strong>Your Wallet:</strong> $<c:out value="${balance}" /></p>
                                <p><strong>Your Wallet After Pay:</strong> $<c:out value="${remainingBalance}" /></p>
                            </div>

                            <!-- Nút Đăng Ký khóa học -->
                            <form action="CheckOut" method="POST">
                                <!-- Thêm hidden input để gửi courseId -->
                                <input type="hidden" name="courseId" value="${course.courseId}" />
                                <button type="submit" class="btn btn-primary mt-3">Enroll Now</button>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- Modal for success or failure message -->
        <div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="messageModalLabel">Notification</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- Display message dynamically based on request attribute -->
                        <p>${message}</p>
                    </div>
                    <div class="modal-footer">
                        <!-- Button to close the modal and redirect to home -->
                        <a href="${redirectUrl}" class="btn btn-primary">Go to Home</a>
                    </div>
                </div>
            </div>
        </div>
        < <!-- Footer Start -->
        <jsp:include page="templates/footer.jsp" />
        <!-- Footer End -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/wow/wow.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>

        <script type="text/javascript">
            // Display the modal when the page loads if there's a message
            $(document).ready(function () {
                if ('${message}' != '') {
                    $('#messageModal').modal('show');
                }
            });
        </script>
    </body>
</html>
