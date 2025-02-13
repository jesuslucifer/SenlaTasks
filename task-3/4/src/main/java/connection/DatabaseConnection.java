package connection;

import controller.ConfigProperty;
import controller.Configurator;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DatabaseConnection {
    private Connection connection;
    private static DatabaseConnection instance;
    @ConfigProperty(propertyName = "db.url")
    private String URL;
    @ConfigProperty(propertyName = "db.user")
    private String USER;
    @ConfigProperty(propertyName = "db.password")
    private String PASSWORD;

    private DatabaseConnection() {
        try {
            Configurator.configure(this);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
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
