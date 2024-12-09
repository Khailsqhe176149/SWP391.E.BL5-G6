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
import model.Quiz;

/**
 *
 * @author Admin
 */
public class DAOCoursesDetail extends DBContext {

    public DAOCoursesDetail() {
        super();
    }

    public Course getCourseById(int courseId) {
        Course course = null;
        String query = "SELECT Courseid, Name, Subjectid, Price, Authorid, Description, Img, Createdtime, Status, Tag "
                + "FROM Course WHERE Courseid = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    course = mapResultSetToCourse(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
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

    public boolean isCourseRegistered(int userId, int courseId) {
        String query = "SELECT COUNT(*) \n"
                + "FROM CourseRegistrater \n"
                + "WHERE UserID = ? AND CourseID = ? AND Status IS NOT NULL";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Nếu có ít nhất 1 bản ghi, nghĩa là người dùng đã đăng ký khóa học
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Nếu không có bản ghi, nghĩa là người dùng chưa đăng ký
    }

    // Phương thức lấy tất cả bài học của một khóa học
    public List<Lesson> getLessonsByCourseId(int courseId) {
        List<Lesson> lessons = new ArrayList<>();
        // Câu truy vấn SQL mới sử dụng bảng CourseLesson
        String query = "SELECT L.Lessonid, L.Date, L.Name, L.Content, L.Description "
                + "FROM CourseLesson CL "
                + "JOIN Lesson L ON CL.Lessonid = L.Lessonid "
                + "WHERE CL.Courseid = ? "
                + "ORDER BY L.Date";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, courseId);  // Truyền courseId vào câu truy vấn
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Ánh xạ dữ liệu từ ResultSet vào đối tượng Lesson
                    Lesson lesson = mapResultSetToLesson(rs);
                    lessons.add(lesson);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    // Phương thức ánh xạ kết quả từ ResultSet vào đối tượng Lesson
    private Lesson mapResultSetToLesson(ResultSet rs) throws SQLException {
        int lessonId = rs.getInt("Lessonid");
        Date date = rs.getDate("Date");
        String name = rs.getString("Name");
        String content = rs.getString("Content");
        String description = rs.getString("Description");

        return new Lesson(lessonId, date, name, content, description);
    }
    // Lấy tất cả quiz của một khóa học

    public List<Quiz> getQuizzesByCourseId(int courseId) {
        List<Quiz> quizzes = new ArrayList<>();
        String query = "SELECT Q.Quizid, Q.Name, Q.minimumscore, Q.Content, Q.Description, Q.Questionid "
                + "FROM CourseQuiz CQ "
                + "JOIN Quiz Q ON CQ.Quizid = Q.Quizid "
                + "WHERE CQ.Courseid = ? "
                + "ORDER BY Q.Name";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Quiz quiz = new Quiz(
                            rs.getInt("Quizid"),
                            rs.getString("Name"),
                            rs.getFloat("minimumscore"),
                            rs.getString("Content"),
                            rs.getString("Description"),
                            rs.getInt("Questionid")
                    );
                    quizzes.add(quiz);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    public static void main(String[] args) {
        // Tạo đối tượng DAOCoursesDetail
        DAOCoursesDetail dao = new DAOCoursesDetail();

        // Giả sử chúng ta có một courseId cần kiểm tra (ví dụ: courseId = 1)
        int courseId = 1;

        // Gọi phương thức getLessonsByCourseId để lấy tất cả bài học của khóa học
        List<Lesson> lessons = dao.getLessonsByCourseId(courseId);

        // Hiển thị danh sách bài học
        if (lessons != null && !lessons.isEmpty()) {
            for (Lesson lesson : lessons) {
                System.out.println("Lesson ID: " + lesson.getLessonid());
                System.out.println("Lesson Name: " + lesson.getName());
                System.out.println("Lesson Date: " + lesson.getDate());
                System.out.println("Lesson Content: " + lesson.getContent());
                System.out.println("Lesson Description: " + lesson.getDescription());
                System.out.println("-----------------------------");
            }
        } else {
            System.out.println("No lessons found for course ID: " + courseId);
        }
    }
}
