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

/**
 *
 * @author Admin
 */
public class DAOListCourses extends DBContext {

    // Constructor kế thừa từ DBContext
    public DAOListCourses() {
        super(); // Gọi constructor của lớp DBContext để khởi tạo kết nối
    }

    // Hàm lấy tất cả các course từ database
    public List<Course> getCoursesWithPagination(int pageIndex, int pageSize) {
        List<Course> courses = new ArrayList<>();

        // Tính toán offset và limit cho phân trang
        int offset = (pageIndex - 1) * pageSize;

        String query = "SELECT Courseid, Name, Subjectid, Price, Authorid, Description, Img, Createdtime, Status, Tag "
                + "FROM Course "
                + "ORDER BY Createdtime DESC "
                + // Có thể sắp xếp theo bất kỳ trường nào
                "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";  // Câu lệnh SQL phân trang cho SQL Server

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int courseId = rs.getInt("Courseid");
                    String name = rs.getString("Name");
                    int subjectId = rs.getInt("Subjectid");
                    double price = rs.getDouble("Price");
                    int authorId = rs.getInt("Authorid");
                    String description = rs.getString("Description");
                    String img = rs.getString("Img");
                    Date createdTime = rs.getDate("Createdtime");
                    int status = rs.getInt("Status");
                    String tag = rs.getString("Tag");

                    Course course = new Course(courseId, name, subjectId, price, authorId, description, img, createdTime, status, tag);
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public int getTotalCourses() {
        int totalCourses = 0;
        String query = "SELECT COUNT(*) FROM Course";  // Truy vấn đếm tổng số khóa học

        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                totalCourses = rs.getInt(1);  // Lấy kết quả từ câu lệnh COUNT
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalCourses;
    }
    
    
    
    

    public List<String> getAllSubjects() {
        List<String> subjects = new ArrayList<>();
        String query = "SELECT Name FROM Subjects WHERE Status = 1";  // Lọc những môn học có trạng thái active

        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                subjects.add(rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public List<Course> getCoursesBySubject(String subjectName, int pageIndex, int pageSize) {
        List<Course> courses = new ArrayList<>();

        int offset = (pageIndex - 1) * pageSize;
        String query = "SELECT Courseid, Name, Subjectid, Price, Authorid, Description, Img, Createdtime, Status, Tag "
                + "FROM Course WHERE Subjectid IN (SELECT Subjectid FROM Subjects WHERE Name = ?) "
                + "ORDER BY Createdtime DESC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, subjectName);
            ps.setInt(2, offset);
            ps.setInt(3, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int courseId = rs.getInt("Courseid");
                    String name = rs.getString("Name");
                    int subjectId = rs.getInt("Subjectid");
                    double price = rs.getDouble("Price");
                    int authorId = rs.getInt("Authorid");
                    String description = rs.getString("Description");
                    String img = rs.getString("Img");
                    Date createdTime = rs.getDate("Createdtime");
                    int status = rs.getInt("Status");
                    String tag = rs.getString("Tag");

                    Course course = new Course(courseId, name, subjectId, price, authorId, description, img, createdTime, status, tag);
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public int getTotalCoursesBySubject(String subjectName) {
        int totalCourses = 0;

        String query = "SELECT COUNT(*) FROM Course WHERE Subjectid IN (SELECT Subjectid FROM Subjects WHERE Name = ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, subjectName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalCourses = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalCourses;
    } 

    public List<Course> getCoursesByPriceRange(double minPrice, double maxPrice, int pageIndex, int pageSize) {
    List<Course> courses = new ArrayList<>();
    int offset = (pageIndex - 1) * pageSize;

    // Câu lệnh SQL lọc theo phạm vi giá
    String query = "SELECT Courseid, Name, Subjectid, Price, Authorid, Description, Img, Createdtime, Status, Tag "
            + "FROM Course WHERE Price BETWEEN ? AND ? "
            + "ORDER BY Createdtime DESC "
            + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setDouble(1, minPrice);
        ps.setDouble(2, maxPrice);
        ps.setInt(3, offset);
        ps.setInt(4, pageSize);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int courseId = rs.getInt("Courseid");
                String name = rs.getString("Name");
                int subjectId = rs.getInt("Subjectid");
                double price = rs.getDouble("Price");
                int authorId = rs.getInt("Authorid");
                String description = rs.getString("Description");
                String img = rs.getString("Img");
                Date createdTime = rs.getDate("Createdtime");
                int status = rs.getInt("Status");
                String tag = rs.getString("Tag");

                Course course = new Course(courseId, name, subjectId, price, authorId, description, img, createdTime, status, tag);
                courses.add(course);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return courses;
}

public int getTotalCoursesByPriceRange(double minPrice, double maxPrice) {
    int total = 0;
    String query = "SELECT COUNT(*) FROM Course WHERE Price BETWEEN ? AND ?";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setDouble(1, minPrice);
        ps.setDouble(2, maxPrice);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return total;
}
 public static void main(String[] args) {
        // Tạo đối tượng DAOListCourses để gọi các phương thức
        DAOListCourses dao = new DAOListCourses();

        // Các tham số để kiểm tra
        double minPrice = 0;
        double maxPrice = 100;
        int pageIndex = 1;
        int pageSize = 5;

        // Kiểm tra hàm getCoursesByPriceRange
        List<Course> courses = dao.getCoursesByPriceRange(minPrice, maxPrice, pageIndex, pageSize);

        // In ra các khóa học đã lấy được
        System.out.println("Courses between $" + minPrice + " and $" + maxPrice + ":");
        for (Course course : courses) {
            System.out.println("Course ID: " + course.getCourseId() + ", Name: " + course.getName() + ", Price: $" + course.getPrice());
        }

        // Kiểm tra hàm getTotalCoursesByPriceRange
        int totalCourses = dao.getTotalCoursesByPriceRange(minPrice, maxPrice);
        System.out.println("Total number of courses in this price range: " + totalCourses);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

 
}
