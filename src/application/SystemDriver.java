package application;

import java.sql.SQLException;

import controllers.CustomerManager;
import controllers.InventoryManager;
import controllers.RentalManager;
import presentation.UIManager;

import persistence.DatabaseManager;

public class SystemDriver {
	
	public CustomerManager customerManager;
	InventoryManager inventoryManager;
	RentalManager rentalManager;
	
	
	public SystemDriver() {
		try {
			DatabaseManager.initDatabase();
			
			customerManager = new CustomerManager();
			inventoryManager = new InventoryManager();
			rentalManager = new RentalManager();
			
			
			UIManager userInterface = new UIManager();
			userInterface.start(this);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CustomerManager getCustomerManager() {
		return this.customerManager;
	}
	
	
	
}
