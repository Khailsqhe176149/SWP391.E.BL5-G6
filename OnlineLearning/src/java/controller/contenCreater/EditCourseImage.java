/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.contenCreater;

import dao.DAOCourses;
import model.Course;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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
 *
 * @author Admin
 */
@WebServlet(name = "EditCourseImage", urlPatterns = {"/EditCourseImage"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10, // 10MB
                 maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditCourseImage extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "img";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseIdStr = request.getParameter("courseId");
        Part filePart = request.getPart("image");  // Lấy file được chọn từ form
        
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = filePart.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

            // Tạo thư mục nếu chưa có
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Đảm bảo rằng file được lưu trữ đúng thư mục
            Path filePath = Path.of(uploadPath, fileName);
            Files.copy(filePart.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Lưu đường dẫn ảnh vào cơ sở dữ liệu
            String imgPath = File.separator + fileName;
            int courseId = Integer.parseInt(courseIdStr);
            Course course = new Course(courseId, null, 0, 0, null, imgPath, 0);

            DAOCourses courseDAO = new DAOCourses();
            boolean updated = courseDAO.updateCourseImage(course);

            // Kiểm tra và chuyển hướng
            if (updated) {
                response.sendRedirect("ManagerCourses");  // Quay lại danh sách khóa học
            } else {
                request.setAttribute("error", "Failed to update image.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }
}



