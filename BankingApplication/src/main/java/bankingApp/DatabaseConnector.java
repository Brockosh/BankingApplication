package bankingApp;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.ZoneId;

@Component
public class DatabaseConnector {

    private static final String URL = "jdbc:postgresql://localhost:5432/BankingApplication";
    private static final String USER = "postgres";
    private static final String PASSWORD = "BankApp123";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the PostgresSQL server successfully.");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return conn;
    }

    public ZoneId getTimeZone() {
        String timezone = "UTC"; // Default timezone
        String sql = "SHOW timezone";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                timezone = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ZoneId.of(timezone);
    }
}