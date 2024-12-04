
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import model.Course;

/**
 *
 * @author Admin
 */
public class DAOCheckOut extends DBContext {

    // Constructor kế thừa từ DBContext
    public DAOCheckOut() {
        super(); // Gọi constructor của lớp DBContext để khởi tạo kết nối
    }
     public boolean registerCourse(int courseId, int userId, int status, java.util.Date registrationDate) {
        String sql = "INSERT INTO CourseRegistrater (CourseID, UserID, Status, RegistrationDate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ps.setInt(2, userId);
            ps.setInt(3, status);
            ps.setDate(4, new java.sql.Date(registrationDate.getTime())); // Chuyển đổi java.util.Date sang java.sql.Date
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Nếu có ít nhất 1 hàng bị ảnh hưởng, tức là thành công
        } catch (SQLException ex) {
            System.out.println("Lỗi khi đăng ký khóa học: " + ex.getMessage());
        }
        return false; // Trả về false nếu có lỗi xảy ra
    }

    public Course getCourseById(int courseId) {
        Course course = null;
        String query = "SELECT Courseid, Name, Subjectid, Price, Authorid, Description, Img, Createdtime, Status, Tag "
                + "FROM Course WHERE Courseid = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    course = mapResultSetToCourse(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
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
    
    public boolean updateWalletBalance(int userId, double newBalance) {
    String sql = "UPDATE Wallet "
               + "SET Balance = ? "
               + "FROM Wallet w "
               + "INNER JOIN Account a ON w.AccountId = a.accId "
               + "WHERE a.userID = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setDouble(1, newBalance); // Đặt số dư mới
        ps.setInt(2, userId); // Đặt userID
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0; // Trả về true nếu cập nhật thành công
    } catch (SQLException ex) {
        System.out.println("Lỗi khi cập nhật số dư ví: " + ex.getMessage());
    }
    return false; // Trả về false nếu có lỗi xảy ra
}


    private Course mapResultSetToCourse(ResultSet rs) throws SQLException {
        int courseId = rs.getInt("Courseid");
        String name = rs.getString("Name");
        int subjectId = rs.getInt("Subjectid");
        double price = rs.getDouble("Price");
        int authorId = rs.getInt("Authorid");
        String description = rs.getString("Description");
        String img = rs.getString("Img");
        Date createdTime = rs.getDate("Createdtime");
        int status = rs.getInt("Status");
        String tag = rs.getString("Tag");

        return new Course(courseId, name, subjectId, price, authorId, description, img, createdTime, status, tag);
    }

    public static void main(String[] args) {
        int userId = 1; // Lấy userId từ session hoặc từ yêu cầu HTTP
        DAORegisterCourse dao = new DAORegisterCourse();
        double balance = dao.getWalletBalance(userId);
        System.out.println("Số dư ví của người dùng: " + balance);
    }
}
