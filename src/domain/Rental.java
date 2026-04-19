package domain;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Rental {
	private String id;
	private String notes;
	private String status;
	
	private LocalDateTime rentalDate;
	private LocalDateTime returnDate;
	
	private Customer customer;
	private HashMap<Equipment, Integer> rentalItems;
	
	// --- Constructor ---
	
	public Rental(String id) {
		this.id = id;
		this.rentalItems = new HashMap<>();
		this.status = "Unconfirmed";
	}
}

