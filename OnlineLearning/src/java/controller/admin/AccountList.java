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
import java.util.List;

@WebServlet("/accountList")
public class AccountList extends HttpServlet {

    private final DAOListAccount dao = new DAOListAccount();

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy các tham số từ form tìm kiếm
        String keyword = request.getParameter("email");
        String statusParam = request.getParameter("status");
        String roleParam = request.getParameter("role");

        // Chuyển đổi status và role từ String sang Integer nếu có
        Integer status = (statusParam != null && !statusParam.isEmpty()) ? Integer.parseInt(statusParam) : null;
        Integer role = (roleParam != null && !roleParam.isEmpty()) ? Integer.parseInt(roleParam) : null;

        // Gọi phương thức DAO để lấy danh sách tài khoản đã lọc
        List<Account> accountList = dao.getAccountsByFilters(keyword, status, role);

        // Nếu không có tài khoản, có thể thông báo cho người dùng
        if (accountList != null && !accountList.isEmpty()) {
            request.setAttribute("accountList", accountList);
        } else {
            request.setAttribute("message", "Không có tài khoản nào.");
        }

        // Chuyển tiếp tới trang JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/accountList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Dựa trên action, xử lý các trường hợp khác nhau
        if ("updaterole".equals(action)) {
            updateRole(request, response);
        } else if ("updatestatus".equals(action)) {
            updateStatus(request, response);
        } else if ("accountdetails".equals(action)) {
           // accountDetails(request, response);
           int accountId = Integer.parseInt(request.getParameter("accountId"));
        Account account = dao.getAccountDetailsById(accountId);
        request.setAttribute("account", account);
        RequestDispatcher dispatcher = request.getRequestDispatcher("accountDetails.jsp");
        dispatcher.forward(request, response);
           
           
        }  else if ("deleteaccount".equals(action)) {
            deleteAccount(request, response);
        } 
    }

    // Cập nhật role
    private void updateRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountId = request.getParameter("accountId");
        String newRole = request.getParameter("role");

        // Giả sử bạn có DAO để cập nhật role
        dao.updateAccountRole(accountId, newRole);

        // Sau khi cập nhật, chuyển hướng lại danh sách tài khoản
        response.sendRedirect("accountList");
    }

    // Cập nhật status
    private void updateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountId = request.getParameter("accountId");
        String newStatus = request.getParameter("status");

        // Giả sử bạn có DAO để cập nhật status
        dao.updateAccountStatus(accountId, newStatus);

        // Sau khi cập nhật, chuyển hướng lại danh sách tài khoản
        response.sendRedirect("accountList");
    }
    // Xóa tài khoản
   private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // String accountId = request.getParameter("accountId");
          int accountId = Integer.parseInt(request.getParameter("accountId"));
        dao.deleteAccount(accountId);  // Call DAO to delete the account
        response.sendRedirect("accountList");  // Redirect to account list after deletion
    }
 
   private void accountDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String accountIdStr = request.getParameter("accountId");
    
    if (accountIdStr != null) {
        try {
            int accountId = Integer.parseInt(accountIdStr);  // Đảm bảo tham số đúng kiểu
            // Lấy chi tiết tài khoản từ DAO
            Account account = dao.getAccountById(accountId);
            // Đưa thông tin tài khoản vào request để hiển thị
            request.setAttribute("account", account);
            RequestDispatcher dispatcher = request.getRequestDispatcher("accountDetails.jsp");
            dispatcher.forward(request, response);  // Chuyển tiếp tới trang accountDetails.jsp
        } catch (NumberFormatException e) {
            // Nếu accountId không phải là một số hợp lệ, xử lý lỗi
            request.setAttribute("message", "ID tài khoản không hợp lệ.");
            request.getRequestDispatcher("accountList.jsp").forward(request, response);  // Quay lại danh sách
        }
    } else {
        // Nếu không có accountId, chuyển về trang danh sách
        request.setAttribute("message", "Không tìm thấy ID tài khoản.");
        request.getRequestDispatcher("accountList.jsp").forward(request, response);
    }
}

    
    
    
    
}
