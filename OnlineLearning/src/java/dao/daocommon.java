/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Khải
 */
public class daocommon extends DBContext {
   

    
    public Account checkLogin(String email, String password) {
        String sql = "SELECT accId, Email, Pass, Status, Createdtime,Roleid FROM Account WHERE Email = ? AND Pass = ?";
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
                    rs.getString("Createdtime"),
                    rs.getInt("Roleid")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error at checkLogin: " + e.getMessage());
        }
        return null;
    }
}
