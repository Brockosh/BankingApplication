package com.brock.bankingApp;

import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Repository
public class TransactionDAO {
    private DatabaseConnector dbConnector;

    public TransactionDAO(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (transaction_id, sending_account_id, receiving_account_id, type, amount, transaction_time, transaction_location, device, payment_method, recent_change_in_account_details, is_suspicious) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, UUID.randomUUID());
            pstmt.setString(2, transaction.getAccountNumber());
            pstmt.setObject(3, transaction.getDestinationAccount());
            pstmt.setString(4, transaction.getType().toString());
            pstmt.setDouble(5, transaction.getAmount());
            pstmt.setObject(6, Timestamp.from(transaction.getTransactionTime().toInstant()));
            pstmt.setString(7, transaction.getTransactionLocation().toString());
            pstmt.setString(8, transaction.getDevice().toString());
            pstmt.setString(9, transaction.getPaymentMethod().toString());
            pstmt.setBoolean(10, transaction.isRecentChangeInAccountDetails());
            pstmt.setBoolean(11, transaction.isSuspicious());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Transaction getTransactionById(UUID transactionId) {
        String sql = "SELECT * FROM transactions WHERE transaction_id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, transactionId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Transaction(
                        rs.getString("account_number"),
                        TransactionUtils.TransferType.valueOf(rs.getString("type")),
                        rs.getDouble("amount"),
                        UUID.fromString(rs.getString("destination_account")),
                        ZonedDateTime.ofInstant(rs.getTimestamp("transaction_time").toInstant(), dbConnector.getTimeZone()),
                        TransactionUtils.Country.valueOf(rs.getString("transaction_location")),
                        TransactionUtils.DeviceType.valueOf(rs.getString("device")),
                        TransactionUtils.PaymentMethod.valueOf(rs.getString("payment_method")),
                        rs.getBoolean("recent_change_in_account_details")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateTransaction(UUID transactionId, Transaction transaction) {
        String sql = "UPDATE transactions SET sending_account_id = ?, receiving_account_id = ?, type = ?, amount = ?, transaction_time = ?, transaction_location = ?, device = ?, payment_method = ?, recent_change_in_account_details = ?, is_suspicious = ? WHERE transaction_id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, transaction.getAccountNumber());
            pstmt.setObject(2, transaction.getDestinationAccount());
            pstmt.setString(3, transaction.getType().toString());
            pstmt.setDouble(4, transaction.getAmount());
            pstmt.setObject(5, Timestamp.from(transaction.getTransactionTime().toInstant()));
            pstmt.setString(6, transaction.getTransactionLocation().toString());
            pstmt.setString(7, transaction.getDevice().toString());
            pstmt.setString(8, transaction.getPaymentMethod().toString());
            pstmt.setBoolean(9, transaction.isRecentChangeInAccountDetails());
            pstmt.setBoolean(10, transaction.isSuspicious());
            pstmt.setObject(11, transactionId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTransaction(UUID transactionId) {
        String sql = "DELETE FROM transactions WHERE transaction_id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, transactionId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int countTransactionsInLast24Hours(String accountNumber) {
        String sql = "SELECT COUNT(*) FROM transactions WHERE sending_account_id = ? AND transaction_time > ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            // Calculate the timestamp for 24 hours ago
            Timestamp timestamp24HoursAgo = Timestamp.from(ZonedDateTime.now().minusHours(24).toInstant());
            pstmt.setTimestamp(2, timestamp24HoursAgo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

}