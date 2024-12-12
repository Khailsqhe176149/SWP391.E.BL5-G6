/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.contenCreater;

import dao.DAOCourses;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name="DeleteCourse", urlPatterns={"/DeleteCourse"})
public class DeleteCourse extends HttpServlet {

    private DAOCourses courseDAO = new DAOCourses();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseIdStr = request.getParameter("courseId");

        try {
            int courseId = Integer.parseInt(courseIdStr);

            // Kiểm tra ràng buộc nếu khóa học có đăng ký
            boolean hasRegistrations = courseDAO.hasRegistrations(courseId);
            if (hasRegistrations) {
                // Trả về lỗi nếu có đăng ký
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Cannot delete course with existing registrations.");
                return;
            }
            
            
              boolean quizDeleted = courseDAO.deleteCourseQuiz(courseId);
              boolean lessonsDeleted = courseDAO.deleteCourseLessons(courseId);
               if (lessonsDeleted && quizDeleted) {
                
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Has lesson or not delete");
                return;
            }
            // Thực hiện xóa khóa học
            boolean isDeleted = courseDAO.deleteCourse(courseId);
            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Course deleted successfully.");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Failed to delete the course.");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid course ID.");
        } 
    }
}