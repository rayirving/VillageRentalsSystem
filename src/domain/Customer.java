package domain;

public class Customer {
	private String id;
	private String firstName;
	private String surname;
	
	private String phoneNumber;
	private String email; 
	
	private String notes;
	boolean isBanned = false;
	
	
	// --- Constructor ---
	
	public Customer(String id) {
		this.id = id;
	}
	
	
	public Customer(String id, String firstName, String surname, String phoneNumber, String email, String notes,
			boolean isBanned) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.notes = notes;
		this.isBanned = isBanned;
	}


	// --- Getters & Setters ---

	public String getId() {return id;}

	public String getFirstName() {return firstName;}
	public Customer setFirstName(String firstName) {this.firstName = firstName; return this;}

	public String getSurname() {return surname;}
	public Customer setSurname(String surname) {this.surname = surname; return this;}

	public String getPhoneNumber() {return phoneNumber;}
	public Customer setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber; return this;}

	public String getEmail() {return email;}
	public Customer setEmail(String email) {this.email = email; return this;}

	public String getNotes() {return notes;}
	public void setNotes(String notes) {this.notes = notes;}

	public boolean isBanned() {return isBanned;}
	public void setBanned(boolean isBanned) {this.isBanned = isBanned;}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", surname=" + surname + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", notes=" + notes + ", isBanned=" + isBanned + "]";
	}
	
	
	
}
