/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.common;

import dao.DAOLogin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Khải
 */
@WebServlet(name="LoginServlet", urlPatterns={"/Login"})
public class LoginServlet extends HttpServlet {
   
   

     private final DAOLogin dao = new DAOLogin();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chuyển tiếp tới trang đăng nhập (login.jsp)
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Kiểm tra thông tin đăng nhập
        Account account = dao.checkLogin(email, password);
        if (account != null) {
            // Đăng nhập thành công, lưu userID vào session
            HttpSession session = request.getSession();
            session.setAttribute("userID", account.getUserID());
            response.sendRedirect("home"); // Chuyển hướng tới LessonServlet
        } else {
            // Đăng nhập thất bại, thông báo lỗi và quay lại trang đăng nhập
            request.setAttribute("error", "Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

  

}
