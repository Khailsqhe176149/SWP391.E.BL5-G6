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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Subjects;
import model.Users;

/**
 *
 * @author Admin
 */
public class DAOCourses extends DBContext {

    // Constructor kế thừa từ DBContext
    public DAOCourses() {
        super(); // Gọi constructor của lớp DBContext để khởi tạo kết nối
    }

    public List<Course> getAllCoursesForCreator(int creatorId) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.CourseId, c.Name, c.Price, s.Name AS SubjectName, c.Status ,c.Img "
                + "FROM Course c "
                + "INNER JOIN Subjects s ON c.SubjectId = s.SubjectId "
                + "WHERE c.AuthorId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, creatorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("CourseId"));
                course.setName(rs.getString("Name"));
                course.setPrice(rs.getDouble("Price"));
                course.setSubjectName(rs.getString("SubjectName"));
                course.setStatus(rs.getInt("Status"));
                course.setImg(rs.getString("Img"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public boolean hasRegistrations(int courseId) {
        String query = "SELECT COUNT(*) FROM CourseRegistrater WHERE CourseID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Có đăng ký
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Không có đăng ký
    }

    public boolean deleteCourse(int courseId) {
        String query = "DELETE FROM Course WHERE CourseID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, courseId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Xóa thành công nếu có dòng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Xóa thất bại
    }

    public boolean deleteCourseLessons(int courseId) {
        String query = "DELETE FROM CourseLesson WHERE CourseId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, courseId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Xóa thành công nếu có bản ghi bị xóa
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Xóa thất bại
    }

    public boolean deleteCourseQuiz(int courseId) {
        String query = "DELETE FROM CourseQuiz WHERE CourseId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, courseId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Xóa thành công nếu có bản ghi bị xóa
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Xóa thất bại
    }

    public Course getCourseById(int courseId) {
        String query = "SELECT * FROM Course WHERE CourseId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("CourseId"));
                course.setName(rs.getString("Name"));
                course.setDescription(rs.getString("Description"));
                course.setPrice(rs.getDouble("Price"));
                course.setSubjectid(rs.getInt("SubjectId"));
                course.setStatus(rs.getInt("Status"));
                course.setTag(rs.getString("Tag"));
                return course;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Subjects getSubjectByName(String name) {
        // Sử dụng PreparedStatement mà không cần dấu ' trong câu SQL
        String sql = "SELECT * FROM subjects WHERE Name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Đặt giá trị cho tham số
            statement.setString(1, name);

            try (ResultSet rs = statement.executeQuery()) {
                // Nếu có kết quả trả về, tạo đối tượng Subjects
                if (rs.next()) {
                    Subjects subject = new Subjects();
                    subject.setSubjectid(rs.getInt("subjectid"));
                    subject.setName(rs.getString("Name"));
                    subject.setStatus(rs.getInt("Status"));
                    return subject; // Trả về subject tìm được
                }
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        }

        // Trả về null nếu không tìm thấy hoặc có lỗi
        return null;
    }

    public boolean updateCourse(Course course) {
        String query = "UPDATE Course SET Name = ?, Description = ?, Price = ?, SubjectId = ?, Status = ?, Tag = ? WHERE CourseId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, course.getName());
            ps.setString(2, course.getDescription());
            ps.setDouble(3, course.getPrice());
            ps.setInt(4, course.getSubjectid());
            ps.setInt(5, course.getStatus());
            ps.setString(6, course.getTag());
            ps.setInt(7, course.getCourseId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

    public List<Subjects> getAllSubjects() {
        List<Subjects> subjectsList = new ArrayList<>();
        String query = "SELECT subjectId, name FROM subjects"; // Truy vấn lấy tất cả môn học

        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            // Duyệt qua tất cả các dòng dữ liệu trong ResultSet
            while (rs.next()) {
                int subjectId = rs.getInt("subjectId");
                String name = rs.getString("name");

                // Tạo đối tượng Subjects và thêm vào danh sách
                Subjects subject = new Subjects(subjectId, name);
                subjectsList.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjectsList; // Trả về danh sách môn học
    }

    public boolean updateCourseImage(Course course) {
        String sql = "UPDATE Course SET Img = ? WHERE Courseid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, course.getImg());
            stmt.setInt(2, course.getCourseId());  // Dùng đúng ID khóa học
            int result = stmt.executeUpdate();
            return result > 0;  // Kiểm tra kết quả
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addCourse(String name, int subjectid, double price, int authorid, String description, String img, Date createdTime, int status, String tag) {
        String sql = "INSERT INTO Course (Name, subjectid, Price, Authorid, Description, Img, Createdtime, Status, Tag) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, subjectid);
            stmt.setDouble(3, price);
            stmt.setInt(4, authorid);
            stmt.setString(5, description);
            stmt.setString(6, img);
            stmt.setTimestamp(7, new java.sql.Timestamp(createdTime.getTime()));
            stmt.setInt(8, status);
            stmt.setString(9, tag);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu thêm thành công
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Trả về false nếu có lỗi
        }
    }

    public static void main(String[] args) {
        // Khởi tạo đối tượng DAO để gọi phương thức
        DAOCourses courseDAO = new DAOCourses();

        // Tạo đối tượng Course với ID khóa học và đường dẫn ảnh mới
        int courseId = 1;  // ID khóa học cần cập nhật
        String newImgPath = "img/new_image.jpg";  // Đường dẫn ảnh mới

        // Tạo đối tượng Course với thông tin cần thiết
        Course course = new Course(courseId, null, 0, 0, null, newImgPath, 0);  // Chỉ quan tâm đến thông tin ảnh

        // Gọi phương thức updateCourseImage để cập nhật ảnh
        boolean updated = courseDAO.updateCourseImage(course);

        // Kiểm tra kết quả cập nhật và in ra thông báo
        if (updated) {
            System.out.println("Image updated successfully for course ID: " + courseId);
        } else {
            System.out.println("Failed to update image for course ID: " + courseId);
        }
    }

}
