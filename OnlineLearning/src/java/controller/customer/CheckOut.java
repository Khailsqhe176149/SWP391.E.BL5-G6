/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dao.DAOCheckOut;
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
import model.Course;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckOut", urlPatterns = {"/CheckOut"})
public class CheckOut extends HttpServlet {

    private DAOCheckOut dao = new DAOCheckOut();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin khóa học
        String courseIdStr = request.getParameter("courseId");
        int courseId = Integer.parseInt(courseIdStr);
        Course course = dao.getCourseById(courseId);

        // Lấy thông tin người dùng từ session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userID");

        // Nếu không có userId trong session, chuyển hướng người dùng tới trang login
        if (userId == null) {
            response.sendRedirect("login.jsp"); // Chuyển hướng tới trang login
            return; // Dừng lại để không tiếp tục xử lý
        }

        // Lấy tên người dùng từ session
        String username = (String) session.getAttribute("username");

        // Lấy số dư ví của người dùng
        double balance = dao.getWalletBalance(userId);
        request.setAttribute("balance", balance); // Truyền số dư ví vào JSP

        // Tính số dư ví sau khi thanh toán khóa học
        double remainingBalance = balance - course.getPrice();
        request.setAttribute("remainingBalance", remainingBalance); // Truyền số dư sau thanh toán vào JSP

        // Truyền thông tin khóa học và tên người dùng vào JSP
        request.setAttribute("course", course);
        request.setAttribute("username", username); // Truyền tên người dùng vào JSP

        // Chuyển tiếp tới trang test.jsp
        request.getRequestDispatcher("/check-out.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin khóa học
        String courseIdStr = request.getParameter("courseId");
        int courseId = Integer.parseInt(courseIdStr);
        Course course = dao.getCourseById(courseId);

        // Lấy thông tin người dùng từ session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userID");

        // Nếu không có userId trong session, chuyển hướng người dùng tới trang login
        if (userId == null) {
            response.sendRedirect("login.jsp"); // Chuyển hướng tới trang login
            return;
        }

        // Lấy tên người dùng từ session
        String username = (String) session.getAttribute("username");

        // Lấy số dư ví của người dùng
        double balance = dao.getWalletBalance(userId);

        // Kiểm tra số dư ví và tiến hành thanh toán
        if (balance >= course.getPrice()) {
            // Tiến hành thanh toán và cập nhật ví
            boolean registrationSuccess = dao.registerCourse(courseId, userId, 1, new Date());

            if (registrationSuccess) {
                boolean balanceUpdated = dao.updateWalletBalance(userId, balance - course.getPrice());

                
                if (balanceUpdated) {
                    int walletId = dao.getWalletIdByUserId(userId);
                    // Ghi lại lịch sử giao dịch, có CourseID để biết giao dịch này gắn liền với khóa học nào
                    boolean historyRecorded = dao.addTransactionHistory(walletId, -course.getPrice(), 1, "Payment for course ID: " + courseId, courseId);

                    if (historyRecorded) {
                        request.setAttribute("message", "Registration successful!");
                        request.setAttribute("redirectUrl", "home");
                    } else {
                        request.setAttribute("message", "Registration successful but failed to record transaction history!");
                        request.setAttribute("redirectUrl", "errorPage.jsp");
                    }
                } else {
                    request.setAttribute("message", "Failed to update wallet balance!");
                    request.setAttribute("redirectUrl", "errorPage.jsp");
                }

            } else {
                request.setAttribute("message", "Failed to update wallet balance!");
                request.setAttribute("redirectUrl", "errorPage.jsp");
            }

        } else {
            // Nếu số dư không đủ, hiển thị thông báo lỗi
            request.setAttribute("message", "Insufficient balance to register for the course!");
            request.setAttribute("redirectUrl", "insufficientBalance.jsp");
        }

        // Chuyển đến JSP để hiển thị modal
        request.getRequestDispatcher("/check-out.jsp").forward(request, response);
    }

}
