<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>List Courses</title>
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
        <!-- DataTables CSS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/jquery.dataTables.min.css">
        <!-- DataTables JS -->
        <script src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>



        <style>
            .sidebar {
                width: 300px; /* Set width for sidebar */
                height: 100vh; /* Set height to full viewport height */
                overflow-y: auto; /* Enable vertical scroll if content exceeds height */
                padding-top: 20px;
            }
            .badge-success {
                background-color: #28a745 !important; /* Màu xanh cho Active */
                color: white !important;             /* Chữ màu trắng */
            }

            .badge-danger {
                background-color: #dc3545 !important; /* Màu đỏ cho Inactive */
                color: white !important;              /* Chữ màu trắng */
            }

            .badge {
                padding: 0.5em 0.75em;  /* Khoảng cách bên trong */
                border-radius: 0.25rem; /* Góc bo tròn */
                font-size: 0.875rem;    /* Kích thước chữ */
                font-weight: bold;      /* Đậm chữ */
            }

        </style>

    </head>

    <body>
        <!-- Navbar Start  -->
        <jsp:include page="templates/navbar.jsp" />
        <!-- Navbar End  -->

        <!-- Sidebar and Main Content Start -->
        <div class="container-fluid" style="display: flex; min-height: 100vh;">
            <!-- Sidebar -->

            <jsp:include page="templates/sidebar.jsp" />




            <!-- Main Content -->
            <div class="col-md-9 ps-4">
                <h2 class="text-center mb-4">Manage Courses</h2>
                <div class="table-responsive">
                    <!-- Table to display course data -->
                    <table class="table table-bordered table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>ID</th>
                                <th>Image</th>
                                <th>Course Name</th>
                                <th>Price</th>
                                <th>Subject</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody id="courseTable">
                            <c:forEach var="course" items="${courses}" varStatus="status">
                                <tr>
                                    
                                    <td>${status.index + 1}</td> 
                                    <td>${course.courseId}</td>
                                    <td>
                                        <!-- Image with click handler to open modal -->
                                        <img src="<c:out value='img/${course.img}' />" alt="${course.name}" width="100" height="70" class="img-thumbnail" data-toggle="modal" data-target="#updateImageModal" data-course-id="${course.courseId}" data-current-img="${course.img}">
                                    </td>
                                    <td>${course.name}</td>
                                    <td>${course.price}</td>
                                    <td>${course.subjectName}</td>
                                    <td>
                                        <span class="badge ${course.status == 1 ? 'badge-success' : 'badge-danger'}">
                                            ${course.status == 1 ? 'Active' : 'Inactive'}
                                        </span>
                                    </td>
                                    <td>
                                        <form action="EditCourse" method="GET" style="display:inline;">
                                            <input type="hidden" name="courseId" value="${course.courseId}">
                                            <button type="submit" class="btn btn-primary btn-sm">
                                                <i class="fas fa-edit"></i> Edit
                                            </button>
                                        </form>
                                        <button class="btn btn-danger btn-sm delete-course" data-id="${course.courseId}">
                                            <i class="fas fa-trash"></i> Delete
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <!-- Modal to upload new image -->
                    <div class="modal fade" id="updateImageModal" tabindex="-1" role="dialog" aria-labelledby="updateImageModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="updateImageModalLabel">Update Course Image</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>

                                <!-- Form to upload new image -->
                                <form action="EditCourseImage" method="POST" enctype="multipart/form-data" id="imageUploadForm">
                                    <input type="hidden" name="courseId" id="courseId">

                                    <div class="form-group">
                                        <label for="image" class="form-label">Chọn ảnh:</label>
                                        <input type="file" id="image" name="image" accept="image/*" class="form-control" />
                                    </div>

                                    <button type="submit" class="btn btn-primary">Upload Image</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
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
        <!-- jQuery script to handle button actions -->

        <script>
            // Khi modal mở, điền thông tin vào form
            $('#updateImageModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget);  // Element đã click
                var courseId = button.data('course-id'); // Lấy courseId từ data
                // Điền courseId vào hidden input trong form
                var modal = $(this);
                modal.find('#courseId').val(courseId);
            });

            // Submit form khi bấm nút "Upload Image"
            $('#imageUploadForm').on('submit', function (e) {
                e.preventDefault(); // Ngăn không cho form submit mặc định

                var formData = new FormData(this);

                $.ajax({
                    url: $(this).attr('action'),
                    type: 'POST',
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function (response) {
                        $('#updateImageModal').modal('hide'); // Đóng modal khi thành công
                        location.reload();  // Reload lại trang sau khi upload
                    },
                    error: function (xhr, status, error) {
                        // Không hiển thị thông báo lỗi nữa
                        $('#updateImageModal').modal('hide'); // Đóng modal nếu có lỗi
                        location.reload();  // Reload lại trang dù có lỗi
                    }
                });
            });
        </script>




        <script>
            // Đảm bảo script được thực thi sau khi DOM đã sẵn sàng
            $(document).ready(function () {
                // Xử lý xóa khóa học
                $(document).on("click", ".delete-course", function () {
                    const courseId = $(this).data("id"); // Lấy courseId từ data-id
                    console.log("Deleting Course ID:", courseId); // Debug để kiểm tra giá trị

                    if (confirm("Are you sure you want to delete this course?")) {
                        // AJAX request để xóa khóa học
                        $.ajax({
                            url: "DeleteCourse", // URL servlet xử lý
                            type: "POST", // Phương thức POST
                            data: {courseId: courseId}, // Gửi courseId đến servlet
                            success: function (response) {
                                console.log("Response:", response); // Debug response
                                alert("Course deleted successfully!");
                                location.reload(); // Reload lại trang sau khi xóa
                            },
                            error: function (xhr) {
                                console.error("Error deleting course:", xhr.responseText); // Debug lỗi
                                if (xhr.status === 400) {
                                    alert("Cannot delete course with existing registrations.");
                                } else {
                                    alert("Failed to delete the course. Please try again.");
                                }
                            }
                        });
                    }
                });

                // Xử lý chỉnh sửa khóa học
                $(document).on("click", ".edit-course", function () {
                    const courseId = $(this).data("id"); // Lấy courseId từ data-id
                    console.log("Editing Course ID:", courseId); // Debug để kiểm tra giá trị
                    if (courseId) {
                        window.location.href = `EditCourse?courseId=${courseId}`; // Chuyển hướng đến trang chỉnh sửa
                    } else {
                        alert("Course ID not found!");
                    }
                });
            });

        </script>
        <script>
            $(document).ready(function () {

                $('.table').DataTable({
                    paging: true,
                    searching: true,
                    ordering: true,
                    lengthChange: true,
                    info: true,
                    language: {
                        search: "Search Course:",
                        lengthMenu: "Show _MENU_ courses per page",
                        info: "Showing _START_ to _END_ of _TOTAL_ courses",
                        paginate: {
                            previous: "Previous",
                            next: "Next"
                        }
                    }
                });
            });
        </script>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
