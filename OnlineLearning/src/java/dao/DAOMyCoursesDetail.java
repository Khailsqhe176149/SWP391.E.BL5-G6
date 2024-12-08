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
public class DAOMyCoursesDetail extends DBContext {

    public DAOMyCoursesDetail() {
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

    public Course getCourseInfo(int userID, int courseID) {
        Course course = null;

        // SQL query để lấy thông tin khóa học và trạng thái học
        String sql = "SELECT "
                + "c.Name, "
                + "c.CourseID, "
                + "c.Name, "
                + "c.Description, "
                + "c.Img, "
                + "cr.Status, "
                + "cr.RegistrationDate "
                + "FROM Users u "
                + "JOIN CourseRegistrater cr ON u.userID = cr.UserID "
                + "JOIN Course c ON cr.CourseID = c.CourseID "
                + "WHERE u.userID = ? AND c.CourseID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set các giá trị cho PreparedStatement
            stmt.setInt(1, userID);
            stmt.setInt(2, courseID);

            // Thực thi truy vấn và lấy kết quả
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Lấy dữ liệu từ ResultSet và gán vào đối tượng Course
                    course = new Course();
                    course.setName(rs.getString("Name"));
                    course.setCourseId(rs.getInt("CourseID"));
                    course.setDescription(rs.getString("Description"));
                    course.setImg(rs.getString("Img"));
                    course.setStatus(rs.getInt("Status"));
                    course.setRegistrationDate(rs.getDate("RegistrationDate"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
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

    private Lesson mapResultSetToLesson(ResultSet rs) throws SQLException {
        int lessonId = rs.getInt("Lessonid");
        Date date = rs.getDate("Date");
        String name = rs.getString("Name");
        String content = rs.getString("Content");
        String description = rs.getString("Description");

        return new Lesson(lessonId, date, name, content, description);
    }

    public int countQuizzesByCourseId(int courseId) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM CourseQuiz CQ "
                + "JOIN Quiz Q ON CQ.Quizid = Q.Quizid "
                + "WHERE CQ.Courseid = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1); // Lấy kết quả từ cột đếm
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public int countLessonsByCourseId(int courseId) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM CourseLesson CL "
                + "JOIN Lesson L ON CL.Lessonid = L.Lessonid "
                + "WHERE CL.Courseid = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1); // Lấy kết quả từ cột đếm
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

     public static void main(String[] args) {
        // Tạo đối tượng DAOMyCoursesDetail để kết nối với DB
        DAOMyCoursesDetail dao = new DAOMyCoursesDetail();

        // Giả sử chúng ta kiểm tra cho courseID = 101
        int courseId = 2;

        // Kiểm tra số lượng quizzes
        int quizCount = dao.countQuizzesByCourseId(courseId);
        System.out.println("Total quizzes for course ID " + courseId + ": " + quizCount);

        // Kiểm tra số lượng lessons
        int lessonCount = dao.countLessonsByCourseId(courseId);
        System.out.println("Total lessons for course ID " + courseId + ": " + lessonCount);
    }
}
