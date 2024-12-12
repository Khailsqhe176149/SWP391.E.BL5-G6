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
import java.util.List;
import model.LessonVideos;

/**
 *
 * @author Admin
 */
@WebServlet(name="ListVideoLesson", urlPatterns={"/ListVideoLesson"})
public class ListVideoLesson extends HttpServlet {
   private DAOLessonDetail dao = new DAOLessonDetail();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int lessonId = Integer.parseInt(request.getParameter("lessonId")); // Lấy lessonId từ tham số URL

        // Gọi phương thức getLessonVideosByLessonId để lấy danh sách video
        List<LessonVideos> videos = dao.getLessonVideosByLessonId(lessonId);

        // Đưa danh sách video vào request attribute để hiển thị trên trang JSP
        request.setAttribute("videos", videos);
         request.setAttribute("lessonId", lessonId);

        // Chuyển hướng đến trang JSP để hiển thị danh sách video
        request.getRequestDispatcher("/list-video-lesson.jsp").forward(request, response);
    }

}
