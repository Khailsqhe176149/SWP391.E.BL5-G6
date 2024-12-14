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

        int lessonId = Integer.parseInt(request.getParameter("lessonId"));

        request.setAttribute("lessonId", lessonId);

        request.getRequestDispatcher("/add-lesson-reading.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String readingURL = request.getParameter("readingURL");

        LessonReading lessonReading = new LessonReading();
        lessonReading.setLessonId(lessonId);
        lessonReading.setTitle(title);
        lessonReading.setDescription(description);
        lessonReading.setReadingURL(readingURL);

        boolean success = dao.addLessonReading(lessonReading);

        if (success) {

            response.sendRedirect("ViewLessonReading?lessonId=" + lessonId);
        } else {

            request.setAttribute("errorMessage", "Failed to add new reading");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
