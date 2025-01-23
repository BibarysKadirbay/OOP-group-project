package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    private static final String URL = "jdbc:postgresql://localhost:5432/Groups";
    private static final String USER = "postgres";
    private static final String PASSWORD = "5040";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection to PostgreSQL established successfully!");
        } catch (SQLException e) {
            System.err.println("Error while connecting to database: " + e.getMessage());
        }
        return connection;

    }

    public static void main(String[] args) {
        connect();
    }
}