/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.contenCreater;

import dao.DAOLesson;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lesson;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddNewLesson", urlPatterns = {"/AddNewLesson"})
public class AddNewLesson extends HttpServlet {

    private DAOLesson dao = new DAOLesson(); // DAO để tương tác với cơ sở dữ liệu

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward request đến trang JSP để hiển thị form thêm bài học
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form gửi lên
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        String description = request.getParameter("description");
        String dateStr = request.getParameter("date");

        // Chuyển đổi ngày tháng từ chuỗi
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Tạo đối tượng Lesson
        Lesson lesson = new Lesson();
        lesson.setName(name);
        lesson.setContent(content);
        lesson.setDescription(description);
        lesson.setDate(date);

        // Thêm bài học vào cơ sở dữ liệu
        boolean success = dao.addLesson(lesson);

        // Chuyển hướng đến trang danh sách bài học nếu thành công
        if (success) {
            response.sendRedirect("ListLesson");
        } else {
            request.setAttribute("errorMessage", "Failed to add lesson");
            request.getRequestDispatcher("/ListLesson").forward(request, response);
        }
    }

}
