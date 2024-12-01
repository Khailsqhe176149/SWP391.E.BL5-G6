/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Khải
 */



import model.Users;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOProfile extends DBContext {
    
    
    // Phương thức lấy thông tin người dùng theo userID
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
    
    // Cập nhật đường dẫn ảnh của người dùng
    public boolean updateUserImage(Users user) {
        String sql = "UPDATE Users SET img = ? WHERE userID = ?";

        try (
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getImg());
            stmt.setInt(2, user.getUserID());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // DAOProfile.java
public boolean updateUserProfile(Users user) {
    String sql = "UPDATE Users SET Name = ?, Gender = ?, Dob = ?, Phone = ?, Address = ? WHERE userID = ?";
    
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, user.getName());
        stmt.setInt(2, user.getGender());
        stmt.setDate(3, (Date) user.getDob());
        stmt.setString(4, user.getPhone());
        stmt.setString(5, user.getAddress());
        stmt.setInt(6, user.getUserID());

        int result = stmt.executeUpdate();
        return result > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    
    
    
}
