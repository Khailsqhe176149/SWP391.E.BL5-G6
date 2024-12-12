/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;
/**
 *
 * @author Admin
 */
public class DAOTransactionHistory extends DBContext {

    public DAOTransactionHistory() {
        super();
    }
       public double getWalletBalance(int userId) {
        double balance = -1; // Giá trị mặc định nếu không tìm thấy số dư

        String sql = "SELECT w.Balance "
                + "FROM Wallet w "
                + "INNER JOIN Account a ON w.AccountId = a.accId "
                + "WHERE a.userID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId); // Đặt userId vào câu lệnh SQL

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    balance = rs.getDouble("Balance"); // Lấy số dư ví từ kết quả
                }
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi lấy số dư ví: " + ex.getMessage());
        }

        return balance;
    }
    
  public List<Transaction> getTransactionHistoryByUserId(int userId) {
    List<Transaction> transactions = new ArrayList<>();
    String query = "SELECT th.TransactionId, th.Amount, th.TransactionDate, th.Description, c.Name AS CourseName, c.Img AS CourseImg " +
                   "FROM TransactionHistory th " +
                   "LEFT JOIN Wallet w ON th.WalletId = w.WalletId " +
                   "LEFT JOIN Course c ON th.CourseID = c.CourseID " +
                   "WHERE w.AccountId = (SELECT accId FROM Account WHERE userID = ?) " +
                   "ORDER BY th.TransactionDate DESC";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Transaction transaction = new Transaction();
            transaction.setTransactionId(rs.getInt("TransactionId"));
            transaction.setAmount(rs.getDouble("Amount"));
            transaction.setTransactionDate(rs.getDate("TransactionDate"));
            transaction.setDescription(rs.getString("Description"));
            transaction.setCourseName(rs.getString("CourseName"));
            transaction.setCourseImg(rs.getString("CourseImg")); // Lấy đường dẫn hình ảnh
            transactions.add(transaction);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return transactions;
}


    
    public static void main(String[] args) {
      

       

            // Khởi tạo DAO và truyền connection
            DAOTransactionHistory dao = new DAOTransactionHistory();

            // ID người dùng cần kiểm tra (thay đổi theo dữ liệu của bạn)
            int userId = 1;

            // Gọi hàm để lấy lịch sử giao dịch
            List<Transaction> transactions = dao.getTransactionHistoryByUserId(userId);

            // In kết quả ra console
            if (transactions.isEmpty()) {
                System.out.println("No transactions found for user ID: " + userId);
            } else {
                System.out.println("Transaction history for user ID: " + userId);
                for (Transaction t : transactions) {
                    System.out.println("Transaction ID: " + t.getTransactionId());
                    System.out.println("Amount: " + t.getAmount());
                    System.out.println("Transaction Date: " + t.getTransactionDate());
                    System.out.println("Description: " + t.getDescription());
                    System.out.println("Course Name: " + (t.getCourseName() != null ? t.getCourseName() : "N/A"));
                    System.out.println("-------------------------------------");
                }
            }
      
    }

    
}
