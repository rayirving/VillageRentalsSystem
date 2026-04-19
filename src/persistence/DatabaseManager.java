package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseManager {
	private static final String URL = "jdbc:h2:./data/mydb";
	private static final String USER = "sa";
	private static final String PASS = "";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}
	
	public static void initDatabase() throws SQLException {
		
		try (Connection connection = DatabaseManager.getConnection();
			Statement statment = connection.createStatement()) {
			
			// Create CATEGORY table
			statment.execute("""
				CREATE TABLE IF NOT EXISTS CATEGORY (
				category_id		VARCHAR(36) PRIMARY KEY,
				name			VARCHAR(50) UNIQUE NOT NULL
				);
			""");
			
			// Create EQUIPMENT table
			statment.execute("""
				CREATE TABLE IF NOT EXISTS EQUIPMENT (
				equipment_id	VARCHAR(36) 	PRIMARY KEY,
				name			VARCHAR(50)		UNIQUE NOT NULL,
				description		VARCHAR(500),
				dailyRentalCost	DECIMAL(10,2)	NOT NULL,
				category_id		VARCHAR(36)		NOT NULL,
				FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id)
				);
			""");
			
			// Create CUSTOMER table
			statment.execute("""
				CREATE TABLE IF NOT EXISTS CUSTOMER (
				customer_id		VARCHAR(36) PRIMARY KEY,
				firstname		VARCHAR(32) NOT NULL,
				surname			VARCHAR(32) NOT NULL,
				phone			VARCHAR(10),
				email			VARCHAR(50),
				notes			VARCHAR(500),
				is_banned 		VARCHAR(1) CHECK (is_banned IN ('T', 'F'))
				);
				""");
			
			
			// Create RENTAL table
			statment.execute("""
					CREATE TABLE IF NOT EXISTS RENTAL (
					rental_id		VARCHAR(36) PRIMARY KEY,
					notes			VARCHAR(50),
					status			VARCHAR(32) NOT NULL,
					rentalDate		DATE NOT NULL,
					returnDate 		DATE NOT NULL,
					customer_id		VARCHAR(36) NOT NULL,
					equipment_id	VARCHAR(36) NOT NULL,
					FOREIGN KEY (customer_id) 	REFERENCES CUSTOMER(customer_id),
					FOREIGN KEY (equipment_id) 	REFERENCES EQUIPMENT(equipment_id)
					);
				""");
			
			System.out.println("Database initialized.");
			
		}	catch (SQLException e) {
			e.printStackTrace(); 
	    }
	}
}
