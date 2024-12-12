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
        // Trả về trang quên mật khẩu (forgot-password.jsp)
        // request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
        request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        if ("sendVerificationCode".equals(action)) {
            //String email = request.getParameter("email");
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
            //String emailverify = email;
            //session.setAttribute("emailverify", emailverify);
            // sendEmail(email, request, response);
             response.sendRedirect("login.jsp");
//        } else if ("confirmVerificationCode".equals(action)) {
//            //confirmVerificationCode(request, response);
//
//            String inputVerificationCode = request.getParameter("verificationCode");
//            String newPassword = request.getParameter("newPassword");
//            String confirmPassword = request.getParameter("confirmPassword");
//            String emailOTP = request.getParameter("emailverify");
//            
//            HttpSession session = request.getSession();
//            String sessionVerificationCode = (String) session.getAttribute("verificationCode");
//
//            // Kiểm tra mã xác thực
//            if (sessionVerificationCode == null || !sessionVerificationCode.equals(inputVerificationCode)) {
//                response.getWriter().write("Mã xác thực không đúng. Vui lòng kiểm tra lại.");
//                return;
//            }
//
//            // Kiểm tra mật khẩu mới và xác nhận mật khẩu
//            if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
//                response.getWriter().write("Mật khẩu mới không khớp. Vui lòng thử lại.");
//                return;
//            }
//
//            // Cập nhật mật khẩu trong cơ sở dữ liệu (giả sử có DAORegister để xử lý việc thay đổi mật khẩu)
//           
//            if ( 1 == 1) {
//                boolean isPasswordUpdated = dao.resetPassword(email, newPassword);
//                if (isPasswordUpdated) {
//                    response.getWriter().write("Mật khẩu của bạn đã được thay đổi thành công.");
//                } else {
//                    response.getWriter().write("Có lỗi xảy ra khi thay đổi mật khẩu. Vui lòng thử lại.");
//                }
//            } 
////            else {
////                response.getWriter().write("Không tìm thấy thông tin người dùng.");
////            }

        }
    }

  

}
