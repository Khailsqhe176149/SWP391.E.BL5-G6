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
import java.util.Date;
import java.util.List;
import model.Subjects;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddCourses", urlPatterns = {"/AddCourses"})
public class AddCourses extends HttpServlet {

    private DAOCourses courseDAO = new DAOCourses();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy tất cả môn học để hiển thị trong dropdown
        List<Subjects> subjects = courseDAO.getAllSubjects();
        // Truyền dữ liệu vào request
        request.setAttribute("subjects", subjects);

        // Lấy userID từ session
        HttpSession session = request.getSession();
        Integer authorid = (Integer) session.getAttribute("userID");
        if (authorid == null) {
            // Nếu chưa có userID trong session, có thể redirect hoặc thông báo lỗi
            response.sendRedirect("login.jsp");
            return;
        }

        // Truyền authorid vào request để sử dụng trong form
        request.setAttribute("authorid", authorid);

        // Chuyển hướng đến trang edit course
        request.getRequestDispatcher("add-course.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        int subjectid = Integer.parseInt(request.getParameter("subjectid"));
        double price = Double.parseDouble(request.getParameter("price"));
        int authorid = Integer.parseInt(request.getParameter("authorid"));
        String description = request.getParameter("description");
        String tag = request.getParameter("tag");

        // Add course to the database
        boolean success = courseDAO.addCourse(name, subjectid, price, authorid, description, "default_image.jpg", new Date(), 1, tag);

        if (success) {
            // Redirect to a success page or back to course list
            response.sendRedirect("ManagerCourses");
        } else {
            // Show error message
            request.setAttribute("error", "Failed to add the course");
            request.getRequestDispatcher("list-courses.jsp").forward(request, response);
        }
    }
}




