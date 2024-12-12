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
@WebServlet(name="AddLessonVideo", urlPatterns={"/AddLessonVideo"})
public class AddLessonVideo extends HttpServlet {
   
   
    private DAOLessonDetail dao = new DAOLessonDetail();  // DAO để tương tác với cơ sở dữ liệu

    // Phương thức GET để lấy lessonId và chuyển tới JSP
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy lessonId từ tham số URL
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        
        // Chuyển lessonId vào JSP
        request.setAttribute("lessonId", lessonId);
        
        // Forward đến trang JSP
        request.getRequestDispatcher("/add-lesson-video.jsp").forward(request, response);
    }

    // Phương thức POST để xử lý thêm video
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy các tham số từ form
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        String videoTitle = request.getParameter("videoTitle");
        String description = request.getParameter("description");
        String videoURL = request.getParameter("videoURL");

        // Tạo đối tượng LessonVideo
        LessonVideos lessonVideo = new LessonVideos(0, videoURL, videoTitle, description);  // videoId tự động sinh từ DB

        // Thêm video vào cơ sở dữ liệu
        boolean success = dao.addLessonVideo(lessonVideo, lessonId);  // Gọi DAO để lưu video

        if (success) {
            // Nếu thành công, chuyển hướng đến danh sách video của bài học
            response.sendRedirect("ListVideoLesson?lessonId=" + lessonId);
        } else {
            // Nếu có lỗi, hiển thị thông báo lỗi
            request.setAttribute("errorMessage", "Failed to add video");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
