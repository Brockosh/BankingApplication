package com.brock.bankingApp.Users;

import com.brock.bankingApp.DatabaseConnector;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.UUID;

@Repository
public class UserDAO {
    private DatabaseConnector dbConnector = new DatabaseConnector();


    public void addUser(User user) {
        String sql = "INSERT INTO humans (id, full_name) VALUES (?, ?)";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, user.getId());
            pstmt.setString(2, user.getFullName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public User getUserById(UUID id) {
        String sql = "SELECT id, full_name FROM humans WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                UUID userId = (UUID) rs.getObject("id");
                String fullName = rs.getString("full_name");
                return new User(userId, fullName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public void updateHuman(UUID id, String newName) {
        String sql = "UPDATE humans SET full_name = ? WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setObject(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void deleteUser(UUID id) {
        String sql = "DELETE FROM humans WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAllUsers() {
        String sql = "DELETE FROM humans";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            System.out.println("All humans have been deleted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}