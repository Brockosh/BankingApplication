import java.sql.*;
import java.util.UUID;

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

    public void deleteAccount(UUID accountId) {
        String sql = "DELETE FROM accounts WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, accountId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }








}
