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
        
        // Lấy tên môn học từ query string (nếu có)
        String subjectName = request.getParameter("subject");
        
        // Nếu có môn học, lấy khóa học theo môn học đó
        List<Course> courses;
        int totalCourses;
        if (subjectName != null && !subjectName.isEmpty()) {
            courses = dao.getCoursesBySubject(subjectName, pageIndex, pageSize);
            totalCourses = dao.getTotalCoursesBySubject(subjectName);  // Lấy tổng khóa học theo môn học
        } else {
            courses = dao.getCoursesWithPagination(pageIndex, pageSize);
            totalCourses = dao.getTotalCourses();  // Tổng khóa học không phân theo môn học
        }

        // Tính toán lại tổng số trang
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);

        // Gửi các thông tin cần thiết đến JSP
        request.setAttribute("courses", courses);  
        request.setAttribute("pageIndex", pageIndex);  
        request.setAttribute("totalPages", totalPages); 
        
        List<String> subjects = dao.getAllSubjects();
        request.setAttribute("subjects", subjects);  

        request.getRequestDispatcher("/listCourses.jsp").forward(request, response);
    }
}