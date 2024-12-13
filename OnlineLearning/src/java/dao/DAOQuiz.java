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
import model.Quiz;
import model.Subjects;

/**
 *
 * @author ADMIN
 */
public class DAOQuiz extends DBContext {

    public List<Quiz> getAllQuizWithParam(String searchParam) {
        List<Quiz> subjects = new ArrayList<>();
        List<Object> list = new ArrayList<>();
//        UserDAO userDAO = new UserDAO();
        try {
            StringBuilder query = new StringBuilder();
            query.append("""
                        SELECT * from Quiz
                         Where 1=1 """);
            if (searchParam != null && !searchParam.trim().isEmpty()) {
                query.append(" AND  Name LIKE ? ");
                list.add("%" + searchParam + "%");
            }

            query.append(" ORDER BY Quizid DESC");
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query.toString());
            mapParams(preparedStatement, list);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Quiz quiz = new Quiz();
                    quiz.setQuizid(rs.getInt("Quizid"));
                    quiz.setName(rs.getString("Name"));
                    quiz.setMiniumscore(rs.getFloat("minimumscore"));
                    quiz.setDescription(rs.getString("Description"));
                    quiz.setContent(rs.getString("content"));
                    subjects.add(quiz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public boolean isExistedQuizName(String name, Integer excludeId) {
        String sql = "SELECT COUNT(*) FROM quiz WHERE name = ?";
        if (excludeId != null) {
            sql += " AND quizId != ?";
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
            Logger.getLogger(DAOQuiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean addNewQuiz(Quiz quiz) {
        String sql = "INSERT INTO quiz (name, minimumscore, Content, Description) VALUES ( ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, quiz.getName());
            statement.setFloat(2, quiz.getMiniumscore());
            statement.setString(3, quiz.getContent());
            statement.setString(4, quiz.getDescription());

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuiz.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean editQuiz(Quiz quiz) {
        String sql = "UPDATE quiz SET name = ? , minimumscore = ? , Content = ? , Description = ?  WHERE quizid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, quiz.getName());
            statement.setFloat(2, quiz.getMiniumscore());
            statement.setString(3, quiz.getContent());
            statement.setString(4, quiz.getDescription());
            statement.setInt(5, quiz.getQuizid());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuiz.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Quiz getQuizById(int qid) {
        String sql = "SELECT * FROM quiz WHERE quizid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, qid);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Quiz quiz = new Quiz();
                    quiz.setQuizid(rs.getInt("Quizid"));
                    quiz.setName(rs.getString("Name"));
                    quiz.setMiniumscore(rs.getFloat("minimumscore"));
                    quiz.setDescription(rs.getString("Description"));
                    quiz.setContent(rs.getString("content"));
                    return quiz;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuiz.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<Quiz> Paging(List<Quiz> quizs, int page, int pageSize) {
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, quizs.size());

        if (fromIndex > toIndex) {
            // Handle the case where fromIndex is greater than toIndex
            fromIndex = toIndex;
        }

        return quizs.subList(fromIndex, toIndex);
    }
}
