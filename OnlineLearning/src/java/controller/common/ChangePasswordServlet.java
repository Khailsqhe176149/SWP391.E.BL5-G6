/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.common;

import dao.DAOChangePassword;
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
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/changepassword"})
public class ChangePasswordServlet extends HttpServlet {

    private final DAOChangePassword dao = new DAOChangePassword();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        if (userID != null) {
            request.getRequestDispatcher("/changepassword.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        if (userID != null) {
            String oldPassword = request.getParameter("oldpassword");
            String newPassword = request.getParameter("newpassword");
            String confirmPassword = request.getParameter("confirmpassword");


            Account account = dao.getAccountByUserID(userID);

            if (account != null && account.getPassword().equals(oldPassword)) {
                if (newPassword.equals(confirmPassword)) {
                    account.setPassword(newPassword);
                    boolean isUpdated = dao.updateAccountPassword(account);

                    if (isUpdated) {
                        response.sendRedirect("profile");
                    } else {
                        request.setAttribute("error", "Cann't change password.");
                        request.getRequestDispatcher("/changepassword.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("errorConfirmPassword", "New password and confirm password does not match.");
                    // Giữ lại giá trị đã nhập
                    request.setAttribute("oldpassword", oldPassword);
                    request.setAttribute("newpassword", newPassword);
                    request.setAttribute("confirmpassword", confirmPassword);
                    request.getRequestDispatcher("/changepassword.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorOldPassword", "Old password wrong.");
                request.setAttribute("oldpassword", oldPassword);
                request.setAttribute("newpassword", newPassword);
                request.setAttribute("confirmpassword", confirmPassword);
                request.getRequestDispatcher("/changepassword.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
