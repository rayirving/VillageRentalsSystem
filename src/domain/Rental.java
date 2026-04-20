package domain;

import java.sql.Date;


public class Rental {
	private String id;
	private String notes;
	private String status;
	
	private Date rentalDate;
	private Date returnDate;
	
	private String customerId;
	private String equipmentId;
	
	// --- Constructor ---
	public Rental(String id, String notes, String status, Date rentalDate, Date returnDate,
			String customerId, String equipmentId) {
		super();
		this.id = id;
		this.notes = notes;
		this.status = status;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.customerId = customerId;
		this.equipmentId = equipmentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	@Override
	public String toString() {
		return "[" + id + "]  " + status + 
				" | Customer: " + customerId + 
				" | Equipment: " + equipmentId + 
				" | Rental: " + rentalDate +
				" | Return: " + returnDate +
				" | Notes: " + notes;
	}
	
	
	
}

