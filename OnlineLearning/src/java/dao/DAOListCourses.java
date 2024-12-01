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

    public List<Course> getCoursesByNameASC(int pageIndex) {
        List<Course> courses = new ArrayList<>();

        // Tính toán OFFSET (bỏ qua các bản ghi của các trang trước)
        int offset = (pageIndex - 1) * 6;

        // Câu lệnh SQL để lấy khóa học sắp xếp theo tên (ASC) với phân trang
        String sql = "SELECT * FROM Course "
                + "ORDER BY Name ASC "
                + "OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Thiết lập giá trị cho OFFSET
            ps.setInt(1, offset);

            // Lặp qua kết quả và thêm vào danh sách
              try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Course course = mapResultSetToCourse(rs);
                courses.add(course);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;

    }
    public List<Course> getCoursesByCreatedTimeAsc(int pageIndex) {
        List<Course> courses = new ArrayList<>();

        int offset = (pageIndex - 1) * 6;
        String sql = "SELECT * FROM Course "
                + "ORDER BY Createdtime ASC "
                + "OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Course course = mapResultSetToCourse(rs);
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    public List<Course> getCoursesByCreatedTimeDesc(int pageIndex) {
        List<Course> courses = new ArrayList<>();

        int offset = (pageIndex - 1) * 6;
        String sql = "SELECT * FROM Course "
                + "ORDER BY Createdtime DESC "
                + "OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Course course = mapResultSetToCourse(rs);
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    public List<Course> getCoursesByEnrollmentDesc(int pageIndex) {
        List<Course> courses = new ArrayList<>();

        int offset = (pageIndex - 1) * 6;
        String sql = "SELECT c.* FROM Course c "
                + "LEFT JOIN Registration r ON c.Courseid = r.Courseid "
                + "GROUP BY c.Courseid "
                + "ORDER BY COUNT(r.UserID) DESC "
                + "OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Course course = mapResultSetToCourse(rs);
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    public List<Course> getCoursesByLessonCountDesc(int pageIndex) {
    List<Course> courses = new ArrayList<>();
    
    int offset = (pageIndex - 1) * 6;
    String sql = "SELECT c.* FROM Course c "
               + "LEFT JOIN Lesson l ON c.Courseid = l.Courseid "
               + "GROUP BY c.Courseid "
               + "ORDER BY COUNT(l.Lessonid) DESC "
               + "OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
    
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, offset);
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Course course = mapResultSetToCourse(rs);
                courses.add(course);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return courses;
}
    private Course mapResultSetToCourse(ResultSet rs) throws SQLException {
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

        return new Course(courseId, name, subjectId, price, authorId, description, img, createdTime, status, tag);
    }
    
    
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
                Course course = mapResultSetToCourse(rs);
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
                Course course = mapResultSetToCourse(rs);
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
                Course course = mapResultSetToCourse(rs);
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

}
