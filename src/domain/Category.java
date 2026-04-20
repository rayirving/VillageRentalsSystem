package domain;

public class Category {
	private String id;
	private String name;
	
	// constructor
	public Category(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	// getters and setters
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}


	// to string
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
	
}
