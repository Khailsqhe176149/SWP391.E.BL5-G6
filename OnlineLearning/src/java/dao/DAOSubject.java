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
import model.Subjects;

/**
 *
 * @author ADMIN
 */
public class DAOSubject extends DBContext {

    public List<Subjects> getAllSubjectWithParam(String searchParam, Integer status) {
        List<Subjects> subjects = new ArrayList<>();
        List<Object> list = new ArrayList<>();
//        UserDAO userDAO = new UserDAO();
        try {
            StringBuilder query = new StringBuilder();
            query.append("""
                        SELECT s.Name, s.subjectId, s.status, COUNT(c.courseid) AS course_count
                         FROM Subjects s
                        LEFT JOIN Course c ON s.subjectid = c.subjectid
                         Where 1=1 """);
            if (searchParam != null && !searchParam.trim().isEmpty()) {
                query.append(" AND  s.Name LIKE ? ");
                list.add("%" + searchParam + "%");
            }
            if (status != null) {
                query.append(" AND  s.status = ? ");
                list.add(status);
            }

            query.append("  GROUP BY s.Name,s.subjectId, s.status  ");
            query.append(" ORDER BY s.subjectid DESC");
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query.toString());
            mapParams(preparedStatement, list);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Subjects subject = new Subjects();
                    subject.setSubjectid(rs.getInt("subjectid"));
                    subject.setName(rs.getString("Name"));
                    subject.setStatus(rs.getInt("Status"));
                    subject.setCourseCount(rs.getInt("course_count"));
                    subjects.add(subject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public boolean isExistedSubjectName(String name, Integer excludeId) {
        String sql = "SELECT COUNT(*) FROM subjects WHERE name = ?";
        if (excludeId != null) {
            sql += " AND subjectid != ?";
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            if (excludeId != null) {
                statement.setInt(2, excludeId);
            }
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean addNewSubject(Subjects subject) {
        String sql = "INSERT INTO subjects (name, status) VALUES ( ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, subject.getName());
            statement.setInt(2, subject.getStatus());

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean changeSubjectStatus(int subjectId, String newStatus) {
        String sql = "UPDATE subjects SET status = ? WHERE subjectid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newStatus);
            statement.setInt(2, subjectId);

            int rowsAffected = statement.executeUpdate();

            // Check if the update was successful
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean editSubject(Subjects subject) {
        String sql = "UPDATE subjects SET name = ? , status = ? WHERE subjectid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, subject.getName());
            statement.setInt(2, subject.getStatus());
            statement.setInt(3, subject.getSubjectid());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Subjects getSubjectByID(int subjectId) {
        String sql = "SELECT * FROM subjects WHERE subjectid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, subjectId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Subjects subject = new Subjects();
                    subject.setSubjectid(rs.getInt("subjectid"));
                    subject.setName(rs.getString("Name"));
                    subject.setStatus(rs.getInt("Status"));
                    return subject;
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

    public List<Subjects> Paging(List<Subjects> subjectses, int page, int pageSize) {
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, subjectses.size());

        if (fromIndex > toIndex) {
            // Handle the case where fromIndex is greater than toIndex
            fromIndex = toIndex;
        }

        return subjectses.subList(fromIndex, toIndex);
    }
}
