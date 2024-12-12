package controller.common;

import common.EmailUntil;
import dao.DAOResetPassword;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgot-password"})
public class ForgotPasswordServlet extends HttpServlet {

    private final DAOResetPassword dao = new DAOResetPassword();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        if ("sendVerificationCode".equals(action)) {
            
            String verificationCode = EmailUntil.getRandomCode();
            EmailUntil.sendEmailOTP(email, verificationCode);
            HttpSession session = request.getSession();
            session.setAttribute("verificationCode", verificationCode);
            boolean isPasswordUpdated = dao.resetPassword(email, verificationCode);
            if (isPasswordUpdated) {
                    response.getWriter().write("Mật khẩu của bạn đã được thay đổi thành công.");
                } else {
                    response.getWriter().write("Có lỗi xảy ra khi thay đổi mật khẩu. Vui lòng thử lại.");
                }
            
             response.sendRedirect("login.jsp");


        }
    }

  

}
