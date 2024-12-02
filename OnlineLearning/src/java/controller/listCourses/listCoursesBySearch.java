/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.listCourses;

import dao.DAOListCourses;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Course;

/**
 *
 * @author Admin
 */
@WebServlet(name = "listCoursesBySearch", urlPatterns = {"/listCoursesBySearch"})
public class listCoursesBySearch extends HttpServlet {

    private DAOListCourses dao = new DAOListCourses();
    private static final int COURSES_PER_PAGE = 6;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchQuery = request.getParameter("searchQuery");

        int pageIndex = 1;
        if (request.getParameter("pageIndex") != null) {
            try {
                pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
            } catch (NumberFormatException e) {
                pageIndex = 1;
            }
        }

        List<Course> courses;
        int totalCourses;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            courses = dao.searchCourses(searchQuery, pageIndex, COURSES_PER_PAGE);
            totalCourses = dao.getTotalCoursesBySearch(searchQuery);
        } else {
            courses = dao.getCoursesWithPagination(pageIndex, COURSES_PER_PAGE);
            totalCourses = dao.getTotalCourses();
        }

        int totalPages = (int) Math.ceil((double) totalCourses / COURSES_PER_PAGE);

        request.setAttribute("courses", courses);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery); // Truyền giá trị tìm kiếm vào JSP

        List<String> subjects = dao.getAllSubjects();
        request.setAttribute("subjects", subjects);

        request.getRequestDispatcher("/listCourses.jsp").forward(request, response);
    }

}
