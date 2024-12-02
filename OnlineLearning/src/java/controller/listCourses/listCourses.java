package controller.listCourses;

import dao.DAOListCourses;
import model.Course;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "listCourses", urlPatterns = {"/listCourses"})
public class listCourses extends HttpServlet {

    private DAOListCourses dao = new DAOListCourses();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageIndex = 1;

        if (request.getParameter("pageIndex") != null) {
            try {
                pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
            } catch (NumberFormatException e) {
                pageIndex = 1;
            }
        }

        int pageSize = 6;

        String subjectName = request.getParameter("subject");
        String priceRange = request.getParameter("priceRange");

        List<Course> courses;
        int totalCourses;

        if (subjectName != null && !subjectName.isEmpty()) {

            courses = dao.getCoursesBySubject(subjectName, pageIndex, pageSize);
            totalCourses = dao.getTotalCoursesBySubject(subjectName);
        } else if (priceRange != null && !priceRange.isEmpty()) {

            double minPrice = 0;
            double maxPrice = Double.MAX_VALUE;

            if (priceRange.equals("under100")) {
                minPrice = 0;
                maxPrice = 100;
            } else if (priceRange.equals("100to500")) {
                minPrice = 100;
                maxPrice = 500;
            } else if (priceRange.equals("above500")) {
                minPrice = 500;
                maxPrice = Double.MAX_VALUE;
            } else if (priceRange.equals("free")) {
                minPrice = 0;
                maxPrice = 0;
            } else if (priceRange.equals("paid")) {
                minPrice = 1;
                maxPrice = Double.MAX_VALUE;
            }

            courses = dao.getCoursesByPriceRange(minPrice, maxPrice, pageIndex, pageSize);
            totalCourses = dao.getTotalCoursesByPriceRange(minPrice, maxPrice);
        } else {

            courses = dao.getCoursesWithPagination(pageIndex, pageSize);
            totalCourses = dao.getTotalCourses();
        }

        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);

        request.setAttribute("courses", courses);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPages", totalPages);

        List<String> subjects = dao.getAllSubjects();
        request.setAttribute("subjects", subjects);

        request.getRequestDispatcher("/listCourses.jsp").forward(request, response);
    }
}
