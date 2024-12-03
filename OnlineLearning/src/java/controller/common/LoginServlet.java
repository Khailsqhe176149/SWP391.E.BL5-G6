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
    
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        Account account = dao.checkLogin(email, password);
        String username = dao.getNameByEmail(email);
        if (account != null) {
            
            HttpSession session = request.getSession();
            session.setAttribute("userID", account.getUserID());
            session.setAttribute("username",username);
            session.setAttribute("acc",account);
            System.out.println(account.getRole_id());
            response.sendRedirect("home"); // Chuyển hướng tới LessonServlet
        } else {
   
            request.setAttribute("error", "Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

  

}
