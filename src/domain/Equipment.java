package domain;

public class Equipment {
    private String id;
    private String name;
    private String description;
    private double dailyRentalCost;
    private String categoryId;

    public Equipment(String id, String name, String description,
                     double dailyRentalCost, String categoryId) {
        this.id              = id;
        this.name            = name;
        this.description     = description;
        this.dailyRentalCost = dailyRentalCost;
        this.categoryId      = categoryId;
    }

    public String getId()               { return id; }
    public String getName()             { return name; }
    public String getDescription()      { return description; }
    public double getDailyRentalCost()  { return dailyRentalCost; }
    public String getCategoryId()       { return categoryId; }

    public void setName(String name)                   { this.name = name; }
    public void setDescription(String description)     { this.description = description; }
    public void setDailyRentalCost(double cost)        { this.dailyRentalCost = cost; }
    public void setCategoryId(String categoryId)       { this.categoryId = categoryId; }

    @Override
    public String toString() {
        return "[" + id + "] " + name +
               " | $" + dailyRentalCost + "/day" +
               " | Category: " + categoryId;
    }

}