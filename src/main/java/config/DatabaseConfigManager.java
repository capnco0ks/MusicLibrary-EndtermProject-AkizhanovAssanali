package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfigManager  {
    private static final DatabaseConfigManager instance = new DatabaseConfigManager();

    private static final String URL = "jdbc:postgresql://localhost:5432/music_library_db";
    private static final String USER = "music_user";
    private static final String PASSWORD = "pass123";
    
    private DatabaseConfigManager() {

    }

    public static DatabaseConfigManager getInstance() {
        return instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }
}
