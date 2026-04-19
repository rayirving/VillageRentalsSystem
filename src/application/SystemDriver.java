package application;

import java.sql.SQLException;

import controllers.CustomerManager;
import controllers.InventoryManager;
import controllers.RentalManager;
import presentation.UIManager;

import persistence.DatabaseManager;

public class SystemDriver {
	
	
	public SystemDriver() {
		try {
			DatabaseManager.initDatabase();
			
			CustomerManager customerManager = new CustomerManager();
			InventoryManager inventoryManager = new InventoryManager();
			RentalManager rentalManager = new RentalManager();
			
			
			UIManager userInterface = new UIManager();
			userInterface.start();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
