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

        <style>
            /* Floating Button Styles */
            .floating-buttons {
                position: fixed;
                bottom: 20px;
                right: 20px;
                display: flex;
                gap: 10px;
                z-index: 1000; /* Ensures the buttons are always on top */
            }

            .floating-buttons button {
                padding: 10px 20px;
                font-size: 16px;
                border-radius: 5px;
                transition: all 0.3s ease;
                cursor: pointer;
                min-width: 150px;
            }

            .floating-buttons button:hover {
                opacity: 0.9;
            }

            .btn-outline-primary {
                background-color: #007bff;
                color: white;
                border: 1px solid #007bff;
            }

            .btn-outline-secondary {
                background-color: #6c757d;
                color: white;
                border: 1px solid #6c757d;
            }
        </style>

    </head>

    <body>
        <!-- Navbar Start  -->
        <jsp:include page="templates/navbar.jsp" />
        <!-- Navbar End  -->

        <!-- Sidebar and Main Content Start -->
        <div class="container-fluid" style="display: flex; min-height: 100vh;">

            <!-- Main Content -->
            <div class="main-content flex-grow-1 p-5">

                <!-- Thanh tìm kiếm -->

                <c:if test="${not empty sessionScope.notification}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert" style="text-align: center;">
                        ${sessionScope.notification}
                        <button type="button" class="close" data-bs-dismiss="alert" aria-label="Close">
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
                        <button type="button" class="close" data-bs-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        session.removeAttribute("notificationErr");
                    %>
                </c:if>
                <div class="row">
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Detail - ${subject.getName()}</h4>
                            </div>
                            <div class="widget-inner">

                                <form class="edit-profile m-b30" action="${pageContext.request.contextPath}/admin/subject-detail" method="post" onsubmit="return validateForm();">
                                    <div class="row">

                                        <div class="form-group col-12">
                                            <label class="col-form-label">Subject Name</label>
                                            <hr>
                                            <div>
                                                <input class="form-control" id="subject_name" name="name" type="text" value="${subject.name}">
                                                <input class="form-control"   name="action" type="hidden" value="edit">
                                                <input class="form-control"   name="id" type="hidden" value="${subject.subjectid}">
                                                <span id="name_error" class="text-danger"></span>
                                            </div>
                                        </div>
                                        <div class="form-group col-12" style="margin-top: 50px">
                                            <label class="col-form-label">Status</label>
                                            <hr>
                                            <div class="d-flex align-items-center">
                                                <div class="form-check me-3">
                                                    <input class="form-check-input" type="radio" id="status" name="status" value="1" <c:if test="${subject.status eq 1}">checked</c:if>>
                                                        <label class="form-check-label">Active</label>
                                                    </div>
                                                    <div class="form-check" style="margin-left: 10px">
                                                        <input class="form-check-input" type="radio" name="status" value="0" <c:if test="${subject.status eq 0}">checked</c:if>>
                                                        <label class="form-check-label">Inactive</label>
                                                    </div>
                                                    <span id="status_error" class="text-danger ms-3"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="floating-buttons">
                                            <button type="button" onclick="location.href = 'subject-management'" class="btn-outline-secondary">Back</button>
                                            <button type="submit" class="btn-outline-primary">Save changes</button>
                                        </div>
                                </div>
                                </form>
                            </div>
                        </div>
                    </div>         
                </div>
            </div>
        </div>

        <!-- Footer Start -->
    <jsp:include page="templates/footer.jsp" />
    <!-- Footer End -->
</body>
</html>
