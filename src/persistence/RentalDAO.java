package persistence;

import domain.Rental;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO {

    public List<Rental> loadAll() {
        List<Rental> list = new ArrayList<>();
        String sql = "SELECT * FROM RENTAL";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.out.println("Error loading rentals: " + e.getMessage());
        }
        return list;
    }

    public void add(Rental rental) throws SQLException {
        String sql = "INSERT INTO RENTAL (rental_id, notes, status, rentalDate, returnDate, customer_id, equipment_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rental.getId());
            ps.setString(2, rental.getNotes());
            ps.setString(3, rental.getStatus());
            ps.setDate(4, rental.getRentalDate());
            ps.setDate(5, rental.getReturnDate());
            ps.setString(6, rental.getCustomerId());
            ps.setString(7, rental.getEquipmentId());
            ps.executeUpdate();
        }
    }

    public void remove(String id) throws SQLException {
        String sql = "DELETE FROM RENTAL WHERE rental_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    public void update(Rental rental) throws SQLException {
        String sql = "UPDATE RENTAL SET notes=?, status=?, returnDate=?, customer_id=?, equipment_id=? " +
                     "WHERE rental_id=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rental.getNotes());
            ps.setString(2, rental.getStatus());
            ps.setDate(3, rental.getReturnDate());
            ps.setString(4, rental.getCustomerId());
            ps.setString(5, rental.getEquipmentId());
            ps.setString(6, rental.getId());
            ps.executeUpdate();
        }
    }

    public void extendReturnDate(String id, int days) throws SQLException {
        String sql = "UPDATE RENTAL SET returnDate = DATEADD('DAY', ?, returnDate) WHERE rental_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, days);
            ps.setString(2, id);
            ps.executeUpdate();
        }
    }

    private Rental mapRow(ResultSet rs) throws SQLException {
        return new Rental(
            rs.getString("rental_id"),
            rs.getString("notes"),
            rs.getString("status"),
            rs.getDate("rentalDate"),
            rs.getDate("returnDate"),
            rs.getString("customer_id"),
            rs.getString("equipment_id")
        );
    }
}