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
@WebServlet(name = "AddLessonReading", urlPatterns = {"/AddLessonReading"})
public class AddLessonReading extends HttpServlet {

    private DAOLessonDetail dao = new DAOLessonDetail();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin lessonId từ request
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));

        // Truyền lessonId vào request để sử dụng trong trang JSP (addLessonReading.jsp)
        request.setAttribute("lessonId", lessonId);

        // Chuyển hướng đến trang thêm bài đọc
        request.getRequestDispatcher("/add-lesson-reading.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin từ form submit
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String readingURL = request.getParameter("readingURL");

        // Tạo đối tượng LessonReading
        LessonReading lessonReading = new LessonReading();
        lessonReading.setLessonId(lessonId);
        lessonReading.setTitle(title);
        lessonReading.setDescription(description);
        lessonReading.setReadingURL(readingURL);

        // Gọi DAO để thêm bài đọc
        boolean success = dao.addLessonReading(lessonReading); // Giả sử bạn đã có phương thức addLessonReading trong DAO

        if (success) {
            // Nếu thêm thành công, chuyển hướng về trang xem bài đọc của bài học
            response.sendRedirect("ViewLessonReading?lessonId=" + lessonId);
        } else {
            // Nếu có lỗi, hiển thị thông báo lỗi
            request.setAttribute("errorMessage", "Failed to add new reading");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
