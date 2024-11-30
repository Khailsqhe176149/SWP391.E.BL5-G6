
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
import model.LatestPost;
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
       
        // Khởi tạo DAO
        DAOHome daoHome = new DAOHome();
        // Lấy danh sách slider
        List<Slider> sliders = daoHome.getActiveSliders();
        // Gửi dữ liệu đến JSP
        request.setAttribute("sliders", sliders);
        
          int limit = 5;  // Số lượng bài viết mới nhất cần lấy
          // Lấy danh sách bài viết mới nhất
        List<LatestPost> latestPosts = daoHome.getLatestPosts(limit);

        // Truyền danh sách bài viết vào request để forward đến JSP
        request.setAttribute("latestPosts", latestPosts);
        
        
        request.getRequestDispatcher("/home.jsp").forward(request, response);
        
    }

}
