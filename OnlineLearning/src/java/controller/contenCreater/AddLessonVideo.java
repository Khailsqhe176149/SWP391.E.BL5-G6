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
import model.LessonVideos;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddLessonVideo", urlPatterns = {"/AddLessonVideo"})
public class AddLessonVideo extends HttpServlet {

    private DAOLessonDetail dao = new DAOLessonDetail();  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int lessonId = Integer.parseInt(request.getParameter("lessonId"));

        request.setAttribute("lessonId", lessonId);

        request.getRequestDispatcher("/add-lesson-video.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        String videoTitle = request.getParameter("videoTitle");
        String description = request.getParameter("description");
        String videoURL = request.getParameter("videoURL");

        LessonVideos lessonVideo = new LessonVideos(0, videoURL, videoTitle, description);

        boolean success = dao.addLessonVideo(lessonVideo, lessonId);

        if (success) {

            response.sendRedirect("ListVideoLesson?lessonId=" + lessonId);
        } else {

            request.setAttribute("errorMessage", "Failed to add video");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
