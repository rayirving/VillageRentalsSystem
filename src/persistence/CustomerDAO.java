package persistence;

import domain.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public void add(Customer customer) throws SQLException {
        String sql = "INSERT INTO CUSTOMER (customer_id, firstname, surname, phone, email, notes, is_banned) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	ps.setString(1, customer.getId());
        	ps.setString(2, customer.getFirstName());
        	ps.setString(3, customer.getSurname());
        	ps.setString(4, customer.getPhoneNumber());
        	ps.setString(5, customer.getEmail());
        	ps.setString(6, customer.getNotes());
        	ps.setString(7, customer.isBanned());
        	ps.executeUpdate();
        }
    }

    public void update(Customer customer) throws SQLException {
        String sql = "UPDATE CUSTOMER SET firstname=?, surname=?, phone=?, email=?, notes=?, is_banned=? " +
                     "WHERE customer_id=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	ps.setString(1, customer.getFirstName());
        	ps.setString(2, customer.getSurname());
        	ps.setString(3, customer.getPhoneNumber());
        	ps.setString(4, customer.getEmail());
        	ps.setString(5, customer.getNotes());
        	ps.setString(6, customer.isBanned());
        	ps.setString(7, customer.getId());
        	ps.executeUpdate();
        }
    }

    public void remove(String customerId) throws SQLException {
        String sql = "DELETE FROM CUSTOMER WHERE customer_id=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	ps.setString(1, customerId);
        	ps.executeUpdate();
        }
    }

    public List<Customer> loadAll() throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM CUSTOMER";
        try (Connection conn = DatabaseManager.getConnection();
             Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery(sql)) {
        	while (resultSet.next()) {
        		customerList.add(createCustomer(resultSet));
        	}
        }
        return customerList;
    }

    // Converts a database row into a Customer object
    private Customer createCustomer(ResultSet rs) throws SQLException {
        return new Customer(
        	rs.getString("customer_id"),
        	rs.getString("firstname"),
        	rs.getString("surname"),
        	rs.getString("phone"),
        	rs.getString("email"),
        	rs.getString("notes"),
        	rs.getString("is_banned")
        );
    }
}