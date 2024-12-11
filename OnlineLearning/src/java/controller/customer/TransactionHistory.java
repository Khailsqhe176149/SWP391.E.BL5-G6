/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.customer;

import dao.DAOTransactionHistory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Transaction;

/**
 *
 * @author Admin
 */
@WebServlet(name = "TransactionHistory", urlPatterns = {"/TransactionHistory"})
public class TransactionHistory extends HttpServlet {

    private DAOTransactionHistory dao = new DAOTransactionHistory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin người dùng từ session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userID");

        // Nếu không có userId trong session, chuyển hướng người dùng tới trang login
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy lịch sử giao dịch từ DAO
         double balance = dao.getWalletBalance(userId);
         request.setAttribute("balance", balance);
        List<Transaction> transactions = dao.getTransactionHistoryByUserId(userId);

        // Truyền dữ liệu lịch sử giao dịch vào JSP
        request.setAttribute("transactions", transactions);

        // Chuyển tiếp tới trang transaction-history.jsp
        request.getRequestDispatcher("/view-transaction-history.jsp").forward(request, response);
    }
}

