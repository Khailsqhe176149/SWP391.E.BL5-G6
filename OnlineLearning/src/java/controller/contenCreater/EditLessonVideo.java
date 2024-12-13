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
@WebServlet(name = "EditLessonVideo", urlPatterns = {"/EditLessonVideo"})
public class EditLessonVideo extends HttpServlet {

    private DAOLessonDetail dao = new DAOLessonDetail();

    // Phương thức GET để hiển thị thông tin video cần chỉnh sửa
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int videoId = Integer.parseInt(request.getParameter("videoId")); // Lấy videoId từ URL
        // Lấy thông tin lessonId từ request
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));

        LessonVideos lessonVideo = dao.getLessonVideoById(videoId); // Lấy video từ DB

        if (lessonVideo != null) {
            request.setAttribute("lessonId", lessonId);
            request.setAttribute("lessonVideo", lessonVideo); // Chuyển video vào request
            request.getRequestDispatcher("/edit-lesson-videos.jsp").forward(request, response); // Forward tới trang edit
        } else {
            request.setAttribute("errorMessage", "Video not found");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // Phương thức POST để cập nhật thông tin video vào cơ sở dữ liệu
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int videoId = Integer.parseInt(request.getParameter("videoId"));
        String videoTitle = request.getParameter("videoTitle");
        String description = request.getParameter("description");
        String videoURL = request.getParameter("videoURL");

        // Tạo đối tượng LessonVideos với thông tin mới
        LessonVideos lessonVideo = new LessonVideos(videoId, videoURL, videoTitle, description);

        // Cập nhật video trong cơ sở dữ liệu
        boolean success = dao.updateLessonVideo(lessonVideo);

        if (success) {
            response.sendRedirect("ListVideoLesson?lessonId=" + request.getParameter("lessonId"));
        } else {
            request.setAttribute("errorMessage", "Failed to update video");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
