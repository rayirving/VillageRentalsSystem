package domain;

public class Equipment {
	private String id;
	private String name;
	private String desc;
	
	private float dailyRentalCost;
	private String category;
	
	// --- Constructor ---
	
	public Equipment(String id) {
		this.id = id;
	}
	
	// --- Getters & Setters ---

	public String getId() {return id;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getDesc() {return desc;}
	public void setDesc(String desc) {this.desc = desc;}

	public float getDailyRentalCost() {return dailyRentalCost;}
	public void setDailyRentalCost(float dailyRentalCost) {this.dailyRentalCost = dailyRentalCost;}

	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}
	
}
