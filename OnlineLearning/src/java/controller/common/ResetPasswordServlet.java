package controller.common;

import dao.DAOResetPassword;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/reset-password"})
public class ResetPasswordServlet extends HttpServlet {

    private final DAOResetPassword dao = new DAOResetPassword();

    private final long EXPIRATION_TIME = 15 * 60 * 1000; // 15 phút

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");

        // Kiểm tra token hợp lệ (trong session)
        HttpSession session = request.getSession();
        String sessionToken = (String) session.getAttribute("resetToken");
        Long tokenCreationTimeObj = (Long) session.getAttribute("resetTokenCreationTime");

        // Kiểm tra nếu tokenCreationTime là null
        if (tokenCreationTimeObj == null) {
            response.getWriter().write("Token không hợp lệ hoặc đã hết hạn.");
            return;
        }

        long tokenCreationTime = tokenCreationTimeObj; // Chuyển Long thành long an toàn

        // Kiểm tra token và thời gian hết hạn
        if (token != null && sessionToken != null && sessionToken.equals(token)) {
            // Kiểm tra token hết hạn
            if (isTokenExpired(tokenCreationTime)) {
                response.getWriter().write("Token đã hết hạn.");
                return;
            }

            // Nếu token hợp lệ và chưa hết hạn, chuyển tiếp đến trang reset mật khẩu
            String sessionEmail = (String) session.getAttribute("resetTokenEmail");
            request.setAttribute("email", sessionEmail);
            request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
        } else {
            response.getWriter().write("Token không hợp lệ hoặc đã hết hạn.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

 
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("messageErr", "Mật khẩu và xác nhận mật khẩu không khớp.");
            request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
            return;
        }

 
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("resetToken");
        String email = (String) session.getAttribute("resetTokenEmail");


        if (token != null && email != null && dao.resetPassword(email, newPassword)) {
            session.removeAttribute("resetToken");
            session.removeAttribute("resetTokenCreationTime");
            session.removeAttribute("resetTokenEmail");
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("messageErr", "Không thể reset mật khẩu.");
            request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
        }
    }


    private boolean isTokenExpired(long tokenCreationTime) {
        return (System.currentTimeMillis() - tokenCreationTime) > EXPIRATION_TIME;
    }
}
