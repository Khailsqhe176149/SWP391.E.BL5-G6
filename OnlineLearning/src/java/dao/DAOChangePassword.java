/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Account;
import model.Users;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Khải
 */
public class DAOChangePassword extends DBContext {
       public Account getAccountByUserID(int userID) {
        String sql = "SELECT * FROM Account WHERE userID = ?";
        try (
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account();
                    account.setAcc_id(rs.getInt("accId"));
                    account.setEmail(rs.getString("Email"));
                    account.setPassword(rs.getString("Pass"));
                    account.setStatus(rs.getInt("Status"));
                    account.setRole_id(rs.getInt("Roleid"));
                    account.setUserID(rs.getInt("userID"));
                    return account;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Cập nhật mật khẩu của tài khoản
    public boolean updateAccountPassword(Account account) {
        String sql = "UPDATE Account SET Pass = ? WHERE accId = ?";
        try (
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, account.getPassword());
            ps.setInt(2, account.getAcc_id());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
