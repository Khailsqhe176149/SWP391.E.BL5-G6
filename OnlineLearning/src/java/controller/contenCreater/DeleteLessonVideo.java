/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.contenCreater;

import dao.DAOLessonDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteLessonVideo", urlPatterns = {"/DeleteLessonVideo"})
public class DeleteLessonVideo extends HttpServlet {

    private DAOLessonDetail dao = new DAOLessonDetail();  // Dao để tương tác với cơ sở dữ liệu

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int videoId = Integer.parseInt(request.getParameter("videoId"));  // Lấy videoId từ tham số URL
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));  // Lấy lessonId từ tham số URL

        // Gọi phương thức deleteLessonVideo để xóa video
        boolean success = dao.deleteLessonVideo(videoId);

        if (success) {
            // Nếu xóa thành công, chuyển hướng về trang danh sách video
            response.sendRedirect("ListVideoLesson?lessonId=" + lessonId);
        } else {
            // Nếu có lỗi, hiển thị thông báo lỗi
            request.setAttribute("errorMessage", "Failed to delete video");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
