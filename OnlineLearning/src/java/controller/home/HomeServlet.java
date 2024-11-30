
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
import model.Slider;

/**
 *
 * @author Admin
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy số trang từ query parameter, mặc định là trang 1
        int pageNumber = 1;
        String page = request.getParameter("page");  // Lấy trang từ tham số "page" trong URL
        if (page != null && !page.isEmpty()) {
            try {
                pageNumber = Integer.parseInt(page);  // Chuyển đổi trang sang kiểu int
            } catch (NumberFormatException e) {
                pageNumber = 1;  // Nếu trang không hợp lệ, sử dụng trang mặc định
            }
        }

        // Tạo đối tượng DAO để lấy danh sách khóa học với phân trang
        DAOHome DAOHome = new DAOHome();
        List<CountCourse> courses = DAOHome.getCoursesWithPagination(pageNumber);

        // Truyền danh sách khóa học và số trang sang JSP
        request.setAttribute("courses", courses);
        request.setAttribute("currentPage", pageNumber);  // Truyền trang hiện tại
        // Khởi tạo DAO
        DAOHome daoHome = new DAOHome();
        // Lấy danh sách slider
        List<Slider> sliders = daoHome.getActiveSliders();
        // Gửi dữ liệu đến JSP
        request.setAttribute("sliders", sliders);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

}
