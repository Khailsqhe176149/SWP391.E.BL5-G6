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
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
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
         String userImg = dao.getImgByEmail(email);
        if (account != null) {
            if (account.getStatus() == 0) {
                HttpSession session = request.getSession();
                session.setAttribute("notificationErr", "Your account has been banned !");
                response.sendRedirect("login.jsp");
            } else {
            HttpSession session = request.getSession();
            session.setAttribute("userID", account.getUserID());
            session.setAttribute("username", username);
             session.setAttribute("userImg", userImg);
            session.setAttribute("accID", account.getAcc_id());
            session.setAttribute("acc", account);
            System.out.println(account.getRole_id());
            response.sendRedirect("home"); 
            }
        } else {
            HttpSession session = request.getSession();
                 session.setAttribute("notificationErr", "Wrong account or password !");
                response.sendRedirect("login.jsp");
        }
    }

}
