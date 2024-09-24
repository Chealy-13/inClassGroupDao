package week02.classicmodels.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDao {

    // Get connection
    protected Connection getConnection() {
        String url = "jdbc:mysql://127.0.0.1:3306/classicmodels";
        String username = "root";
        String password = "";

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        return null;
    }

    // Close connection
    protected void freeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Failed to close the connection.");
                e.printStackTrace();
            }
        }
    }
}
