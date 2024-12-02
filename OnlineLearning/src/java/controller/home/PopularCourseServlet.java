/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import dao.DAOHome;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.CountCourse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "PopularCourseServlet", urlPatterns = {"/PopularCourseServlet"})
public class PopularCourseServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pageNumber = 1;
        String page = request.getParameter("page");
        if (page != null && !page.isEmpty()) {
            try {
                pageNumber = Integer.parseInt(page);
            } catch (NumberFormatException e) {
                pageNumber = 1;
            }
        }

        DAOHome DAOHome = new DAOHome();
        List<CountCourse> courses = DAOHome.getCoursesWithPagination(pageNumber);

        request.setAttribute("courses", courses);
        request.setAttribute("currentPage", pageNumber);  // Truyền trang hiện tại
        request.getRequestDispatcher("/partials/popularCourses.jsp").forward(request, response);
    }

}
