/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package customer;

import dao.DAOLessonDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Lesson;
import model.LessonReading;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ViewLessonDetail", urlPatterns = {"/ViewLessonDetail"})
public class ViewLessonDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Trong Servlet
        DAOLessonDetail dao = new DAOLessonDetail();
        List<LessonReading> lessonReadings = dao.getLessonReadingsByLessonId(1); // Lấy bài đọc cho lessonId = 1
        request.setAttribute("lessonReadings", lessonReadings);
        request.getRequestDispatcher("/test.jsp").forward(request, response);

    }

}
