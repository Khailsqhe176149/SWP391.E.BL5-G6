package controller.listCourses;

import dao.DAOListCourses;
import java.io.IOException;
import java.util.List;
import model.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "listCoursesBySort", urlPatterns = {"/listCoursesBySort"})
public class listCoursesBySort extends HttpServlet {

    private DAOListCourses dao = new DAOListCourses();
    private static final int COURSES_PER_PAGE = 6;  // Number of courses per page

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sortOption = request.getParameter("sort");

        int pageIndex = 1;

        if (request.getParameter("pageIndex") != null) {
            try {
                pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
            } catch (NumberFormatException e) {
                pageIndex = 1;
            }
        }

        List<Course> courses = null;
        int totalCourses = 0;

        if (sortOption != null) {
            switch (sortOption) {
                case "az":
                    courses = dao.getCoursesByNameASC(pageIndex);
                    totalCourses = dao.getTotalCourseCount();
                    break;
                case "latest":
                    courses = dao.getCoursesByCreatedTimeDesc(pageIndex);
                    totalCourses = dao.getTotalCourseCount();
                    break;
                case "oldest":
                    courses = dao.getCoursesByCreatedTimeAsc(pageIndex);
                    totalCourses = dao.getTotalCourseCount();
                    break;
                case "mostRegistrations":
                    courses = dao.getCoursesByEnrollmentDesc(pageIndex);
                    totalCourses = dao.getTotalCourseCount();
                    break;
                case "mostLessons":
                    courses = dao.getCoursesByLessonCountDesc(pageIndex);
                    totalCourses = dao.getTotalCourseCount();
                    break;
                default:
                    courses = dao.getCoursesByNameASC(pageIndex);
                    totalCourses = dao.getTotalCourseCount();
                    break;
            }
        } else {

            courses = dao.getCoursesByNameASC(pageIndex);
            totalCourses = dao.getTotalCourseCount();
        }

        int totalPages = (int) Math.ceil((double) totalCourses / COURSES_PER_PAGE);

        request.setAttribute("courses", courses);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("sortOption", sortOption);

        List<String> subjects = dao.getAllSubjects();
        request.setAttribute("subjects", subjects);

        request.getRequestDispatcher("/listCourses.jsp").forward(request, response);
    }
}
