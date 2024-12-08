/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.LessonReading;
import model.LessonVideos;

/**
 *
 * @author Admin
 */
public class DAOListLessonVideo extends DBContext {

    // Constructor kế thừa từ DBContext
    public DAOListLessonVideo() {
        super(); // Gọi constructor của lớp DBContext để khởi tạo kết nối
    }
       // Phương thức lấy tất cả video của một lesson
    public List<LessonVideos> getVideosByLesson(int lessonId) {
        List<LessonVideos> videos = new ArrayList<>();
        String sql = "SELECT Videoid, VideoURL, VideoTitle, Description FROM LessonVideos WHERE Lessonid = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set lessonId vào PreparedStatement
            stmt.setInt(1, lessonId);
            
            // Thực thi truy vấn và lấy kết quả
            ResultSet rs = stmt.executeQuery();
            
            // Duyệt qua kết quả và thêm video vào danh sách
            while (rs.next()) {
                int videoId = rs.getInt("Videoid");
                String videoURL = rs.getString("VideoURL");
                String videoTitle = rs.getString("VideoTitle");
                String description = rs.getString("Description");
                
                LessonVideos video = new LessonVideos(videoId, videoURL, videoTitle, description);
                videos.add(video);
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting videos: " + ex.getMessage());
        }
        
        return videos;
    }
    
     // Hàm lấy danh sách LessonReading theo lessonId
    public List<LessonReading> getLessonReadingsByLessonId(int lessonId) {
        List<LessonReading> lessonReadings = new ArrayList<>();
        String sql = "SELECT * FROM LessonReading WHERE LessonId = ?"; // Câu lệnh SQL

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Set giá trị cho tham số trong câu lệnh SQL
            ps.setInt(1, lessonId);

            // Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            // Duyệt qua kết quả và tạo đối tượng LessonReading
            while (rs.next()) {
                int readingId = rs.getInt("ReadingId");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String readingURL = rs.getString("ReadingURL");

                // Thêm bài đọc vào danh sách
                lessonReadings.add(new LessonReading(readingId, lessonId, title, description, readingURL));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log lỗi nếu có
        }

        return lessonReadings;
    }
    public static void main(String[] args) {
        // Tạo đối tượng DAOLessonDetail để sử dụng hàm
        DAOListLessonVideo dao = new DAOListLessonVideo();
        
        // ID của lesson cần kiểm tra
        int lessonId = 1; // Bạn có thể thay đổi lessonId theo dữ liệu thực tế
        
        // Lấy danh sách video của lesson
        List<LessonVideos> videos = dao.getVideosByLesson(lessonId);
        
        // Hiển thị danh sách video ra console
        System.out.println("Videos for Lesson ID: " + lessonId);
        for (LessonVideos video : videos) {
            System.out.println("Video ID: " + video.getVideoId());
            System.out.println("Video URL: " + video.getVideoURL());
            System.out.println("Video Title: " + video.getVideoTitle());
            System.out.println("Description: " + video.getDescription());
            System.out.println("----------------------------");
        }
    }
}
