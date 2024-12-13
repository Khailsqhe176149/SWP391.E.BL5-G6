<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>${title}</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

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

        <!-- Sidebar and Main Content Start -->
        <div class="container-fluid" style="display: flex; min-height: 100vh;">

            <!-- Main Content -->
            <div class="main-content flex-grow-1 p-5">

                <div class="row">
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Detail - ${subject.getName()}</h4>
                            </div>
                            <div class="widget-inner">

                                <div class="row">

                                    <div class="form-group col-12">
                                        <label class="col-form-label">Quiz Name</label>
                                        <div>
                                            <input readonly="" class="form-control" id="subject_name" name="name" type="text" value="${subject.name}">
                                        </div>
                                    </div>
                                    <div class="form-group col-12">
                                        <label class="col-form-label">Min Score</label>
                                        <div>
                                            <input readonly="" class="form-control" id="subject_name" name="name" type="text" value="${subject.miniumscore}">
                                        </div>
                                    </div>
                                    <div class="form-group col-12">
                                        <label class="col-form-label">Description</label>
                                        <div>
                                            <input readonly="" class="form-control" id="subject_name" name="name" type="text" value="${subject.getDescription()}">
                                        </div>
                                    </div>
                                    <div class="form-group col-12">
                                        <label class="col-form-label">Content</label>
                                        <div>
                                            ${subject.content}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>         
            </div>
        </div>
                                        <button onclick="window.location.href='quiz-management'" style="margin-left: 50px"class="btn btn-outline-dark">Back</button>
    </div>

    <!-- Footer Start -->
    <jsp:include page="templates/footer.jsp" />
    <!-- Footer End -->
    <script src="https://cdn.ckeditor.com/4.20.2/standard/ckeditor.js"></script>

    <!-- Initialize CKEditor -->
    <script>
        CKEDITOR.replace('content', {
            toolbar: [
                {name: 'basicstyles', items: ['Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript']},
                {name: 'paragraph', items: ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent']},
                {name: 'insert', items: ['Link', 'Unlink']},
                {name: 'styles', items: ['Format']},
                {name: 'tools', items: ['Maximize']}
            ],
            removeButtons: '',
            height: 200
        });
    </script>
</body>
</html>
