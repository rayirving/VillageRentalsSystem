package controllers;


import java.util.ArrayList;
import java.util.Scanner;

import domain.Customer;

public class CustomerManager {
	
	private ArrayList<Customer> customers = new ArrayList<>();
	
	/*
	 * get customer by id
	 * returns null if no customer is found
	 */
	public Customer getCustomer(String id) {
		for (Customer customer : customers) {
			if (customer.getId().equals(id)) {
				return customer;
			}
		}
		System.out.println("Customer not found!");
		return null; 
	}
	
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	
	/*
	 * remove customer by id
	 */
	public void removeCustomer(String id) {
		for (int i = 0; i < customers.size();i ++) {
			if (customers.get(i).getId().equals(id)) {
				customers.remove(i);
				System.out.println("Customer removed.");
				return;
			}
		}
		System.out.println("Customer not found!");
	}
	
	/*
	 * modify customer by id
	 */
	public void modifyCustomer(String id) { // might need to add some error handling
		Customer customer = getCustomer(id);
		
		//print menu
		System.out.println("What would you like to modify?");
		System.out.println("1. First name");
		System.out.println("2. Last name");
		System.out.println("3. Phone number");
		System.out.println("4. Email");
		System.out.println("5. Notes");
		System.out.println("6. Banned Status");
		
		//getting input
		Scanner keyboard;
		keyboard = new Scanner(System.in);
		int choice = Integer.parseInt(keyboard.nextLine());
		
		switch (choice) {
		case 1: 
			System.out.println("Enter new first name: ");
			customer.setFirstName(keyboard.nextLine());
			break;
		case 2:
			System.out.println("Enter new last name: ");
			customer.setSurname(keyboard.nextLine());
			break;
		case 3:
			System.out.println("Enter new phone number: ");
			customer.setPhoneNumber(keyboard.nextLine());
			break;
		case 4:
			System.out.println("Enter new email: ");
			customer.setEmail(keyboard.nextLine());
			break;
		case 5:
			System.out.println("Enter new notes: ");
			customer.setNotes(keyboard.nextLine());
			break;
		case 6:
			System.out.println("1. Banned");
			System.out.println("2. Unbanned");
			int ban_choice = Integer.parseInt(keyboard.nextLine());
			if (ban_choice == 1) {
				customer.setBanned(true);
			}
			else if (ban_choice == 2) {
				customer.setBanned(false);
			}
			break;
		}
	}
}
