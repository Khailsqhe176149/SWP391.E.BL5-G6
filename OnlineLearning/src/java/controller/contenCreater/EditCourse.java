/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.contenCreater;

import dao.DAOCourses;
import model.Course;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import model.Subjects;

/**
 * Servlet to handle editing a course
 */
@WebServlet(name = "EditCourse", urlPatterns = {"/EditCourse"})
public class EditCourse extends HttpServlet {

    private DAOCourses courseDAO = new DAOCourses();
    private static final String UPLOAD_DIRECTORY = "img";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseIdStr = request.getParameter("courseId");

        try {
            // Chuyển đổi courseId từ String sang int
            int courseId = Integer.parseInt(courseIdStr);

            // Lấy thông tin khóa học
            Course course = courseDAO.getCourseById(courseId);

            // Kiểm tra nếu course không tồn tại, trả về lỗi
            if (course == null) {
                request.setAttribute("error", "Course not found");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            // Lấy tất cả môn học để hiển thị trong dropdown
            List<Subjects> subjects = courseDAO.getAllSubjects();

            // Truyền dữ liệu vào request
            request.setAttribute("subjects", subjects);
            request.setAttribute("course", course);

            // Chuyển hướng đến trang edit course
            request.getRequestDispatcher("edit-courses.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Nếu có lỗi khi chuyển đổi courseId, trả về trang lỗi
            request.setAttribute("error", "Invalid course ID format");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseIdStr = request.getParameter("courseId");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String subjectIdStr = request.getParameter("subjectId");
        String statusStr = request.getParameter("status");

    

        try {
            int courseId = Integer.parseInt(courseIdStr);
            double price = Double.parseDouble(priceStr);
            int subjectId = Integer.parseInt(subjectIdStr);
            int status = Integer.parseInt(statusStr);

            // Tạo đối tượng Course từ dữ liệu đã nhập
            Course course = new Course(courseId, name, subjectId, price, description, null, status);

            // Cập nhật khóa học vào cơ sở dữ liệu
            boolean updated = courseDAO.updateCourse(course);

            // Kiểm tra kết quả cập nhật và chuyển hướng
            if (updated) {
                response.sendRedirect("ManagerCourses"); // Cập nhật thành công, quay lại danh sách
            } else {
                request.setAttribute("error", "Failed to update course.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid input data.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
