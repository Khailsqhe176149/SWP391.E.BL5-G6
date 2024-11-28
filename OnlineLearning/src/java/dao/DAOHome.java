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

/**
 *
 * @author Admin
 */
public class DAOHome extends DBContext {

    // Constructor kế thừa từ DBContext
    public DAOHome() {
        super(); // Gọi constructor của lớp DBContext để khởi tạo kết nối
    }

 
    
 public List<CountCourse> getCoursesWithPagination(int pageNumber) {
    List<CountCourse> courses = new ArrayList<>();

    // Tính toán offset cho phân trang
    int offset = (pageNumber - 1) * 4;  // Sửa lại từ pageNumber * 4 thành (pageNumber - 1) * 4

    // SQL query để lấy khóa học có nhiều người đăng ký nhất và bao gồm cả price và img
    String sql = "WITH CourseWithRegistrations AS ("
               + "  SELECT c.Courseid, c.Name AS CourseName, COUNT(cr.UserID) AS NumberOfRegistrations, "
               + "         c.Price, c.Img "
               + "  FROM Course c "
               + "  LEFT JOIN CourseRegistrater cr ON c.Courseid = cr.CourseID "
               + "  GROUP BY c.Courseid, c.Name, c.Price, c.Img "
               + ") "
               + "SELECT Courseid, CourseName, NumberOfRegistrations, Price, Img "
               + "FROM CourseWithRegistrations "
               + "ORDER BY NumberOfRegistrations DESC "
               + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";

    try (PreparedStatement pst = connection.prepareStatement(sql)) {
        pst.setInt(1, offset);
        
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int courseId = rs.getInt("Courseid");
                String courseName = rs.getString("CourseName");
                int numberOfRegistrations = rs.getInt("NumberOfRegistrations");
                double price = rs.getDouble("Price");  // Lấy giá khóa học
                String img = rs.getString("Img");  // Lấy ảnh đại diện khóa học
                
                // Tạo đối tượng CountCourse và thêm vào danh sách
                courses.add(new CountCourse(courseId, courseName, numberOfRegistrations, price, img));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return courses;
}
}
