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
import java.util.List;
import model.Lesson;

/**
 *
 * @author Admin
 */
@WebServlet(name="LessonContent", urlPatterns={"/LessonContent"})
public class LessonContent extends HttpServlet {
    
    private DAOLesson dao = new DAOLesson(); // Đối tượng DAO để lấy dữ liệu từ DB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tất cả các bài học từ DAO
        List<Lesson> lessons = dao.getAllLessons();
        
        // Đưa danh sách bài học vào trong request để hiển thị trên JSP
        request.setAttribute("lessons", lessons);
        
        // Forward request đến trang JSP để hiển thị dữ liệu
        request.getRequestDispatcher("/list-lessons.jsp").forward(request, response);
    }

}
