/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.contenCreater;

import dao.DAOLesson;
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
import model.Users;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteLessonFromCourse", urlPatterns = {"/deleteLessonFromCourse"})
public class DeleteLessonFromCourse extends HttpServlet {

    private DAOLesson dao = new DAOLesson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        int lessonId = Integer.parseInt(req.getParameter("lessonId"));
        // Lấy thông tin người dùng từ session
       
        boolean isDeleted = dao.removeLessonFromCourse(courseId, lessonId);
        resp.sendRedirect("AddLessonToCourses");
     
    }
}
