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
import model.CountCourse;
import model.LatestPost;
import model.LessonReading;
import model.LessonVideos;
import model.Post;
import model.Slider;

/**
 *
 * @author Admin
 */
public class DAOLessonDetail extends DBContext {

    // Constructor kế thừa từ DBContext
    public DAOLessonDetail() {
        super(); // Gọi constructor của lớp DBContext để khởi tạo kết nối
    }

    // Hàm lấy danh sách LessonReading theo lessonId
    public List<LessonReading> getLessonReadingsByLessonId(int lessonId) {
        List<LessonReading> readings = new ArrayList<>();
        String query = "SELECT * FROM LessonReading WHERE LessonId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, lessonId);
            ResultSet rs = ps.executeQuery();

            // Duyệt qua kết quả và tạo danh sách LessonReading
            while (rs.next()) {
                LessonReading reading = new LessonReading(
                        rs.getInt("ReadingId"),
                        rs.getInt("LessonId"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("ReadingURL")
                );
                readings.add(reading);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readings;
    }

    public boolean addLessonReading(LessonReading lessonReading) {
        String query = "INSERT INTO LessonReading (LessonId, Title, Description, ReadingURL) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, lessonReading.getLessonId());
            ps.setString(2, lessonReading.getTitle());
            ps.setString(3, lessonReading.getDescription());
            ps.setString(4, lessonReading.getReadingURL());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;  // Return true if insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false if there's an error
    }

    public boolean deleteLessonReading(int readingId) {
        String query = "DELETE FROM LessonReading WHERE ReadingId = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, readingId);  // Gán giá trị readingId vào câu lệnh SQL
            int rowsAffected = ps.executeUpdate(); // Thực thi câu lệnh và lấy số lượng dòng bị ảnh hưởng
            return rowsAffected > 0; // Nếu có ít nhất một dòng bị ảnh hưởng, trả về true
        } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu có
        }
        return false; // Trả về false nếu có lỗi hoặc không có dòng nào bị ảnh hưởng
    }

    public boolean updateLessonReading(LessonReading lessonReading) {
        String query = "UPDATE LessonReading SET Title = ?, Description = ?, ReadingURL = ? WHERE ReadingId = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, lessonReading.getTitle());
            ps.setString(2, lessonReading.getDescription());
            ps.setString(3, lessonReading.getReadingURL());
            ps.setInt(4, lessonReading.getReadingId());

            int rowsAffected = ps.executeUpdate(); // Thực thi câu lệnh cập nhật
            return rowsAffected > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu có
        }
        return false; // Trả về false nếu không thành công
    }

    public LessonReading getLessonReadingById(int readingId) {
        // Câu truy vấn SQL
        String query = "SELECT * FROM LessonReading WHERE ReadingId = ?";

        // Tạo đối tượng LessonReading để chứa dữ liệu trả về
        LessonReading lessonReading = null;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            // Set giá trị cho dấu ? trong câu truy vấn
            ps.setInt(1, readingId);

            // Thực thi câu truy vấn và lấy kết quả
            ResultSet rs = ps.executeQuery();

            // Nếu có kết quả, tạo đối tượng LessonReading từ dữ liệu
            if (rs.next()) {
                lessonReading = new LessonReading();
                lessonReading.setReadingId(rs.getInt("ReadingId"));
                lessonReading.setLessonId(rs.getInt("LessonId"));
                lessonReading.setTitle(rs.getString("Title"));
                lessonReading.setDescription(rs.getString("Description"));
                lessonReading.setReadingURL(rs.getString("ReadingURL"));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu có
        }

        // Trả về đối tượng LessonReading hoặc null nếu không tìm thấy
        return lessonReading;
    }

    // Phương thức để lấy danh sách video theo lessonId
    public List<LessonVideos> getLessonVideosByLessonId(int lessonId) {
        List<LessonVideos> videos = new ArrayList<>();
        String query = "SELECT * FROM LessonVideos WHERE Lessonid = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, lessonId); // Gán lessonId vào câu truy vấn
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Tạo đối tượng LessonVideos từ kết quả truy vấn
                int videoId = rs.getInt("Videoid");
                String videoURL = rs.getString("VideoURL");
                String videoTitle = rs.getString("VideoTitle");
                String description = rs.getString("Description");

                // Thêm video vào danh sách
                LessonVideos video = new LessonVideos(videoId, videoURL, videoTitle, description);
                videos.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // In lỗi ra console nếu có lỗi xảy ra
        }
        return videos;  // Trả về danh sách video
    }

    public boolean deleteLessonVideo(int videoId) {
        String query = "DELETE FROM LessonVideos WHERE Videoid = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, videoId);  // Gán videoId vào câu truy vấn
            int rowsAffected = ps.executeUpdate();  // Thực thi câu lệnh DELETE

            return rowsAffected > 0;  // Nếu có dòng bị xóa, trả về true
        } catch (SQLException e) {
            e.printStackTrace();  // In lỗi ra console nếu có lỗi
            return false;  // Nếu có lỗi, trả về false
        }
    }
     public boolean addLessonVideo(LessonVideos video, int lessonId) {
        String query = "INSERT INTO LessonVideos (Lessonid, VideoURL, VideoTitle, Description) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, lessonId);  // Gán lessonId
            ps.setString(2, video.getVideoURL());  // Gán videoURL
            ps.setString(3, video.getVideoTitle());  // Gán videoTitle
            ps.setString(4, video.getDescription());  // Gán description

            int rowsAffected = ps.executeUpdate();  // Thực thi câu lệnh INSERT

            return rowsAffected > 0;  // Nếu có dòng bị ảnh hưởng, trả về true
        } catch (SQLException e) {
            e.printStackTrace();  // In lỗi ra console nếu có lỗi
            return false;  // Nếu có lỗi, trả về false
        }
    }
     
     public boolean updateLessonVideo(LessonVideos lessonVideo) {
    String query = "UPDATE LessonVideos SET VideoURL = ?, VideoTitle = ?, Description = ? WHERE Videoid = ?";
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setString(1, lessonVideo.getVideoURL());
        ps.setString(2, lessonVideo.getVideoTitle());
        ps.setString(3, lessonVideo.getDescription());
        ps.setInt(4, lessonVideo.getVideoId());

        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0; // Trả về true nếu có ít nhất một dòng được cập nhật
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
     
     
    // Hàm lấy video theo videoId
    public LessonVideos getLessonVideoById(int videoId) {
        String query = "SELECT * FROM LessonVideos WHERE Videoid = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, videoId); // Set videoId vào câu truy vấn

            ResultSet rs = ps.executeQuery(); // Thực thi truy vấn
            if (rs.next()) {
                // Tạo đối tượng LessonVideos từ kết quả truy vấn
                int lessonId = rs.getInt("Lessonid");
                String videoURL = rs.getString("VideoURL");
                String videoTitle = rs.getString("VideoTitle");
                String description = rs.getString("Description");

                // Trả về đối tượng LessonVideos
                return new LessonVideos(videoId, videoURL, videoTitle, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Nếu không tìm thấy video, trả về null
    }
}
