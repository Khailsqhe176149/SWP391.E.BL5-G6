package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Users;

/**
 *
 * @author ADMIN
 */
public class DAOUser extends DBContext {

    public Users getByUserId(int userId) {
        String sql = "SELECT * FROM users WHERE userId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Users user = new Users();
                    user.setUserID(rs.getInt("userId"));
                    user.setName(rs.getString("Name"));
                    user.setImg(rs.getString("Img"));
                    return user;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Users getByAccountId(int accId) {
        String sql = "SELECT * from Account a join users u on u.userID= a.userID where a.accId  = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Users user = new Users();
                    user.setUserID(rs.getInt("userId"));
                    user.setName(rs.getString("Name"));
                    user.setImg(rs.getString("Img"));
                    return user;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public static void main(String[] args) {
        DAOUser dao = new DAOUser();
        System.out.println(dao.getByAccountId(1));
    }
}
