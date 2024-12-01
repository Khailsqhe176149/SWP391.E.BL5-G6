/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
/**
 *
 * @author Khải
 */
public class DAOResetPassword extends DBContext {
   

    public boolean resetPassword(String email, String newPassword) {
        boolean isUpdated = false;
        String query = "UPDATE Account SET password = ? WHERE email = ?";
        
        try (
             PreparedStatement ps = connection.prepareStatement(query)) {
            
            ps.setString(1, newPassword);
            ps.setString(2, email);
            int rowsUpdated = ps.executeUpdate();
            
            if (rowsUpdated > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return isUpdated;
    }
   
}
