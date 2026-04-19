package application;

import java.sql.SQLException;

import persistence.DatabaseManager;

public class SystemDriver {
	
	public SystemDriver() {
		try {
			DatabaseManager.initDatabase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
