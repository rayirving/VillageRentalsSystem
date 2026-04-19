package persistence;

import java.sql.*;
import java.util.HashMap;

public class CategoryDAO {

    // Returns a HashMap of category_id -> name to match InventoryManager
    public HashMap<String, String> loadAll() {
        HashMap<String, String> map = new HashMap<>();
        String sql = "SELECT * FROM CATEGORY";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                map.put(rs.getString("category_id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error loading categories: " + e.getMessage());
        }
        return map;
    }

    public void add(String id, String name) throws SQLException {
        String sql = "INSERT INTO CATEGORY (category_id, name) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
        }
    }

    public void remove(String id) throws SQLException {
        String sql = "DELETE FROM CATEGORY WHERE category_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    public void update(String id, String name) throws SQLException {
        String sql = "UPDATE CATEGORY SET name = ? WHERE category_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, id);
            ps.executeUpdate();
        }
    }
}