package controller.admin;

import common.EmailUntil;
import dao.DAORegister;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="AddAccountServlet", urlPatterns={"/addAccount"})
public class AddAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DAORegister dao = new DAORegister(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/addAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Lấy giá trị role và status từ form
        int role = Integer.parseInt(request.getParameter("role"));
        int status = Integer.parseInt(request.getParameter("status"));

        boolean hasError = false;
        StringBuilder errorMessage = new StringBuilder();
        boolean emailExist = dao.isEmailExist(email);
        boolean phoneExist = dao.isPhoneExist(phone);

        if (emailExist) {
            request.setAttribute("emailError", "Email đã tồn tại. Vui lòng chọn email khác.");
        }

        if (phoneExist) {
            request.setAttribute("phoneError", "Số điện thoại đã tồn tại. Vui lòng chọn số điện thoại khác.");
        }
        if (email != null && !email.contains("@")) {
            request.setAttribute("emailError", "Email phải có '@'.");
        }

        if (phone != null && !phone.matches("^0\\d{9}$")) {
            request.setAttribute("phoneError", "Số điện thoại phải có 10 số và bắt đầu bằng số 0.");
        }

        if (emailExist || phoneExist) {
            request.getRequestDispatcher("/addAccount.jsp").forward(request, response);
            return;
        }

        // Gọi DAO để thực hiện đăng ký, truyền cả role và status vào
        boolean isRegistered = dao.registerUserForAdmin(name, gender, dob, phone, address, email, password, status, role);

        if (isRegistered) {
            String subject = "Registration Successful";
            String message = "Dear " + name + ",\n\nYour account has been successfully created!";
            EmailUntil.sendEmail(email, subject, message);
            response.sendRedirect("accountList");
        } else {
            request.setAttribute("error", "Đăng ký thất bại. Vui lòng thử lại.");
            request.getRequestDispatcher("/accountList").forward(request, response);
        }
    }
}
