/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Khải
 */
public class DBContext {
    protected Connection connection;
    public DBContext()
    {
        try {
            // Edit URL , username, password to authenticate with your MS SQL Server
            String url = "jdbc:sqlserver://localhost:1433;databaseName= SWP391_G6;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
    public static void main(String[] args) {
        DBContext dbContext = new DBContext();
        Connection conn = dbContext.connection;
        
        try {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Kết nối thành công!");
            } else {
                System.out.println("Kết nối thất bại.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra kết nối: " + e.getMessage());
        }
    }
}

