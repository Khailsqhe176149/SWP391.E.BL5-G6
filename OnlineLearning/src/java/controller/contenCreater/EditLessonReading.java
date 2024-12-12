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
import model.LessonReading;

/**
 *
 * @author Admin
 */
@WebServlet(name="EditLessonReading", urlPatterns={"/EditLessonReading"})
public class EditLessonReading extends HttpServlet {
   
     private DAOLessonDetail dao = new DAOLessonDetail();

    // Hiển thị dữ liệu bài đọc trong form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int readingId = Integer.parseInt(request.getParameter("readingId"));  // Lấy readingId từ URL
        LessonReading lessonReading = dao.getLessonReadingById(readingId);   // Lấy thông tin bài đọc từ DAO

        if (lessonReading != null) {
            request.setAttribute("lessonReading", lessonReading);  // Gửi dữ liệu bài đọc vào trang JSP
            request.getRequestDispatcher("/edit-lesson-reading.jsp").forward(request, response);  // Chuyển đến trang Edit
        } else {
            request.setAttribute("errorMessage", "Lesson reading not found");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // Cập nhật thông tin bài đọc
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int readingId = Integer.parseInt(request.getParameter("readingId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String readingURL = request.getParameter("readingURL");

        // Tạo đối tượng LessonReading với dữ liệu mới
        LessonReading lessonReading = new LessonReading();
        lessonReading.setReadingId(readingId);
        lessonReading.setTitle(title);
        lessonReading.setDescription(description);
        lessonReading.setReadingURL(readingURL);

        // Cập nhật bài đọc trong cơ sở dữ liệu
        boolean success = dao.updateLessonReading(lessonReading);

        if (success) {
            response.sendRedirect("ViewLessonReading?lessonId=" + request.getParameter("lessonId"));
        } else {
            request.setAttribute("errorMessage", "Failed to update lesson reading");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
