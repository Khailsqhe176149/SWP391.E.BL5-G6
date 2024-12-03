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
       
        String courseIdStr = request.getParameter("courseId");
        int courseId = Integer.parseInt(courseIdStr);

       
        Course course = dao.getCourseById(courseId);

        
        List<Lesson> lessons = dao.getLessonsByCourseId(courseId);

     
        List<Quiz> quizzes = dao.getQuizzesByCourseId(courseId);

        
        request.setAttribute("course", course);
        request.setAttribute("lessons", lessons);
        request.setAttribute("quizzes", quizzes);

        request.getRequestDispatcher("/course-detail.jsp").forward(request, response);
    }
}


