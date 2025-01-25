package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;
    private static DatabaseConnection instance;

    private static final String URL ="jdbc:mysql://localhost:3306/hotel_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
