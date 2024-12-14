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
import model.Post;
import model.Slider;

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
               + "FROM CourseWithRegistrations WHERE Courseid IN (SELECT Courseid FROM Course WHERE Status = 1)  "
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
 
  public List<Slider> getActiveSliders() {
        List<Slider> sliders = new ArrayList<>();
        String query = "SELECT * FROM Slider WHERE Status = 1 ORDER BY Createdtime DESC";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Slider slider = new Slider(
                    rs.getInt("Sliderid"),
                    rs.getInt("Status"),
                    rs.getString("Img"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getDate("Createdtime"),
                    rs.getInt("slidercategoryid")
                );
                sliders.add(slider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sliders;
    }
 
     
public List<LatestPost> getLatestPosts(int limit) {
    List<LatestPost> latestPosts = new ArrayList<>();
    String sql = "SELECT TOP (?) p.Postid, p.Title, p.Content, p.Createdtime, u.Name AS AuthorName, p.Img, p.Status, p.Sliderid " +
                 "FROM Post p " +
                 "JOIN Users u ON p.authorid = u.userID " +
                 "ORDER BY p.Createdtime DESC"; 

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, limit);

        try (ResultSet rs = stmt.executeQuery()) {
           
            while (rs.next()) {
              
                LatestPost post = new LatestPost(
                    rs.getInt("Postid"),
                    rs.getString("Title"),
                    rs.getString("Content"),
                    new java.util.Date(rs.getTimestamp("Createdtime").getTime()), // Chuyển Timestamp sang Date
                    rs.getString("AuthorName"), // Lấy tên tác giả từ cột AuthorName
                    rs.getString("Img"),
                    rs.getInt("Status"),
                    rs.getInt("Sliderid")
                );
                // Thêm bài viết vào danh sách
                latestPosts.add(post);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return latestPosts;
}



    
     public static void main(String[] args) {
        DAOHome daoHome = new DAOHome();
        List<LatestPost> latestPosts = daoHome.getLatestPosts(5);  // Lấy 5 bài viết mới nhất

        for (LatestPost post : latestPosts) {
            System.out.println("Title: " + post.getTitle());
            System.out.println("Author: " + post.getAuthorName());
            System.out.println("Content: " + post.getContent());
            System.out.println("Created Time: " + post.getCreatedtime());
            System.out.println("----------------------------------------");
        }
    }
 
 
}

