package controller.common;

import common.EmailUntil;
import dao.DAORegister;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgot-password"})
public class ForgotPasswordServlet extends HttpServlet {

    private final DAORegister dao = new DAORegister();

    private final long EXPIRATION_TIME = 15 * 60 * 1000; // 15 phút

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        // Kiểm tra email có tồn tại trong cơ sở dữ liệu
        if (dao.isEmailExist(email)) {
            // Tạo token ngẫu nhiên
            String token = generateToken();

// Lưu token và thời gian tạo token vào session
            HttpSession session = request.getSession();
            long tokenCreationTime = System.currentTimeMillis(); // Lưu thời gian tạo token
            session.setAttribute("resetToken", token);
            session.setAttribute("resetTokenCreationTime", tokenCreationTime); // Lưu thời gian tạo token
            session.setAttribute("resetTokenEmail", email); // Lưu email vào session

            // Gửi email với token
            String resetLink = "http://localhost:9999/OnlineLearning/reset-password?token=" + token;
            String message = "Click the link to reset your password: " + resetLink;
            EmailUntil.sendEmail(email, "Password Reset Request", message);

            request.setAttribute("message", "Chúng tôi đã gửi một liên kết reset mật khẩu đến email của bạn.");
            request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
        } else {
            request.setAttribute("messageErr", "Email không tồn tại trong hệ thống.");
            request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
        }
    }

    // Hàm tạo token ngẫu nhiên
    private String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[24];  // Tạo 24 byte cho token
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().encodeToString(bytes); // Chuyển sang chuỗi Base64
    }
}
