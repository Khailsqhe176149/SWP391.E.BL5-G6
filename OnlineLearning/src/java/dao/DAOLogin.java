/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Account;

/**
 *
 * @author Khải
 */
public class DAOLogin extends DBContext {
   

    
    public Account checkLogin(String email, String password) {
        String sql = "SELECT accId, Email, Pass, Status, Createdtime,Roleid,userID FROM Account WHERE Email = ? AND Pass = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
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
        } catch (SQLException e) {
            System.out.println("Error at checkLogin: " + e.getMessage());
        }
        return null;
    }
}