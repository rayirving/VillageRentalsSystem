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
	
	public Equipment(String id, String name, String desc, float dailyRentalCost, String category) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.dailyRentalCost = dailyRentalCost;
		this.category = category;
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

	
	@Override
	public String toString() {
		return "Equipment [id=" + id + ", name=" + name + ", desc=" + desc + ", dailyRentalCost=" + dailyRentalCost
				+ ", category=" + category + "]";
	}
	
}
