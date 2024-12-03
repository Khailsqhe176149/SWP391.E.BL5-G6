/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.registerCourse;

import dao.DAORegisterCourse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import model.Course;
import model.Lesson;
import model.Quiz;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RegisterCourseServlet", urlPatterns = {"/RegisterCourseServlet"})
public class RegisterCourseServlet extends HttpServlet {

    private DAORegisterCourse dao = new DAORegisterCourse();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseIdStr = request.getParameter("courseId");
        int courseId = Integer.parseInt(courseIdStr);

        // Lấy thông tin khóa học
        Course course = dao.getCourseById(courseId);

        // Lấy thông tin người dùng từ session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userID");

        if (userId != null) {
            // Lấy số dư ví của người dùng
            double balance = dao.getWalletBalance(userId);
            request.setAttribute("balance", balance); // Truyền số dư ví vào JSP

            // Tính số dư ví sau khi thanh toán khóa học
            double remainingBalance = balance - course.getPrice();
            request.setAttribute("remainingBalance", remainingBalance); // Truyền số dư sau thanh toán vào JSP
        }

        // Truyền thông tin khóa học vào JSP
        request.setAttribute("course", course);
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy session của người dùng
        HttpSession session = request.getSession(false); // false: nếu không có session thì trả về null

        // Kiểm tra nếu session tồn tại và chứa userID
        if (session != null && session.getAttribute("userID") != null) {
            // Lấy userID từ session
            Integer userId = (Integer) session.getAttribute("userID");

            // Lấy courseId từ tham số request
            String courseIdStr = request.getParameter("courseId"); // Lấy tham số courseId từ form
            if (courseIdStr == null || courseIdStr.isEmpty()) {
                // Nếu không có courseId, trả về lỗi hoặc redirect
                response.sendRedirect("errorPage.jsp"); // Chuyển hướng tới trang lỗi
                return;
            }

            int courseId = Integer.parseInt(courseIdStr); // Chuyển tham số thành int

            // Trạng thái đăng ký khóa học (1 = Đã đăng ký)
            int status = 1;

            // Lấy thời gian hiện tại để đăng ký
            Date registrationDate = new Date(System.currentTimeMillis());

            // Đăng ký khóa học
            boolean success = dao.registerCourse(courseId, userId, status, registrationDate);

            if (success) {
                // Chuyển hướng tới trang thông báo thành công
                response.sendRedirect("courseRegistrationSuccess.jsp");
            } else {
                // Nếu đăng ký thất bại, chuyển hướng tới trang thông báo thất bại
                response.sendRedirect("courseRegistrationFailure.jsp");
            }
        } else {
            // Nếu không có userID trong session, chuyển hướng đến trang login
            response.sendRedirect("login.jsp");
        }
    }

}
