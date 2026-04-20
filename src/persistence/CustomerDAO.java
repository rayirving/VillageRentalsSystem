package persistence;

import domain.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public void add(Customer customer) throws SQLException {
        String sql = "INSERT INTO CUSTOMER (customer_id, firstname, surname, phone, email, notes, is_banned) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        	preparedStatement.setString(1, customer.getId());
        	preparedStatement.setString(2, customer.getFirstName());
        	preparedStatement.setString(3, customer.getSurname());
        	preparedStatement.setString(4, customer.getPhoneNumber());
        	preparedStatement.setString(5, customer.getEmail());
        	preparedStatement.setString(6, customer.getNotes());
        	preparedStatement.setString(7, customer.isBanned());
        	preparedStatement.executeUpdate();
        }
    }

    public void update(Customer customer) throws SQLException {
        String sql = "UPDATE CUSTOMER SET firstname=?, surname=?, phone=?, email=?, notes=?, is_banned=? " +
                     "WHERE customer_id=?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        	preparedStatement.setString(1, customer.getFirstName());
        	preparedStatement.setString(2, customer.getSurname());
        	preparedStatement.setString(3, customer.getPhoneNumber());
        	preparedStatement.setString(4, customer.getEmail());
        	preparedStatement.setString(5, customer.getNotes());
        	preparedStatement.setString(6, customer.isBanned());
        	preparedStatement.setString(7, customer.getId());
        	preparedStatement.executeUpdate();
        }
    }

    public void remove(String customerId) throws SQLException {
        String sql = "DELETE FROM CUSTOMER WHERE customer_id=?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        	preparedStatement.setString(1, customerId);
        	preparedStatement.executeUpdate();
        }
    }

    public List<Customer> loadAll() throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM CUSTOMER";
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
        	while (resultSet.next()) {
        		customerList.add(createCustomer(resultSet));
        	}
        }
        return customerList;
    }

    // Converts a database row into a Customer object
    private Customer createCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(
        	resultSet.getString("customer_id"),
        	resultSet.getString("firstname"),
        	resultSet.getString("surname"),
        	resultSet.getString("phone"),
        	resultSet.getString("email"),
        	resultSet.getString("notes"),
        	resultSet.getString("is_banned")
        );
    }
}