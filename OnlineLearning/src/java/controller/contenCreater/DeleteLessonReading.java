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
@WebServlet(name="DeleteLessonReading", urlPatterns={"/DeleteLessonReading"})
public class DeleteLessonReading extends HttpServlet {
     private DAOLessonDetail dao = new DAOLessonDetail();
    
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tham số readingId và lessonId từ URL
        int readingId = Integer.parseInt(request.getParameter("readingId"));
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));

        // Thực hiện xóa bài đọc trong cơ sở dữ liệu
        boolean success = dao.deleteLessonReading(readingId);

        // Nếu xóa thành công, chuyển hướng về danh sách bài đọc của lesson
        if (success) {
            response.sendRedirect("ViewLessonReading?lessonId=" + lessonId);
        } else {
            // Nếu có lỗi, hiển thị thông báo lỗi
            request.setAttribute("errorMessage", "Failed to delete lesson reading");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
