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
import model.Lesson;

/**
 *
 * @author Admin
 */
@WebServlet(name="EditLesson", urlPatterns={"/EditLesson"})
public class EditLesson extends HttpServlet {
   
    private DAOLesson dao = new DAOLesson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy Lesson ID từ tham số URL
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));

        // Lấy thông tin bài học từ cơ sở dữ liệu
        Lesson lesson = dao.getLessonById(lessonId);

        if (lesson != null) {
            // Đưa dữ liệu bài học vào request để hiển thị trên form
            request.setAttribute("lesson", lesson);
            request.getRequestDispatcher("/edit-lesson.jsp").forward(request, response);
        } else {
            // Nếu không tìm thấy bài học, chuyển hướng đến trang lỗi
            request.setAttribute("errorMessage", "Lesson not found");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
       @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        // Lấy thông tin bài học từ form
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        String name = request.getParameter("name");

        // Lấy và chuyển đổi ngày tháng từ chuỗi (dạng yyyy-MM-dd) sang java.sql.Date
        String dateString = request.getParameter("date");
        java.sql.Date date = null;
        
        if (dateString != null && !dateString.isEmpty()) {
            date = java.sql.Date.valueOf(dateString); // Dùng Date.valueOf() để chuyển đổi chuỗi
        }

        String content = request.getParameter("content");
        String description = request.getParameter("description");

        // Tạo đối tượng Lesson từ dữ liệu form
        Lesson lesson = new Lesson();
        lesson.setLessonid(lessonId);
        lesson.setName(name);
        lesson.setDate(date);
        lesson.setContent(content);
        lesson.setDescription(description);

        // Cập nhật bài học trong cơ sở dữ liệu
        boolean success = dao.updateLesson(lesson);

        if (success) {
            // Nếu cập nhật thành công, chuyển hướng đến trang danh sách bài học
            response.sendRedirect("ListLesson");
        } else {
            // Nếu có lỗi, hiển thị thông báo lỗi
            request.setAttribute("errorMessage", "Failed to update lesson");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Xử lý lỗi chung
        request.setAttribute("errorMessage", "An error occurred while updating the lesson.");
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}

}
