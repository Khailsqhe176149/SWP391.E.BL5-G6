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
    
}
