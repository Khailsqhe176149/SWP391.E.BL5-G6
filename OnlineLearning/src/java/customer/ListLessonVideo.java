/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package customer;

import dao.DAOLessonDetail;
import dao.DAOListLessonVideo;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        int lessonId = 1; // Mặc định là 1 nếu không có lessonId trong request
        if (lessonIdStr != null && !lessonIdStr.isEmpty()) {
            lessonId = Integer.parseInt(lessonIdStr);  // Chuyển từ String sang int
        }

        // Tạo DAO để lấy video và bài đọc
        DAOListLessonVideo dao = new DAOListLessonVideo();
        List<LessonVideos> videos = dao.getVideosByLesson(lessonId);

        // Kiểm tra nếu có video nào, lấy video đầu tiên trong danh sách
        if (videos != null && !videos.isEmpty()) {
            LessonVideos firstVideo = videos.get(0);  // Lấy video đầu tiên

            // Gửi danh sách video và video đầu tiên đến JSP
            request.setAttribute("lessonId", lessonId);
            request.setAttribute("videos", videos);
            request.setAttribute("videoTitle", firstVideo.getVideoTitle());
            request.setAttribute("videoURL", firstVideo.getVideoURL());
            request.setAttribute("videoDescription", firstVideo.getDescription());
        }

        // Lấy danh sách bài đọc cho lessonId
        List<LessonReading> lessonReadings = dao.getLessonReadingsByLessonId(lessonId);
        request.setAttribute("lessonReadings", lessonReadings);

        // Chuyển hướng đến trang JSP
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }

    // Xử lý POST request - Hiển thị video khi người dùng chọn
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy lessonId từ request parameter
        String lessonIdStr = request.getParameter("lessonId");
        int lessonId = 7; // Mặc định là 1 nếu không có lessonId trong request
        if (lessonIdStr != null && !lessonIdStr.isEmpty()) {
            lessonId = Integer.parseInt(lessonIdStr);  // Chuyển từ String sang int
        }

        // Tạo DAO để lấy video và bài đọc
        DAOListLessonVideo dao = new DAOListLessonVideo();
        List<LessonVideos> videos = dao.getVideosByLesson(lessonId);

        // Lấy videoId từ request parameter
        String videoIdStr = request.getParameter("videoId");
        if (videoIdStr != null && !videoIdStr.isEmpty()) {
            int videoId = Integer.parseInt(videoIdStr);

            // Tìm video theo videoId
            LessonVideos selectedVideo = null;
            for (LessonVideos video : videos) {
                if (video.getVideoId() == videoId) {
                    selectedVideo = video;
                    break;
                }
            }

            // Kiểm tra xem video có được tìm thấy không
            if (selectedVideo != null) {
                List<LessonReading> lessonReadings = dao.getLessonReadingsByLessonId(lessonId);
                request.setAttribute("lessonReadings", lessonReadings);
                request.setAttribute("videos", videos);
                request.setAttribute("videoTitle", selectedVideo.getVideoTitle());
                request.setAttribute("videoURL", selectedVideo.getVideoURL());
                request.setAttribute("videoDescription", selectedVideo.getDescription());
                request.getRequestDispatcher("/test.jsp").forward(request, response);
            } else {
                System.out.println("Video not found.");
                response.getWriter().write("Video not found.");
            }
        } else {
            System.out.println("Invalid videoId.");
            response.getWriter().write("Invalid video ID.");
        }
    }

}
