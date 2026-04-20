package controllers;


import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

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
	
	public void add(
			String id,
			String firstName, 
			String surname,
			String phoneNumber,
			String email
			) throws SQLException {
		Customer customer = new Customer(
			id, 
			firstName, 
			surname, 
			phoneNumber, 
			email, 
			"", 
			"F");
		
		customerDAO.add(customer);
		load();
	}
	
	public void update(
		String id,
		String firstname,
		String surname,
		String phonenumber,
		String email,
		String notes,
		String isBanned
			) throws SQLException {
		
		Customer customer = getById(id);
		
		if (customer != null) {
			if (firstname != "" || firstname != null) {customer.setFirstName(firstname);};
			if (surname != "" || surname != null) {customer.setSurname(surname);};
			if (phonenumber != "" || phonenumber != null) {customer.setPhoneNumber(phonenumber);}
			if (email != "" || email != null) {customer.setEmail(email);};
			if (notes != "" || notes != null) {customer.setNotes(notes);};
			if (isBanned.toUpperCase() == "T" || isBanned.toUpperCase() == "F") {customer.setBanned(isBanned.toUpperCase());};
		}
	
		customerDAO.update(customer);
		load();
	}
	
	public void remove(String id) throws SQLException {
		customerDAO.remove(id);
		load();
	}
	

}
