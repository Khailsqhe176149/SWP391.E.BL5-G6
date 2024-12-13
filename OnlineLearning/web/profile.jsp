<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Users" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
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
        <style>
            /* Chỉnh sửa ảnh thành hình vuông và to ra */
            .profile-img {
                width: 200px; /* To ra */
                height: 200px; /* Cắt thành hình vuông */
                object-fit: cover; /* Đảm bảo ảnh không bị méo */
                border-radius: 10px; /* Tạo góc bo tròn nhẹ */
            }
        </style>
    </head>

    <body>

        <!-- Navbar Start -->
        <jsp:include page="templates/navbar.jsp" />
        <!-- Navbar End -->

        <div class="container profile-container">
            <div class="row">
                <div class="col-lg-12 mb-4 mb-sm-5">
                    <div class="card card-style1 border-0">
                        <div class="card-body p-4">
                            <div class="row align-items-center">
                                <% 
                                    Users user = (Users) request.getAttribute("user");
                                %>
                                <div class="col-lg-4 mb-4 mb-lg-0 text-center">
                                    <!-- Hiển thị ảnh đại diện người dùng -->
                                    <img src="<%= user.getImg() %>" alt="User Image" class="img-fluid profile-img">
                                </div>
                                <div class="col-lg-8">
                                    <div class="bg-secondary d-lg-inline-block py-3 px-4 mb-3 rounded">
                                        <h3 class="h2 text-white mb-0"><%= user.getName() %></h3>
                                    </div>


                                    <!-- Form cập nhật thông tin profile -->

                                    <form action="profile" method="POST" enctype="multipart/form-data">
                                        <div class="mb-3">
                                            <label for="name" class="form-label">Name:</label>
                                            <input type="text" id="name" name="name" class="form-control" value="<%= user.getName() %>" required />
                                        </div>
                                        <div class="mb-3">
                                            <label for="gender" class="form-label">Gender:</label>
                                            <select id="gender" name="gender" class="form-control">
                                                <option value="1" <%= user.getGender() == 1 ? "selected" : "" %>>Male</option>
                                                <option value="0" <%= user.getGender() == 0 ? "selected" : "" %>>Female</option>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="dob" class="form-label">Date of birth:</label>
                                            <input type="date" id="dob" name="dob" class="form-control" value="<%= user.getDob() %>" required />
                                        </div>
                                        <div class="mb-3">
                                            <label for="phone" class="form-label">Phone:</label>
                                            <input type="text" id="phone" name="phone" class="form-control" value="<%= user.getPhone() %>" required />
                                        </div>
                                        <div class="mb-3">
                                            <label for="address" class="form-label">Address:</label>
                                            <input type="text" id="address" name="address" class="form-control" value="<%= user.getAddress() %>" required />
                                        </div>

                                        <!-- Form thay đổi ảnh đại diện -->
                                        <h4>Choose avatar</h4>
                                        <div class="mb-3">
                                            <label for="image" class="form-label">Choose imagine:</label>
                                            <input type="file" id="image" name="image" accept="image/*" class="form-control" />
                                        </div>
                                        <button type="submit" class="btn btn-primary">Update Information</button>
                                        <a href="http://localhost:9999/OnlineLearning/changepassword">
                                            <button type="button" class="btn btn-primary">Change Password</button>
                                        </a>

                                    </form>





                                </div>
                            </div>
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
