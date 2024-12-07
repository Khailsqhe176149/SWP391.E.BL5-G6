package controller.admin;

import dao.DAOListAccount;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import java.io.IOException;
import model.Users;

@WebServlet("/accountDetails")
public class AccountDetails extends HttpServlet {

    private final DAOListAccount dao = new DAOListAccount();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountIdParam = request.getParameter("accountId");
        
        // Kiểm tra xem ID tài khoản có hợp lệ không
        if (accountIdParam != null && !accountIdParam.isEmpty()) {
            int accountId = Integer.parseInt(accountIdParam);
            //Account account = dao.getAccountById(accountId);
            Account account = dao.getAccountDetailsById(accountId);
           // Users useraccountdetails = dao.getUserByAcountID(accountId);
            if (account != null) {
                request.setAttribute("account", account);
                //request.setAttribute("useraccountdetails", account);
                RequestDispatcher dispatcher = request.getRequestDispatcher("accountDetails.jsp");
                dispatcher.forward(request, response);
            } else {
                // Nếu không tìm thấy tài khoản, hiển thị thông báo lỗi
                request.setAttribute("message", "Tài khoản không tồn tại.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("accountList.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("accountList");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Nếu người dùng muốn cập nhật thông tin tài khoản
        if ("update".equals(action)) {
            updateAccount(request, response);
        }
    }

    // Cập nhật thông tin tài khoản
    private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountId = Integer.parseInt(request.getParameter("accountId"));
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String status = request.getParameter("status");

        // Gọi DAO để cập nhật thông tin tài khoản
        dao.updateAccountDetails(accountId, email, role, status);

        // Sau khi cập nhật, chuyển hướng lại trang chi tiết tài khoản
        response.sendRedirect("accountDetails?accountId=" + accountId);
    }
}
