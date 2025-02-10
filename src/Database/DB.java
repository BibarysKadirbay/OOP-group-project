package Database;

import Database.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB implements IDB {
    private static DB instance;
    private String host;
    private String username;
    private String password;
    private String dbName;
    private Connection connection;

    private DB(String host, String username, String password, String dbName) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
        getConnection();
    }

    public static DB getInstance(String host, String username, String password, String dbName) {
        if (instance == null) {
            synchronized (DB.class) { // Thread-safe initialization
                if (instance == null) {
                    instance = new DB(host, username, password, dbName);
                }
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    return connection;
                }
            } catch (SQLException e) {
                System.out.println("Database connection check failed: " + e.getMessage());
            }
        }

        String connectionUrl = host + "/" + dbName;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(connectionUrl, username, password);
        } catch (Exception e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
        }
        return connection;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Failed to close connection: " + e.getMessage());
            }
        }
    }
}