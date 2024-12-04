/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Khải
 */
import model.Account;
import model.Roles;
import model.Users;
import java.sql.*;
import java.util.*;

public class DAOListAccount extends DBContext {

    // Phương thức lấy tất cả tài khoản
    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        String sql = "  SELECT accId, Email, Pass, Status, Createdtime, Roleid, u.userID,u.Name FROM Account a join Users u on a.userID=u.userID";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                Account account = new Account(
                        rs.getInt("accId"),
                        rs.getString("Email"),
                        rs.getString("Pass"),
                        rs.getInt("Status"),
                        rs.getDate("Createdtime"),
                        rs.getInt("Roleid"),
                        rs.getInt("userID"),
                        rs.getString("Name")
                );
                accountList.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Error at getAllAccounts: " + e.getMessage());
        }
        return accountList;
    }

    // Phương thức lấy chi tiết tài khoản theo ID
    public Account getAccountById(int accId) {
        String sql = "SELECT accId, Email, Pass, Status, Createdtime, Roleid, userID FROM Account WHERE accId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getInt("accId"),
                            rs.getString("Email"),
                            rs.getString("Pass"),
                            rs.getInt("Status"),
                            rs.getDate("Createdtime"),
                            rs.getInt("Roleid"),
                            rs.getInt("userID")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error at getAccountById: " + e.getMessage());
        }
        return null;
    }
    
    public Account getAccountDetailsById(int accountId) {
    Account account = null;

   
    String sql = "SELECT a.accId, a.Email, a.Pass, a.Status, a.Createdtime, a.Roleid, "
            + "u.userID, u.Name, u.Phone "
            + "FROM Account a "
            + "JOIN Users u ON a.userID = u.userID "
            + "WHERE a.accId = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        // Set tham số accountId vào câu truy vấn
        ps.setInt(1, accountId);

        // Thực thi truy vấn
        try (ResultSet rs = ps.executeQuery()) {
            // Nếu tìm thấy tài khoản, tạo đối tượng Account
            if (rs.next()) {
                account = new Account(
                        rs.getInt("accId"),
                        rs.getString("Email"),
                        rs.getString("Pass"),
                        rs.getInt("Status"),
                        rs.getDate("Createdtime"),
                        rs.getInt("Roleid"),
                        rs.getInt("userID"),
                        rs.getString("Name"),
                        rs.getString("Phone") // Lấy thêm trường Phone từ bảng Users
                );
            }
        }
    } catch (SQLException e) {
        System.out.println("Error at getAccountById: " + e.getMessage());
    }

    return account; // Trả về tài khoản hoặc null nếu không tìm thấy
}
    

//     public Users getUserByAcountID(int userID) {
//        String sql = " SELECT   u.userID, u.Name, u.Gender, u.Dob, u.Phone, u.Img, u.Address  FROM Users  JOIN Account a ON a.userID = u.userID   WHERE a.accId = ?";
//
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, userID);
//            ResultSet rs = ps.executeQuery();
//
//            // Nếu có kết quả trả về, tạo đối tượng Users
//            if (rs.next()) {
//                return new Users(
//                        rs.getInt("userID"),
//                        rs.getString("Name"),
//                        rs.getInt("Gender"),
//                        rs.getDate("Dob"),
//                        rs.getString("Phone"),
//                        rs.getString("Img"),
//                        rs.getString("Address")
//                );
//            }
//        } catch (SQLException e) {
//            System.out.println("Error at getUserByID: " + e.getMessage());
//        }
//
//        return null; // Trả về null nếu không tìm thấy người dùng
//    }

    

    public Users getUserByID(int userID) {
        String sql = "SELECT userID, Name, Gender, Dob, Phone, Img, Address FROM Users WHERE userID = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            // Nếu có kết quả trả về, tạo đối tượng Users
            if (rs.next()) {
                return new Users(
                        rs.getInt("userID"),
                        rs.getString("Name"),
                        rs.getInt("Gender"),
                        rs.getDate("Dob"),
                        rs.getString("Phone"),
                        rs.getString("Img"),
                        rs.getString("Address")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error at getUserByID: " + e.getMessage());
        }

        return null; // Trả về null nếu không tìm thấy người dùng
    }

    // Phương thức xóa tài khoản
    public void deleteAccount(int accountId) {
        String sql = "DELETE FROM Account WHERE accId = ?";

        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật status của tài khoản
    public boolean updateAccountStatus(String accountId, String status) {
        String sql = "UPDATE Account SET Status = ? WHERE accId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status); // Thiết lập status mới
            ps.setString(2, accountId); // Thiết lập accountId

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật role của tài khoản
    public boolean updateAccountRole(String accountId, String role) {
        String sql = "UPDATE Account SET Roleid = ? WHERE accId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, role); // Thiết lập role mới
            ps.setString(2, accountId); // Thiết lập accountId

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Account> getAccountsByFilters(String keyword, Integer status, Integer role) {
        List<Account> accountList = new ArrayList<>();

        // Bắt đầu xây dựng câu truy vấn SQL với điều kiện động
        StringBuilder sql = new StringBuilder("SELECT accId, Email, Pass, Status, Createdtime, Roleid, u.userID, u.Name "
                + "FROM Account a "
                + "JOIN Users u ON a.userID = u.userID WHERE 1=1");

        // Thêm điều kiện tìm kiếm theo email nếu từ khóa có
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND a.Email LIKE ?");
        }

        // Thêm điều kiện lọc theo status nếu status không phải null
        if (status != null) {
            sql.append(" AND a.Status = ?");
        }

        // Thêm điều kiện lọc theo role nếu role không phải null
        if (role != null) {
            sql.append(" AND a.Roleid = ?");
        }

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int parameterIndex = 1;

            // Nếu có từ khóa email, thiết lập tham số tìm kiếm email
            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(parameterIndex++, "%" + keyword + "%");
            }

            // Nếu có filter theo status, thiết lập tham số status
            if (status != null) {
                ps.setInt(parameterIndex++, status);
            }

            // Nếu có filter theo role, thiết lập tham số role
            if (role != null) {
                ps.setInt(parameterIndex++, role);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Account account = new Account(
                            rs.getInt("accId"),
                            rs.getString("Email"),
                            rs.getString("Pass"),
                            rs.getInt("Status"),
                            rs.getDate("Createdtime"),
                            rs.getInt("Roleid"),
                            rs.getInt("userID"),
                            rs.getString("Name")
                    );
                    accountList.add(account);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error at getAccountsByFilters: " + e.getMessage());
        }

        return accountList; // Trả về danh sách tài khoản đã lọc
    }

    public void updateAccountDetails(int accountId, String email, String role, String status) {
        String query = "UPDATE Account SET Email = ?, Roleid = ?, Status = ? WHERE accId = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(query)) {

            // Set giá trị cho các tham số trong câu lệnh SQL
            statement.setString(1, email);
            statement.setString(2, role);
            statement.setString(3, status);
            statement.setInt(4, accountId);

            // Thực thi câu lệnh cập nhật
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cập nhật tài khoản thành công.");
            } else {
                System.out.println("Không tìm thấy tài khoản để cập nhật.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi cập nhật tài khoản: " + e.getMessage());
        }
    }

}
