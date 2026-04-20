package controllers;


import java.sql.SQLException;
import java.util.List;
import domain.Customer;
import persistence.CustomerDAO;

public class CustomerManager {
	
	private List<Customer> customers;
	private final CustomerDAO customerDAO = new CustomerDAO();
	
	public CustomerManager() throws SQLException {
		load();
	}
	
	private void load() throws SQLException {
		customers = customerDAO.loadAll();
	}
	
	public List<Customer> getAll() {
		return customers;
	}
	
	public Customer getById(String id) {
		for (Customer customer : customers) {
			if (customer.getId().equals(id)) {
				return customer;
			}
		}
		return null;
	}
	
	public void add(Customer customer) throws SQLException {
		customerDAO.add(customer);
		load();
	}
	
	public void update(Customer customer) throws SQLException {
		customerDAO.update(customer);
		load();
	}
	
	public void remove(String id) throws SQLException {
		customerDAO.remove(id);
		load();
	}
	

}
