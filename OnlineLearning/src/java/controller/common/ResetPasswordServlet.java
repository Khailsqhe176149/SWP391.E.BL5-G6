package controller.common;

import dao.DAOResetPassword;
import common.EmailUntil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Random;

@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/reset-password"})
public class ResetPasswordServlet extends HttpServlet {

    private final DAOResetPassword dao = new DAOResetPassword();
    private final long EXPIRATION_TIME = 15 * 60 * 1000; // 15 phút

    // Tạo mã OTP ngẫu nhiên
    private String generateOtp() {
        Random random = new Random();
        int otp = random.nextInt(999999); // Tạo mã OTP 6 chữ số
        return String.format("%06d", otp); // Đảm bảo OTP có đủ 6 chữ số
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin từ session
        HttpSession session = request.getSession();
        String sessionOtp = (String) session.getAttribute("resetOtp");
        Long otpCreationTimeObj = (Long) session.getAttribute("resetOtpCreationTime");

        // Kiểm tra xem mã OTP đã được gửi chưa
        if (sessionOtp == null || otpCreationTimeObj == null) {
            response.sendRedirect("errorPage.jsp"); // Nếu OTP chưa được gửi, chuyển hướng đến trang lỗi
            return;
        }

        // Kiểm tra thời gian OTP có hết hạn hay không
        long otpCreationTime = otpCreationTimeObj;
        if (isOtpExpired(otpCreationTime)) {
            response.getWriter().write("Mã OTP đã hết hạn.");
            return;
        }

        // Nếu OTP hợp lệ và chưa hết hạn, chuyển đến trang reset mật khẩu
        String sessionEmail = (String) session.getAttribute("resetOtpEmail");
        request.setAttribute("email", sessionEmail);
        request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String otp = request.getParameter("otp");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        HttpSession session = request.getSession();
        String sessionOtp = (String) session.getAttribute("resetOtp");
        Long otpCreationTimeObj = (Long) session.getAttribute("resetOtpCreationTime");

        // Kiểm tra OTP và thời gian hết hạn
        if (sessionOtp == null || otpCreationTimeObj == null || !sessionOtp.equals(otp)) {
            request.setAttribute("messageErr", "Mã OTP không hợp lệ hoặc đã hết hạn.");
            request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
            return;
        }

        if (isOtpExpired(otpCreationTimeObj)) {
            request.setAttribute("messageErr", "Mã OTP đã hết hạn.");
            request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("messageErr", "Mật khẩu xác nhận không khớp.");
            request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
            return;
        }

        // Tiến hành cập nhật mật khẩu
        String email = (String) session.getAttribute("resetOtpEmail");
        boolean resetSuccess = dao.resetPassword(email, password);

        if (resetSuccess) {
            request.setAttribute("message", "Mật khẩu đã được thay đổi thành công.");
            session.removeAttribute("resetOtp"); // Xóa OTP sau khi thay đổi mật khẩu thành công
            session.removeAttribute("resetOtpEmail"); // Xóa email đã reset
            request.getRequestDispatcher("/login.jsp").forward(request, response); // Chuyển hướng đến trang đăng nhập
        } else {
            request.setAttribute("messageErr", "Có lỗi xảy ra khi cập nhật mật khẩu.");
            request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
        }
    }

    private boolean isOtpExpired(long otpCreationTime) {
        return System.currentTimeMillis() - otpCreationTime > EXPIRATION_TIME;
    }

    // Gửi OTP qua email
    private void sendOtpEmail(String email, String otp) {
        String subject = "Mã OTP cho việc reset mật khẩu";
        String messageText = "Mã OTP của bạn là: " + otp + ". Mã OTP này sẽ hết hạn sau 15 phút.";
        EmailUntil.sendEmail(email, subject, messageText);
    }
}
