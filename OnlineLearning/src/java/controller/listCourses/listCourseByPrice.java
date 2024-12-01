package controller.listCourses;

import dao.DAOListCourses;
import model.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "listCourseByPrice", urlPatterns = {"/listCourseByPrice"})
public class listCourseByPrice extends HttpServlet {
private DAOListCourses dao = new DAOListCourses();

  
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageIndex = 1;
        if (request.getParameter("pageIndex") != null) {
            try {
                pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
            } catch (NumberFormatException e) {
                pageIndex = 1;  // Mặc định là trang 1 nếu không thể parse
            }
        }

        int pageSize = 6; // Kích thước mỗi trang (6 khóa học mỗi trang)

        // Lấy tham số priceRange từ query string
        String priceRange = request.getParameter("priceRange");

        double minPrice = 0;
        double maxPrice = Double.MAX_VALUE;  // Mặc định là không giới hạn giá

        if (priceRange != null) {
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
                maxPrice = 100000;
            }
        }

        // Lấy các khóa học theo phạm vi giá
        List<Course> courses = dao.getCoursesByPriceRange(minPrice, maxPrice, pageIndex, pageSize);
        int totalCourses = dao.getTotalCoursesByPriceRange(minPrice, maxPrice);  // Tổng số khóa học trong phạm vi giá

        // Tính toán lại tổng số trang
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);
         List<String> subjects = dao.getAllSubjects();
        request.setAttribute("subjects", subjects); 
        // Gửi các thông tin cần thiết đến JSP
        request.setAttribute("courses", courses);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("priceRange", priceRange); // Truyền priceRange để làm highlight trong UI

        // Chuyển hướng đến JSP để hiển thị kết quả
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }
}
