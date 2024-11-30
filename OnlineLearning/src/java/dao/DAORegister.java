/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Khải
 */
import java.sql.*;
import java.time.LocalDate;

public class DAORegister extends DBContext {
    
    
     public boolean isEmailExist(String email) {
        String sql = "SELECT COUNT(*) FROM Account WHERE Email = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }

    
    public boolean isPhoneExist(String phone) {
        String sql = "SELECT COUNT(*) FROM Users WHERE Phone = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
    
    

    public boolean registerUser(String name, int gender, String dob, String phone, String address, String email, String password) {
        if (isEmailExist(email)) {
            return false;
        }
        if (isPhoneExist(phone)) {
            return false; 
        }
        String sqlUser = "INSERT INTO Users (Name, Gender, Dob, Phone, Img, Address) VALUES (?, ?, ?, ?, ?, ?)";
      
        String sqlAccount = "INSERT INTO Account (Email, Pass, Status, Createdtime, Roleid, userID) VALUES (?, ?, ?, ?, ?, ?)";

        try {
           
            connection.setAutoCommit(false);

            
            PreparedStatement userStmt = connection.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, name);
            userStmt.setInt(2, gender);
            userStmt.setString(3, dob);
            userStmt.setString(4, phone);
            userStmt.setString(5, "abc");  
            userStmt.setString(6, address);

            int rowsAffected = userStmt.executeUpdate();

            
            ResultSet generatedKeys = userStmt.getGeneratedKeys();
            int userId = -1;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1); 
            }

            if (rowsAffected > 0 && userId > 0) {
                
                PreparedStatement accountStmt = connection.prepareStatement(sqlAccount);
                accountStmt.setString(1, email);
                accountStmt.setString(2, password); 
                accountStmt.setInt(3, 1); 
                accountStmt.setDate(4, Date.valueOf(LocalDate.now())); 
                accountStmt.setInt(5, 1); 
                accountStmt.setInt(6, userId); 

                accountStmt.executeUpdate();

                
                connection.commit();
                return true; 
            }

           
            connection.rollback();
        } catch (SQLException e) {
            System.out.println("Error at DAORegister: " + e.getMessage());
            e.printStackTrace();
            try {
                connection.rollback(); 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
               
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;  
    }
}
