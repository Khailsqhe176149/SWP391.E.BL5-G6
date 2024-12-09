/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dao.DAOListLessonVideo;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.LessonReading;
import model.LessonVideos;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ListLessonVideo", urlPatterns = {"/ListLessonVideo"})
public class ListLessonVideo extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy lessonId từ request parameter
        String lessonIdStr = request.getParameter("lessonId");
        int lessonId = Integer.parseInt(lessonIdStr);

        String courseIdStr = request.getParameter("courseId");
        int courseId = Integer.parseInt(courseIdStr);

        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        if (userID == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        DAOListLessonVideo dao = new DAOListLessonVideo();

        List<LessonVideos> videos = dao.getVideosByLesson(lessonId);

        if (videos != null && !videos.isEmpty()) {
            LessonVideos firstVideo = videos.get(0);

            // Gửi danh sách video và video đầu tiên đến JSP
            request.setAttribute("courseId", courseId);
            request.setAttribute("lessonId", lessonId);
            request.setAttribute("videos", videos);
            request.setAttribute("videoTitle", firstVideo.getVideoTitle());
            request.setAttribute("videoURL", firstVideo.getVideoURL());
            request.setAttribute("videoDescription", firstVideo.getDescription());
        } else {

            request.setAttribute("videoMessage", "No videos available for this lesson.");
        }

        List<LessonReading> lessonReadings = dao.getLessonReadingsByLessonId(lessonId);
        request.setAttribute("lessonReadings", lessonReadings);

        DAOListLessonVideo courseDao = new DAOListLessonVideo();
        courseDao.updateCourseStatusInProgress(courseId, userID);

        request.getRequestDispatcher("/list-lesson-video.jsp").forward(request, response);
    }

    // Xử lý POST request - Hiển thị video khi người dùng chọn
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String courseIdStr = request.getParameter("courseId");
        int courseId = Integer.parseInt(courseIdStr);
        String lessonIdStr = request.getParameter("lessonId");
        if (lessonIdStr == null || lessonIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Lesson ID is required");
            return;
        }

        int lessonId = Integer.parseInt(lessonIdStr);

        // DAO để lấy video và bài đọc
        DAOListLessonVideo dao = new DAOListLessonVideo();
        List<LessonVideos> videos = dao.getVideosByLesson(lessonId);

        String videoIdStr = request.getParameter("videoId");

        int videoId = Integer.parseInt(videoIdStr);

        LessonVideos selectedVideo = null;
        for (LessonVideos video : videos) {
            if (video.getVideoId() == videoId) {
                selectedVideo = video;
                break;
            }
        }

        // Kiểm tra xem video có được tìm thấy không
        if (selectedVideo == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Video not found");
            return;
        }

        // Lấy bài đọc cho lessonId
        List<LessonReading> lessonReadings = dao.getLessonReadingsByLessonId(lessonId);

        // Đưa thông tin vào request
        request.setAttribute("courseId", courseId);
        request.setAttribute("lessonId", lessonId);
        request.setAttribute("lessonReadings", lessonReadings);
        request.setAttribute("videos", videos);
        request.setAttribute("videoTitle", selectedVideo.getVideoTitle());
        request.setAttribute("videoURL", selectedVideo.getVideoURL());
        request.setAttribute("videoDescription", selectedVideo.getDescription());

        // Chuyển hướng đến trang JSP
        request.getRequestDispatcher("/list-lesson-video.jsp").forward(request, response);
    }

}
