/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.viewCoursesDetail;

import dao.DAOCoursesDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Course;
import model.Lesson;
import model.Quiz;

/**
 *
 * @author Admin
 */
@WebServlet(name="CourseDetailServlet", urlPatterns={"/CourseDetailServlet"})
public class CourseDetailServlet extends HttpServlet {
   


    private DAOCoursesDetail dao = new DAOCoursesDetail();

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Lấy thông tin userId từ session
    HttpSession session = request.getSession(false);
    Integer userId = (Integer) session.getAttribute("userID");

    // Lấy courseId từ request
    String courseIdStr = request.getParameter("courseId");
    int courseId = Integer.parseInt(courseIdStr);

    // Lấy thông tin khóa học từ cơ sở dữ liệu
    DAOCoursesDetail dao = new DAOCoursesDetail();
    Course course = dao.getCourseById(courseId);
    List<Lesson> lessons = dao.getLessonsByCourseId(courseId);
    List<Quiz> quizzes = dao.getQuizzesByCourseId(courseId);

    // Truyền thông tin khóa học, bài học, quiz sang JSP
    request.setAttribute("course", course);
    request.setAttribute("lessons", lessons);
    request.setAttribute("quizzes", quizzes);

    // Kiểm tra xem người dùng đã đăng ký khóa học chưa
    boolean isRegistered = false;
    if (userId != null) {
        isRegistered = dao.isCourseRegistered(userId, courseId);
    }

    // Truyền thông tin đăng ký vào request (nếu có userId)
    request.setAttribute("isRegistered", isRegistered);

    // Chuyển hướng sang trang chi tiết khóa học (JSP)
    request.getRequestDispatcher("/course-detail.jsp").forward(request, response);
}



}


