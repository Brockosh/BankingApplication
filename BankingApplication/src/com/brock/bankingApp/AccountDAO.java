package com.brock.bankingApp;

import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.UUID;

@Repository
public class AccountDAO {
    private DatabaseConnector dbConnector = new DatabaseConnector();

    public void addAccount(Account account, UUID customerId) {
        String sql = "INSERT INTO accounts (id, customer_id, account_name, account_number, type, balance) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, account.getId());
            pstmt.setObject(2, customerId);
            pstmt.setString(3, account.getAccountName());
            pstmt.setInt(4, account.getAccountNumber());
            pstmt.setString(5, account.getType().toString());
            pstmt.setDouble(6, account.getBalance());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Account getAccountById(UUID accountId) {
        Account account = null;
        String sql = "SELECT id, customer_id, account_name, account_number, type, balance FROM accounts WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Account(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("account_name"),
                        rs.getInt("account_number"),
                        Account.AccountType.valueOf(rs.getString("type")),
                        rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateAccount(UUID accountId, Account updatedAccount) {
        String sql = "UPDATE accounts SET account_name = ?, account_number = ?, type = ?, balance = ? WHERE id = ?";

        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, updatedAccount.getAccountName());
            pstmt.setInt(2, updatedAccount.getAccountNumber());
            pstmt.setString(3, updatedAccount.getType().toString());
            pstmt.setDouble(4, updatedAccount.getBalance());

            pstmt.setObject(5, accountId);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Account updated successfully.");
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAccount(UUID accountId) {
        String sql = "DELETE FROM accounts WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, accountId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("com.brock.bankingApp.Account deleted successfully.");
            } else {
                System.out.println("com.brock.bankingApp.Account not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}