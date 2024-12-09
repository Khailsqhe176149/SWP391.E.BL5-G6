package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PostCategory;

/**
 *
 * @author ADMIN
 */
public class DAOPostCategory extends DBContext {

    public PostCategory getById(int pid) {
        String sql = "SELECT * FROM PostCategory WHERE postCatPostidegoryId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pid);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    PostCategory user = new PostCategory();
                    user.setPostCatPostidegoryId(rs.getInt("postCatPostidegoryId"));
                    user.setName(rs.getString("Name"));
                    user.setStatus(rs.getInt("status"));
                    return user;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<PostCategory> getAllPostCategoryWithParam(String searchParam, Integer status) {
        List<PostCategory> subjects = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder();
            query.append(" Select * from PostCategory pc where 1 = 1  ");
            if (searchParam != null && !searchParam.trim().isEmpty()) {
                query.append(" AND pc.name LIKE ? ");
                list.add("%" + searchParam + "%");
            }

            if (status != null) {
                query.append(" AND  pc.status = ? ");
                list.add(status);
            }

            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query.toString());
            mapParams(preparedStatement, list);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    PostCategory post = new PostCategory();
                    post.setPostCatPostidegoryId(rs.getInt("postCatPostidegoryId"));
                    post.setName(rs.getString("Name"));
                    post.setStatus(rs.getInt("status"));
                    subjects.add(post);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getCause());
        }
        return subjects;
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

    public List<PostCategory> Paging(List<PostCategory> postCategorys, int page, int pageSize) {
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, postCategorys.size());

        if (fromIndex > toIndex) {
            // Handle the case where fromIndex is greater than toIndex
            fromIndex = toIndex;
        }

        return postCategorys.subList(fromIndex, toIndex);
    }

    public static void main(String[] args) {
        DAOPostCategory dao = new DAOPostCategory();
        List<PostCategory> list = dao.getAllPostCategoryWithParam(null, null);
        for (PostCategory postCategory : list) {
            System.out.println(postCategory);
        }
    }
}
