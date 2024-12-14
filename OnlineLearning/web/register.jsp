<%-- 
    Document   : register
    Created on : Nov 30, 2024, 5:24:08 PM
    Author     : Khải
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/stylelogin.css">
    <title>Register</title>
  </head>
  <body class="img js-fullheight" style="background-image: url(img/bg.jpg);">
    <section class="ftco-section">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-md-6 text-center mb-5">
            <h2 class="heading-section">Register</h2>
          </div>
        </div>
        <div class="row justify-content-center">
          <div class="col-md-6 col-lg-4">
            <div class="login-wrap p-0">
              <h3 style="font-size: 24px" class="mb-4 text-center">Have an account?
                <a href="Login" style="color: #fbceb5; margin-left: 10px; font-size: 15px; text-decoration: underline;">Login here!</a>
              </h3>
              <form action="Register" method="post" class="signin-form" onsubmit="return validatePassword();">
                <div class="form-group">
                  <input type="text" class="form-control" name="name" placeholder="Full Name" value="${param.name}" required>
                </div>
                <div class="form-group">
                  <label>Gender</label>
                  <select class="form-control" name="gender" required>
                    <option value="0" ${param.gender == "0" ? "selected" : ""} style=" color: black;" >Male</option>
                    <option value="1" ${param.gender == "1" ? "selected" : ""} style=" color: black;" >Female</option>
                  </select>
                </div>
                <div class="form-group">
                  <input type="date" class="form-control" name="dob" placeholder="Date of Birth" value="${param.dob}" required>
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" id="phone" name="phone" placeholder="Phone" value="${param.phone}" required>
                  <c:if test="${not empty phoneError}">
                    <div class="error-message" style="color: red;">${phoneError}</div>
                  </c:if>
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" name="address" placeholder="Address" value="${param.address}" required>
                </div>
                <div class="form-group">
                  <input type="email" class="form-control" name="email" placeholder="Email" value="${param.email}" required>
                  <c:if test="${not empty emailError}">
                    <div class="error-message" style="color: red;">${emailError}</div>
                  </c:if>
                </div>
                <div class="form-group">
                  <input id="password-field" type="password" class="form-control" name="password" placeholder="Password" required>
                  <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                </div>
                <div class="form-group">
                  <input id="confirm-password" type="password" class="form-control" name="confirmPassword" placeholder="Confirm Password" required>
                  <span toggle="#confirm-password" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                </div>
                <div class="form-group">
                  <button type="submit" class="form-control btn btn-primary submit px-3">Sign Up</button>
                </div>
              </form>
<!--              <p class="w-100 text-center">&mdash; Or Sign Up With &mdash;</p>-->
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
      // Toggle password visibility
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
      
      
      // Chỉ cho phép nhập số vào trường phone
    function allowOnlyNumbers(event) {
        var keyCode = event.keyCode || event.which;
        var keyValue = String.fromCharCode(keyCode);

        // Kiểm tra xem ký tự nhập vào có phải là số không (0-9)
        if (!/^[0-9]$/.test(keyValue)) {
            event.preventDefault();
        }
    }

    // Áp dụng cho input phone
    document.getElementById('phone').addEventListener('keypress', allowOnlyNumbers);
      
      
      

      // JavaScript function to validate password and confirm password
      function validatePassword() {
        var password = document.getElementById("password-field").value;
        var confirmPassword = document.getElementById("confirm-password").value;
        
        if (password !== confirmPassword) {
          alert("Passwords do not match. Please try again.");
          return false;  // Prevent form submission
        }
        return true;  // Allow form submission
      }
    </script>
  </body>
</html>
