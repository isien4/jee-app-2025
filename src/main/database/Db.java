package src.main.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {

    private String url = "jdbc:postgresql://localhost:5432/jee_db";
    private String user = "postgres";
    private String password = "passer";

    private static Connection connection = null;

    public Connection getConnection() {
        // Design Pattern Singleton
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("SUCCESS");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("FAILED");
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
