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
import java.util.ArrayList;
import java.util.List;
import model.Course;

/**
 *
 * @author Admin
 */
@WebServlet(name="listCoursesBySort", urlPatterns={"/listCoursesBySort"})
public class listCoursesBySort extends HttpServlet {
   
      // DAO instance to interact with the database
    private DAOListCourses dao = new DAOListCourses();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the selected sort option from the form
        String sortOption = request.getParameter("sort");
        int pageIndex = 1; // You can get the pageIndex dynamically if needed
        
        // List to hold the courses
        List<Course> courses = new ArrayList<>();

        // Sort courses based on the selected sort option
        if (sortOption != null) {
            switch (sortOption) {
                case "az":
                    courses = dao.getCoursesByNameASC(pageIndex);
                    break;
                case "latest":
                    courses = dao.getCoursesByCreatedTimeDesc(pageIndex);
                    break;
                case "oldest":
                    courses = dao.getCoursesByCreatedTimeAsc(pageIndex);
                    break;
                case "mostRegistrations":
                    courses = dao.getCoursesByEnrollmentDesc(pageIndex);
                    break;
                case "mostLessons":
                    courses = dao.getCoursesByLessonCountDesc(pageIndex);
                    break;
                default:
                    // Default case if no sort option is selected (can be handled as per your requirements)
                    courses = dao.getCoursesByNameASC(pageIndex); // or any default sorting
                    break;
            }
        }

        // Set the sorted courses as a request attribute
        request.setAttribute("courses", courses);
          List<String> subjects = dao.getAllSubjects();
        request.setAttribute("subjects", subjects); 

        // Forward the request to the JSP to display the results
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }

}
