package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Post;
import model.PostCategory;
import model.Users;

/**
 *
 * @author ADMIN
 */
public class DAOPost extends DBContext {

    final DAOUser dAOUser = new DAOUser();
    final DAOPostCategory dAOPostCategory = new DAOPostCategory();

    public List<Post> getAllPostWithParam(String searchParam, Integer categoryId, Integer status) {
        List<Post> posts = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder();
            query.append("""
                       Select * from Post p 
                       JOIN PostCategory pc
                       ON p.postCatPostidegoryId = pc.postCatPostidegoryId
                       Where 1=1 """);
            if (searchParam != null && !searchParam.trim().isEmpty()) {
                query.append(" AND  p.Title LIKE ? ");
                list.add("%" + searchParam + "%");
            }
            if (categoryId != null) {
                query.append(" AND   p.postCatPostidegoryId = ? ");
                list.add(categoryId);
            }
            if (status != null) {
                query.append(" AND  pc.status = ? ");
                list.add(status);
            }

            query.append(" Order by p.Createdtime desc");
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query.toString());
            mapParams(preparedStatement, list);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post();
                    Users u = dAOUser.getByUserId(rs.getInt("authorid"));
                    post.setAuthor(u);

                    PostCategory category = dAOPostCategory.getById(rs.getInt("postCatPostidegoryId"));
                    post.setCategory(category);

                    post.setPostid(rs.getInt("postId"));
                    post.setTitle(rs.getString("title"));
                    post.setContent(rs.getString("content"));
                    post.setCreatetime(rs.getDate("Createdtime"));
                    post.setContent(rs.getString("content"));
                    post.setImg(rs.getString("Img"));
                    posts.add(post);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getCause());
        }
        return posts;
    }

    public void updatePost(Post post) {
        String sql = "UPDATE Post SET title = ?, content = ?, img = ?, postCatPostidegoryId = ? WHERE postId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getImg());
            ps.setInt(4, post.getCategory().getPostCatPostidegoryId());
            ps.setInt(5, post.getPostid());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePost(int postId) {
        String sql = "DELETE FROM Post WHERE postId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePost(Post post) {
        String sql = "INSERT INTO Post (title, content, img, postCatPostidegoryId, createdTime, authorid) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getImg());
            ps.setInt(4, post.getCategory().getPostCatPostidegoryId());
            ps.setTimestamp(5, new Timestamp(post.getCreatetime().getTime()));
            ps.setInt(6, post.getAuthor().getUserID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Post getById(int userId) {
        String sql = "SELECT * FROM Post WHERE postid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Post post = new Post();
                    Users u = dAOUser.getByUserId(rs.getInt("authorid"));
                    post.setAuthor(u);

                    PostCategory category = dAOPostCategory.getById(rs.getInt("postCatPostidegoryId"));
                    post.setCategory(category);

                    post.setPostid(rs.getInt("postId"));
                    post.setTitle(rs.getString("title"));
                    post.setContent(rs.getString("content"));
                    post.setCreatetime(rs.getDate("Createdtime"));
                    post.setContent(rs.getString("content"));
                    post.setImg(rs.getString("Img"));
                    return post;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void mapParams(PreparedStatement ps, List<Object> args) throws SQLException {
        int i = 1;
        for (Object arg : args) {
            if (arg instanceof Date) {
                ps.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
            } else if (arg instanceof Integer) {
                ps.setInt(i++, (Integer) arg);
            } else if (arg instanceof Long) {
                ps.setLong(i++, (Long) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (Double) arg);
            } else if (arg instanceof Float) {
                ps.setFloat(i++, (Float) arg);
            } else {
                ps.setString(i++, (String) arg);
            }

        }
    }

    public List<Post> Paging(List<Post> posts, int page, int pageSize) {
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, posts.size());

        if (fromIndex > toIndex) {
            // Handle the case where fromIndex is greater than toIndex
            fromIndex = toIndex;
        }

        return posts.subList(fromIndex, toIndex);
    }
}
