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

        List<Subjects> subjects = courseDAO.getAllSubjects();

        request.setAttribute("subjects", subjects);

        HttpSession session = request.getSession();
        Integer authorid = (Integer) session.getAttribute("userID");
        if (authorid == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        request.setAttribute("authorid", authorid);

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

        boolean success = courseDAO.addCourse(name, subjectid, price, authorid, description, "default_image.jpg", new Date(), 1, tag);

        if (success) {

            response.sendRedirect("ManagerCourses");
        } else {

            request.setAttribute("error", "Failed to add the course");
            request.getRequestDispatcher("list-courses.jsp").forward(request, response);
        }
    }
}
