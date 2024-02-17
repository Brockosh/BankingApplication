import java.sql.*;
import java.util.UUID;

public class HumanDAO {
    private DatabaseConnector dbConnector = new DatabaseConnector();


    public void addHuman(Human human) {
        String sql = "INSERT INTO humans (id, full_name) VALUES (?, ?)";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, human.getId());
            pstmt.setString(2, human.getFullName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public Human getHumanById(UUID id) {
        String sql = "SELECT id, full_name FROM humans WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Human(rs.getString("full_name"));
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


    public void deleteHuman(UUID id) {
        String sql = "DELETE FROM humans WHERE id = ?";
        try (Connection conn = dbConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAllHumans() {
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
