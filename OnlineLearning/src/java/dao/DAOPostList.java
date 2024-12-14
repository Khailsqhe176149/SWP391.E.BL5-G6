/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Post;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import model.Users;

/**
 *
 * @author Khải
 */
public class DAOPostList extends DBContext  {
     public List<Post> getFilteredPosts(String search, String status, int page, int pageSize) {
        List<Post> posts = new ArrayList<>();
        // Tính toán OFFSET dựa trên trang và kích thước trang
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM Post WHERE 1=1";

        if (search != null && !search.trim().isEmpty()) {
            sql += " AND Title LIKE ?";
        }

        if (status != null && !status.trim().isEmpty()) {
            sql += " AND Status = ?";
        }
        // Sắp xếp và phân trang
        sql += " ORDER BY Postid OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int index = 1;
            if (search != null && !search.trim().isEmpty()) {
                ps.setString(index++, "%" + search + "%");
            }
            if (status != null && !status.trim().isEmpty()) {
                ps.setInt(index, Integer.parseInt(status));
            }
             ps.setInt(index++, offset);  // OFFSET: Bỏ qua số bản ghi theo trang
            ps.setInt(index++, pageSize);  // FETCH NEXT: Số bản ghi lấy trong trang
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post(
                    rs.getInt("Postid"),
                    rs.getString("Title"),
                    rs.getString("Content"),
                    rs.getTimestamp("Createdtime"),
                    rs.getInt("authorid"),
                    rs.getString("Img"),
                    rs.getInt("Status"),
                    rs.getInt("Sliderid")
                );
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }
     public int getTotalPostCount(String search, String status) {
        int totalCount = 0;
        String sql = "SELECT COUNT(*) FROM Post WHERE 1=1";

        // Thêm điều kiện tìm kiếm nếu có
        if (search != null && !search.trim().isEmpty()) {
            sql += " AND Title LIKE ?";
        }

        // Thêm điều kiện trạng thái nếu có
        if (status != null && !status.trim().isEmpty()) {
            sql += " AND Status = ?";
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int index = 1;

            // Thêm tham số cho tìm kiếm nếu có
            if (search != null && !search.trim().isEmpty()) {
                ps.setString(index++, "%" + search + "%");
            }

            // Thêm tham số cho trạng thái nếu có
            if (status != null && !status.trim().isEmpty()) {
                ps.setInt(index++, Integer.parseInt(status));
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalCount;
    }
     
     
     
     public boolean updatePostImage(Post post) {
        String sql = "UPDATE Post SET img = ? WHERE Postid = ?";

        try (
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, post.getImg());
            stmt.setInt(2, post.getPostid());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
     
     public Post getPostById(int postId) {
        Post post = null;
        String sql = "SELECT * FROM Post WHERE Postid = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                post = new Post(
                    rs.getInt("Postid"),
                    rs.getString("Title"),
                    rs.getString("Content"),
                    rs.getTimestamp("Createdtime"),
                    rs.getInt("authorid"),
                    rs.getString("Img"),
                    rs.getInt("Status"),
                    rs.getInt("Sliderid")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }
     
     
     
     
     public Post getPostAndAuthorNameById(int postId) {
        String sql = "SELECT p.*, u.Name AS authorName FROM Post p " +
                     "JOIN Users u ON p.authorid = u.userID WHERE p.postid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Post post = new Post();
                post.setPostid(rs.getInt("postid"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setImg(rs.getString("img"));
                post.setStatus(rs.getInt("status"));
                post.setSliderid(rs.getInt("sliderid"));
                post.setAuthorName(rs.getString("authorName")); // Lưu tên tác giả
                return post;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
     

     
    // Update post status
    public void updatePostStatus(int postId, String status) {
        String sql = "UPDATE Post SET Status = ? WHERE Postid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(status));
            ps.setInt(2, postId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
    public boolean updatePost(Post post) {
        String sql = "UPDATE Post SET title = ?, content = ?, img = ?, status = ?, sliderid = ? WHERE postid = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getImg());
            ps.setInt(4, post.getStatus());
            ps.setInt(5, post.getSliderid());
            ps.setInt(6, post.getPostid());

            // Thực hiện cập nhật vào cơ sở dữ liệu
            int rowsUpdated = ps.executeUpdate();

            // Kiểm tra kết quả cập nhật
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
             
    public boolean addPost(Post post) {
    String sql = "INSERT INTO Post (Title, Content, Img, Status, Sliderid, authorid, Createdtime) VALUES (?, ?, ?, ?, ?, ?, ?)";
    java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, post.getTitle());
        ps.setString(2, post.getContent());
        ps.setString(3, post.getImg());
        ps.setInt(4, post.getStatus());
        ps.setInt(5, post.getSliderid());
        ps.setInt(6, post.getAuthorid());
        ps.setDate(7, currentDate); 

        int result = ps.executeUpdate();
        return result > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

 public void deletePost(int postId) {
        String sql = "DELETE FROM Post WHERE Postid = ?";

        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
