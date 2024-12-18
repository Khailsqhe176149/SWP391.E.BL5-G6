<%-- 
    Document   : login
    Created on : Nov 28, 2024, 2:13:39 PM
    Author     : Khải
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/stylelogin.css">
        <title>Login</title>
    </head>
    <body class="img js-fullheight" style="background-image: url(img/bg.jpg);">
        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6 text-center mb-5">
                        <h2 class="heading-section">Login</h2>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-4">
                        <div class="login-wrap p-0">
                            <c:if test="${not empty sessionScope.notification}">
                                <div class="alert alert-success alert-dismissible fade show" role="alert" style="text-align: center;">
                                    ${sessionScope.notification}
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
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
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <%
                                    session.removeAttribute("notificationErr");
                                %>
                            </c:if>
                            <h3 style="font-size: 24px" class="mb-4 text-center">Don't have an account?
                                <a href="Register" style="color: #fbceb5; margin-left: 10px;font-size: 15px;text-decoration: underline;">Create an account here!</a>
                            </h3>
                            <form action="Login" method="post" class="signin-form">
                                <div class="form-group">
                                    <input type="text" class="form-control" name="email" placeholder="Email" required>
                                </div>
                                <div class="form-group">
                                    <input id="password-field" type="password" class="form-control" name="password" placeholder="Password" required>
                                    <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="form-control btn btn-primary submit px-3">Sign In</button>
                                </div>
                                <div class="form-group d-md-flex">
                                    <div class="w-50">
                                        <label class="checkbox-wrap checkbox-primary">Remember Me
                                            <input type="checkbox" checked>
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                    <div class="w-50 text-md-right">
                                        <a href="forgot-password" style="color: #fff">Forgot Password</a>
                                    </div>
                                </div>
                            </form>
                            <!--              <p class="w-100 text-center">&mdash; Or Sign In With &mdash;</p>-->
                            <!--              <div class="social d-flex text-center">
                                            <a href="#" class="px-2 py-2 mr-md-1 rounded"><span class="ion-logo-facebook mr-2"></span> Facebook</a>
                                            <a href="#" class="px-2 py-2 ml-md-1 rounded"><span class="ion-logo-twitter mr-2"></span> Twitter</a>
                                          </div>-->
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main1.js"></script>
        <script>
            $(".toggle-password").click(function() {
        var input = $($(this).attr("toggle"));
        if (input.attr("type") === "password") {
          input.attr("type", "text");
          $(this).toggleClass("fa-eye fa-eye-slash");
        } else {
          input.attr("type", "password");
          $(this).toggleClass("fa-eye-slash fa-eye");
        }
      });
        </script>
    </body>
</html>
