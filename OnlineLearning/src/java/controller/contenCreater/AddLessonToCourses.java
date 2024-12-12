/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.contenCreater;

import dao.DAOCourses;
import dao.DAOLesson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import model.Course;
import model.Lesson;
import model.Users;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddLessonToCourses", urlPatterns = {"/AddLessonToCourses"})
public class AddLessonToCourses extends HttpServlet {

    private DAOLesson dao = new DAOLesson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin người dùng từ session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userID");

        Integer creatorId = userId;// Nếu không có userId trong session, chuyển hướng người dùng tới trang login
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        Users user = dao.getUserByID(userId);

        List<Course> courses = dao.getAllCoursesForCreator(creatorId); // Thay creatorId bằng ID giáo viên

        // Gắn danh sách bài học vào từng khóa học
        for (Course course : courses) {
            List<Lesson> lessons = dao.getLessonsByCourseId(course.getCourseId());
            course.setLessons(lessons); // Đảm bảo lớp Course có thuộc tính "lessons"
        }
        request.setAttribute("user", user);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/add-lesson-to-course.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        String[] lessonIds = req.getParameterValues("lessonIds");

        Date date = new Date();

        if (lessonIds != null) {
            for (String lessonId : lessonIds) {
                dao.addLessonToCourse(courseId, Integer.parseInt(lessonId), date);
            }
        }

        resp.sendRedirect("AddLessonToCourses"); // Quay lại danh sách khóa học
    }

}
