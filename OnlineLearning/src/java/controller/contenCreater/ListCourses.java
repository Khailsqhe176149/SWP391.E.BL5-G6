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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Course;
import model.Users;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ListCourses", urlPatterns = {"/ManagerCourses"})
public class ListCourses extends HttpServlet {

    private DAOCourses courseDAO = new DAOCourses();

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
        Users user = courseDAO.getUserByID(userId);

        List<Course> courses = courseDAO.getAllCoursesForCreator(creatorId); // Thay creatorId bằng ID giáo viên
        request.setAttribute("user", user);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/list-courses.jsp").forward(request, response);
    }

}
