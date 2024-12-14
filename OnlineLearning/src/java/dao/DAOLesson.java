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
import model.Lesson;
import model.Users;

/**
 *
 * @author Admin
 */
public class DAOLesson extends DBContext {

    // Constructor kế thừa từ DBContext
    public DAOLesson() {
        super(); // Gọi constructor của lớp DBContext để khởi tạo kết nối
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Course";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("Courseid"));
                course.setName(rs.getString("Name"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
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

    public boolean addLessonToCourse(int courseId, int lessonId, Date date) {
        String query = "INSERT INTO CourseLesson (Courseid, Lessonid, Date) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, courseId);
            ps.setInt(2, lessonId);
            ps.setTimestamp(3, new java.sql.Timestamp(date.getTime()));
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Thành công nếu có bản ghi được thêm
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Thất bại
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

    public List<Lesson> getLessonsByCourseId(int courseId) {
        List<Lesson> lessons = new ArrayList<>();
        String query = "SELECT l.Lessonid, l.Name, l.Description FROM Lesson l "
                + "INNER JOIN CourseLesson cl ON l.Lessonid = cl.Lessonid "
                + "WHERE cl.Courseid = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setLessonid(rs.getInt("Lessonid"));
                lesson.setName(rs.getString("Name"));
                lesson.setDescription(rs.getString("Description"));
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

   public boolean updateLesson(Lesson lesson) {
    String query = "UPDATE Lesson SET Name = ?, Date = ?, Content = ?, Description = ? WHERE Lessonid = ?";
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setString(1, lesson.getName());
        ps.setDate(2, new java.sql.Date(lesson.getDate().getTime()));
        ps.setString(3, lesson.getContent());
        ps.setString(4, lesson.getDescription());
        ps.setInt(5, lesson.getLessonid());

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0; // Trả về true nếu có bản ghi bị ảnh hưởng (cập nhật thành công)
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
   
      public boolean addLesson(Lesson lesson) {
        String query = "INSERT INTO Lesson (Name, Content, Description, Date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, lesson.getName());
            ps.setString(2, lesson.getContent());
            ps.setString(3, lesson.getDescription());
            ps.setDate(4, new java.sql.Date(lesson.getDate().getTime())); // Chuyển đổi Date thành java.sql.Date
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi
    }


    public boolean deleteCourseLesson(int lessonId) {
        String query = "DELETE FROM CourseLesson \n"
                + "WHERE Lessonid = ?;";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, lessonId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi
    }

    public boolean deleteLessonVideo(int lessonId) {
        String query = "DELETE FROM LessonVideos \n"
                + "WHERE Lessonid = ?; ";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, lessonId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi
    }

    public boolean deleteLessonReading(int lessonId) {
        String query = "DELETE FROM LessonReading \n"
                + "WHERE LessonId = ?;";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, lessonId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi
    }

    public boolean deleteLesson(int lessonId) {
        String query = "DELETE FROM Lesson WHERE Lessonid = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, lessonId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi
    }

    public Lesson getLessonById(int lessonId) {
    String query = "SELECT * FROM Lesson WHERE Lessonid = ?";
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, lessonId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // Tạo đối tượng Lesson từ dữ liệu trong ResultSet
            Lesson lesson = new Lesson();
            lesson.setLessonid(rs.getInt("Lessonid"));
            lesson.setName(rs.getString("Name"));
            lesson.setDate(rs.getDate("Date")); // Lấy thông tin ngày
            lesson.setContent(rs.getString("Content")); // Lấy thông tin nội dung
            lesson.setDescription(rs.getString("Description")); // Lấy thông tin mô tả

            return lesson;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; 
}


    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<>();
        String query = "SELECT * FROM Lesson"; // SQL để lấy tất cả bài học
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setLessonid(rs.getInt("Lessonid"));
                lesson.setName(rs.getString("Name"));
                lesson.setContent(rs.getString("Content"));
                lesson.setDescription(rs.getString("Description"));
                lesson.setDate(rs.getDate("Date"));
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

 public boolean isLessonAlreadyAdded(int courseId, int lessonId) {
    String query = "SELECT COUNT(*) FROM course_lessons WHERE course_id = ? AND lesson_id = ?";
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, courseId);
        ps.setInt(2, lessonId);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1) > 0;  // Nếu số lượng lớn hơn 0, nghĩa là bài học đã tồn tại
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
public boolean removeLessonFromCourse(int courseId, int lessonId) {
    boolean result = false;
    String query = "DELETE FROM CourseLesson WHERE Courseid = ? AND  Lessonid = ?";
    
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        
        ps.setInt(1, courseId);
        ps.setInt(2, lessonId);
        
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            result = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return result;
}


}
