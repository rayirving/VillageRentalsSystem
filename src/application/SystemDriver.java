package application;

import java.sql.SQLException;

import presentation.UIManager;

import persistence.DatabaseManager;

public class SystemDriver {
	
	
	public SystemDriver() {
		try {
			DatabaseManager.initDatabase();
			UIManager userInterface = new UIManager();
			userInterface.start();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
