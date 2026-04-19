package controllers;

import java.util.ArrayList;
import java.util.List;

import domain.Customer;
import domain.Equipment;
import domain.Rental;
import java.util.UUID;

public class RentalManager {
	private List<Rental> rentals;
	private int defaultLoanPeriod = 30;
	
	public RentalManager() {
		this.rentals = new ArrayList<>();
	}
	
	public void addRental(Customer customer, String notes) {
		UUID uuid = UUID.randomUUID();
		Rental rental = new Rental(uuid.toString());
		
		rental
		.setCustomer(customer)
		.incrementReturnDate(defaultLoanPeriod)
		.setNotes(notes);
	}
	
	public boolean removeRental(String rentalID) {
		for (Rental rental : rentals) {
			if (rental.getId() == rentalID) {
				rentals.remove(rental);
				return true;
			}
		} 
		return false;
	}
	
	
	public List<Rental> getRentalsById(String rentalID) {
		List<Rental> matchedRentals = new ArrayList<>();
		for (Rental rental : rentals) {
			if (rental.getId().contains(rentalID)) {
				matchedRentals.add(rental);
			}
		}
		return matchedRentals;
	}
	public List<Rental> getRentalsByCustomer(String name) {
		List<Rental> matchedRentals = new ArrayList<>();
		for (Rental rental : rentals) {
			if (
					rental.getCustomer().getFirstName().contains(name.trim()) || 
					rental.getCustomer().getSurname().contains(name.trim())) {
				matchedRentals.add(rental);
			}
		}
		return matchedRentals;
	}
	public List<Rental> getRentalsByEquipment(String name) {
		List<Rental> matchedRentals = new ArrayList<>();
		for (Rental rental : rentals) {
			for (Equipment equipment : rental.getRentalItems().keySet()) {
				if (equipment.getName().contains(name)) {
					matchedRentals.add(rental);
					break;
				}
			}
		}
		return matchedRentals;
	}
}
