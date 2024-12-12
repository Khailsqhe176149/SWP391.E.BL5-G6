/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Slider;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import model.Post;
import model.Users;

/**
 *
 * @author Khải
 */
public class DAOSliderList extends DBContext {

    public List<Slider> getFilteredSliders(String search, String status) {
        List<Slider> sliders = new ArrayList<>();
        String sql = "SELECT * FROM Slider WHERE 1=1";

        if (search != null && !search.trim().isEmpty()) {
            sql += " AND Title LIKE ?";
        }

        if (status != null && !status.trim().isEmpty()) {
            sql += " AND Status = ?";
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int index = 1;
            if (search != null && !search.trim().isEmpty()) {
                ps.setString(index++, "%" + search + "%");
            }
            if (status != null && !status.trim().isEmpty()) {
                ps.setInt(index, Integer.parseInt(status));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider(
                        rs.getInt("Sliderid"),
                        rs.getInt("Status"),
                        rs.getString("Img"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getTimestamp("Createdtime"),
                        rs.getInt("slidercategoryid")
                );
                sliders.add(slider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sliders;
    }

    public boolean updateSliderImage(Slider slider) {
        String sql = "UPDATE Slider SET img = ? WHERE Sliderid = ?";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, slider.getImg());
            stmt.setInt(2, slider.getSliderid());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Slider getSliderById(int sliderId) {
        Slider slider = null;
        String sql = "SELECT * FROM Slider WHERE Sliderid = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, sliderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                slider = new Slider(
                        rs.getInt("Sliderid"),
                        rs.getInt("Status"),
                        rs.getString("Img"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getTimestamp("Createdtime"),
                        rs.getInt("slidercategoryid")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return slider;
    }

    public void updateSliderStatus(int sliderId, String status) {
        String sql = "UPDATE Slider SET Status = ? WHERE Sliderid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(status));
            ps.setInt(2, sliderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateSlider(Slider slider) {
        String sql = "UPDATE Slider SET  Status = ?, Img = ?, Title = ?, Description = ?, slidercategoryid = ? WHERE Sliderid = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, slider.getStatus());
            ps.setString(2, slider.getImg());
            ps.setString(3, slider.getTitle());
            ps.setString(4, slider.getDescription());
            ps.setInt(5, slider.getSlidercategoryid());
            ps.setInt(6, slider.getSliderid());

            // Thực hiện cập nhật vào cơ sở dữ liệu
            int rowsUpdated = ps.executeUpdate();

            // Kiểm tra kết quả cập nhật
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addSlider(Slider slider) {
        String sql = "INSERT INTO Slider (Status, Img, Title, Description, Createdtime, slidercategoryid) VALUES (?, ?, ?, ?, ?, ?)";
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, slider.getStatus());
            ps.setString(2, slider.getImg());
            ps.setString(3, slider.getTitle());
            ps.setString(4, slider.getDescription());
            ps.setDate(5, currentDate);
            ps.setInt(6, slider.getSlidercategoryid());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void deleteSlider(int sliderId) {
        String sql = "DELETE FROM Slider WHERE Sliderid = ?";

        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, sliderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}
