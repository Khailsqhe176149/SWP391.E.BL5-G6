/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Course;
import model.Users;

/**
 *
 * @author Admin
 */
public class DAOListMyCourses extends DBContext {

    public DAOListMyCourses() {
        super();
    }

    public int getTotalCoursesByUserId(int userId) {
        int totalCourses = 0;
        String sql = "SELECT COUNT(*) FROM CourseRegistrater WHERE UserID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalCourses = rs.getInt(1);  // Lấy tổng số khóa học
            }
        } catch (SQLException ex) {
            System.out.println("Error while counting courses: " + ex.getMessage());
        }

        return totalCourses;
    }

    public List<Course> getCoursesByUserId(int userId, int offset, int pageSize) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.Courseid, c.Name, c.Description, c.Price, c.Img, cr.Status "
                + "FROM Course c "
                + "JOIN CourseRegistrater cr ON c.Courseid = cr.CourseID "
                + "WHERE cr.UserID = ? "
                + "ORDER BY cr.RegistrationDate ASC "
                + // Sắp xếp theo ngày đăng ký
                "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";  // Phân trang

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, offset);  // Offset cho phân trang
            stmt.setInt(3, pageSize);  // Số lượng bản ghi mỗi trang

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt("Courseid");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                double price = rs.getDouble("Price");
                String img = rs.getString("Img");
                int status = rs.getInt("Status");  // Lấy trạng thái khóa học

                // Tạo đối tượng Course với thêm thuộc tính status
                courses.add(new Course(courseId, name, description, price, status, img));
            }
        } catch (SQLException ex) {
            System.out.println("Error while fetching courses: " + ex.getMessage());
        }

        return courses;
    }
    
    public int getTotalCoursesByUserIdAndStatus(int userId, int status) {
    int totalCount = 0;
    String sql = "SELECT COUNT(*) AS totalCount " +
                 "FROM Course c " +
                 "JOIN CourseRegistrater cr ON c.Courseid = cr.CourseID " +
                 "WHERE cr.UserID = ? AND cr.Status = ?";  // Lọc theo userID và status

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, userId);    // Lấy userId từ tham số
        stmt.setInt(2, status);    // Lọc theo status
        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            totalCount = rs.getInt("totalCount");  // Lấy tổng số bản ghi
        }
    } catch (SQLException ex) {
        System.out.println("Error while fetching total count: " + ex.getMessage());
    }

    return totalCount;
}

    public List<Course> getCoursesByUserIdAndStatus(int userId, int status, int offset, int pageSize) {
    List<Course> courses = new ArrayList<>();
    String sql = "SELECT c.Courseid, c.Name, c.Description, c.Price, c.Img, cr.Status " +
                 "FROM Course c " +
                 "JOIN CourseRegistrater cr ON c.Courseid = cr.CourseID " +
                 "WHERE cr.UserID = ? AND cr.Status = ? " +  // Lọc theo userID và status
                 "ORDER BY cr.RegistrationDate DESC " +  // Sắp xếp theo ngày đăng ký
                 "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";  // Phân trang

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, userId);       // Lấy userId từ tham số
        stmt.setInt(2, status);       // Lọc theo status
        stmt.setInt(3, offset);       // Offset cho phân trang
        stmt.setInt(4, pageSize);     // Số lượng bản ghi mỗi trang
        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int courseId = rs.getInt("Courseid");
            String name = rs.getString("Name");
            String description = rs.getString("Description");
            double price = rs.getDouble("Price");
            String img = rs.getString("Img");
            int courseStatus = rs.getInt("Status");  // Lấy trạng thái khóa học

            // Tạo đối tượng Course và thêm vào danh sách
           courses.add(new Course(courseId, name, description, price, status, img));
        }
    } catch (SQLException ex) {
        System.out.println("Error while fetching courses: " + ex.getMessage());
    }

    return courses;
}
    public Users getUserByID(int userID) {
        String sql = "SELECT userID, Name, Gender, Dob, Phone, Img, Address FROM Users WHERE userID = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            
            // Nếu có kết quả trả về, tạo đối tượng Users
            if (rs.next()) {
                return new Users(
                    rs.getInt("userID"),
                    rs.getString("Name"),
                    rs.getInt("Gender"),
                    rs.getDate("Dob"),
                    rs.getString("Phone"),
                    rs.getString("Img"),
                    rs.getString("Address")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error at getUserByID: " + e.getMessage());
        }
        
        return null; // Trả về null nếu không tìm thấy người dùng
    }

}
