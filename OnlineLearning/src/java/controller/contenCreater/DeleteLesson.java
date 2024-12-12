/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.contenCreater;

import dao.DAOLesson;
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
@WebServlet(name="DeleteLesson", urlPatterns={"/DeleteLesson"})
public class DeleteLesson extends HttpServlet {
   
 private DAOLesson dao = new DAOLesson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy ID bài học từ tham số URL
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));

        // Thực hiện xóa bài học
        boolean success1 = dao.deleteCourseLesson(lessonId);
        boolean success2 = dao.deleteLessonVideo(lessonId);
        boolean success3 = dao.deleteLessonReading(lessonId);
        boolean success = dao.deleteLesson(lessonId);

        // Chuyển hướng về trang danh sách bài học sau khi xóa
        if (success) {
            response.sendRedirect("ListLesson");
        } else {
            // Nếu có lỗi, hiển thị thông báo lỗi
            request.setAttribute("errorMessage", "Failed to delete lesson");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
