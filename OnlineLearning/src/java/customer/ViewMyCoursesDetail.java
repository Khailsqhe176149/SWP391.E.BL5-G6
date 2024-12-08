/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package customer;

import dao.DAOCoursesDetail;
import dao.DAOMyCoursesDetail;
import jakarta.servlet.RequestDispatcher;
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
@WebServlet(name = "ViewMyCoursesDetail", urlPatterns = {"/ViewMyCoursesDetail"})
public class ViewMyCoursesDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Bước 1: Lấy userID từ session
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        if (userID == null) {
            // Nếu không có userID trong session, chuyển hướng đến trang đăng nhập
            response.sendRedirect("login.jsp");
            return;
        }

        // Bước 2: Lấy CourseID từ tham số request
        String courseIdParam = request.getParameter("courseId");
     
        int courseId = Integer.parseInt(courseIdParam);

        DAOMyCoursesDetail dao = new DAOMyCoursesDetail();
        Course course = dao.getCourseInfo(userID, courseId);  // giả sử bạn đã có phương thức này trong DBContext
        List<Lesson> lessons = dao.getLessonsByCourseId(courseId);
        List<Quiz> quizzes = dao.getQuizzesByCourseId(courseId);
        int quizCount = dao.countQuizzesByCourseId(courseId);
        int lessonCount = dao.countLessonsByCourseId(courseId);
        
       
        request.setAttribute("quizCount", quizCount);
        request.setAttribute("lessonCount", lessonCount);
        request.setAttribute("lessons", lessons);
        request.setAttribute("quizzes", quizzes);
        request.setAttribute("course", course);
        request.getRequestDispatcher("/view-mycourses-detail.jsp").forward(request, response);
    }
}
