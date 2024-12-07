/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package customer;

import dao.DAOListMyCourses;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Course;
import model.Users;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ListMyCourses", urlPatterns = {"/ListMyCourses"})
public class ListMyCourses extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy userID từ session
        HttpSession session = request.getSession(false);  // false để không tạo session mới nếu không tồn tại
        if (session == null || session.getAttribute("userID") == null) {
            // Nếu không có userID trong session, chuyển hướng đến trang login
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy userID từ session
        int userID = (int) session.getAttribute("userID");

        // Khởi tạo đối tượng DAO và gọi hàm getUserByID để lấy thông tin người dùng
        DAOListMyCourses dao = new DAOListMyCourses();
        Users user = dao.getUserByID(userID);  // Lấy thông tin người dùng

        // Nếu không tìm thấy người dùng, chuyển hướng về trang login
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy các tham số phân trang từ request
        int pageIndex = 1;  // Trang mặc định là 1
        if (request.getParameter("pageIndex") != null) {
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        }

        int pageSize = 12;  // Số lượng bản ghi mỗi trang
        int offset = (pageIndex - 1) * pageSize;

        // Khởi tạo đối tượng DAO để truy vấn danh sách khóa học với phân trang
        List<Course> courses = dao.getCoursesByUserId(userID, offset, pageSize);  // Lấy khóa học của userID với phân trang

        // Lấy tổng số khóa học của người dùng để tính tổng số trang
        int totalCourses = dao.getTotalCoursesByUserId(userID);
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);

        // Đưa đối tượng user, danh sách khóa học và thông tin phân trang vào request attribute
        request.setAttribute("user", user);  // Đưa thông tin người dùng vào request
        request.setAttribute("courses", courses);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPages", totalPages);

        // Chuyển hướng đến trang JSP để hiển thị thông tin người dùng và khóa học
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-mycourses.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tham số status từ POST request
        int status = Integer.parseInt(request.getParameter("status"));

        HttpSession session = request.getSession(false);  // false để không tạo session mới nếu không tồn tại
        if (session == null || session.getAttribute("userID") == null) {
            // Nếu không có userID trong session, chuyển hướng đến trang login
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy userID từ session
        int userID = (int) session.getAttribute("userID");

        // Khởi tạo đối tượng DAO và gọi hàm getUserByID để lấy thông tin người dùng
        DAOListMyCourses dao = new DAOListMyCourses();
        Users user = dao.getUserByID(userID);  // Lấy thông tin người dùng

        // Nếu không tìm thấy người dùng, chuyển hướng về trang login
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy tham số pageIndex từ URL hoặc mặc định là 1
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex") != null ? request.getParameter("pageIndex") : "1");

        // Phân trang
        int pageSize = 12;
        int offset = (pageIndex - 1) * pageSize;

        // Khởi tạo đối tượng DAO để truy vấn danh sách khóa học với status và phân trang
        
        List<Course> courses = dao.getCoursesByUserIdAndStatus(userID, status, offset, pageSize);

        // Lấy tổng số khóa học của người dùng theo status để tính tổng số trang
        int totalCourses = dao.getTotalCoursesByUserIdAndStatus(userID, status);
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);

        // Đưa danh sách khóa học và thông tin phân trang vào request attribute
        request.setAttribute("courses", courses);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("status", status);

        // Chuyển hướng đến trang JSP để hiển thị khóa học và phân trang
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-mycourses.jsp");
        dispatcher.forward(request, response);
    }

}
