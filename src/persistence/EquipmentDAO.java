package persistence;

import domain.Equipment;
import java.sql.*;
import java.util.HashMap;

public class EquipmentDAO {

    // Loads all equipment with their stock count
    public HashMap<Equipment, Integer> loadAll() {
        HashMap<Equipment, Integer> map = new HashMap<>();
        String sql = "SELECT * FROM EQUIPMENT";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Equipment equipment = mapRow(rs);
                int stock = rs.getInt("stock");
                map.put(equipment, stock);
            }
        } catch (SQLException e) {
            System.out.println("Error loading equipment: " + e.getMessage());
        }
        return map;
    }

    public void add(Equipment equipment) throws SQLException {
        String sql = "INSERT INTO EQUIPMENT (equipment_id, name, description, dailyRentalCost, stock, category_id) " +
                     "VALUES (?, ?, ?, ?, 0, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, equipment.getId());
            ps.setString(2, equipment.getName());
            ps.setString(3, equipment.getDescription());
            ps.setDouble(4, equipment.getDailyRentalCost());
            ps.setString(5, equipment.getCategoryId());
            ps.executeUpdate();
        }
    }

    public void remove(String id) throws SQLException {
        String sql = "DELETE FROM EQUIPMENT WHERE equipment_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    public void addStock(String id, int amount) throws SQLException {
        String sql = "UPDATE EQUIPMENT SET stock = stock + ? WHERE equipment_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, amount);
            ps.setString(2, id);
            ps.executeUpdate();
        }
    }

    public void removeStock(String id, int amount) throws SQLException {
        String sql = "UPDATE EQUIPMENT SET stock = stock - ? WHERE equipment_id = ? AND stock >= ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, amount);
            ps.setString(2, id);
            ps.setInt(3, amount); // prevents stock going below 0
            ps.executeUpdate();
        }
    }

    public void update(Equipment equipment) throws SQLException {
        String sql = "UPDATE EQUIPMENT SET name=?, description=?, dailyRentalCost=?, category_id=? " +
                     "WHERE equipment_id=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, equipment.getName());
            ps.setString(2, equipment.getDescription());
            ps.setDouble(3, equipment.getDailyRentalCost());
            ps.setString(4, equipment.getCategoryId());
            ps.setString(5, equipment.getId());
            ps.executeUpdate();
        }
    }

    private Equipment mapRow(ResultSet rs) throws SQLException {
        return new Equipment(
            rs.getString("equipment_id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getDouble("dailyRentalCost"),
            rs.getString("category_id")
        );
    }
}