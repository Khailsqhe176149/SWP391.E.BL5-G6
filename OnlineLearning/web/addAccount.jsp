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
            <h2 class="heading-section">Register For Admin</h2>
          </div>
        </div>
        <div class="row justify-content-center">
          <div class="col-md-6 col-lg-4">
            <div class="login-wrap p-0">
              <h3 style="font-size: 24px" class="mb-4 text-center">Account List?
                <a href="accountList" style="color: #fbceb5; margin-left: 10px; font-size: 15px; text-decoration: underline;">Click here!</a>
              </h3>
              <form action="addAccount" method="post" class="signin-form" onsubmit="return validatePassword();">
                <div class="form-group">
                  <input type="text" class="form-control" name="name" placeholder="Full Name" value="${param.name}" required>
                </div>
                <div class="form-group">
                  <label>Gender</label>
                  <select class="form-control" name="gender" required>
                    <option value="0" ${param.gender == "0" ? "selected" : ""}>Male</option>
                    <option value="1" ${param.gender == "1" ? "selected" : ""}>Female</option>
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
                </div>
                <div class="form-group">
                  <label for="role">Role</label>
                  <select class="form-control" name="role" required>
                    <option value="1" ${param.role == "1" ? "selected" : ""}>Customer</option>
                    <option value="2" ${param.role == "2" ? "selected" : ""}>Admin</option>
                    <option value="3" ${param.role == "3" ? "selected" : ""}>Staff</option>
                    <option value="4" ${param.role == "4" ? "selected" : ""}>Content Creator</option>
                  </select>
                </div>
                <div class="form-group">
                  <label for="status">Status</label>
                  <select class="form-control" name="status" required>
                    <option value="1" ${param.status == "1" ? "selected" : ""}>Active</option>
                    <option value="0" ${param.status == "0" ? "selected" : ""}>Inactive</option>
                  </select>
                </div>
                <div class="form-group">
                  <button type="submit" class="form-control btn btn-primary submit px-3">Register</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script>
      $(".toggle-password").click(function() {
        $(this).toggleClass("fa-eye fa-eye-slash");
        var input = $($(this).attr("toggle"));
        if (input.attr("type") == "password") {
          input.attr("type", "text");
        } else {
          input.attr("type", "password");
        }
      });

      function validatePassword() {
        var password = document.getElementById("password-field").value;
        var confirmPassword = document.getElementById("confirm-password").value;
        if (password !== confirmPassword) {
          alert("Passwords do not match!");
          return false;
        }
        return true;
      }
    </script>
  </body>
</html>
