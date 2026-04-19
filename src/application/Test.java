package application;

import java.util.Scanner;

import controllers.CustomerManager;
import controllers.InventoryManager;
import domain.Customer;

public class Test {


	
	public static void main(String[] args) {
		CustomerManager cm = new CustomerManager();
		InventoryManager im = new InventoryManager();

			
		System.out.println("hello");
		// pre made data
		Customer bob = new Customer("1", "Sponge", "Bob", "12345", "spongebob@mail", "", false);
		cm.addCustomer(bob);
		
		System.out.println(cm.getCustomer("1"));
		System.out.println(cm.getCustomer("2"));
		
		Customer scooby = new Customer("2", "Scooby", "doo", "654654546", "scooblymail", "scobydobydo", false);
		cm.addCustomer(scooby);
		
		System.out.println(cm.getCustomer("2"));
		
		cm.removeCustomer("1");
		System.out.println(cm.getCustomer("1"));
		
		//cm.modifyCustomer("2");
		//System.out.println(cm.getCustomer("2"));
		
		System.out.println("\n-- Equipment Test --");
		
		im.addEquipment("1", "kitchen", "Pan", "yummy", 10.5f, 5);
		im.addEquipment("2", "recreation", "Baseball Bat", "fdsafd", 5.0f, 3);
		im.addEquipment("3", "Box", "Box", "Box", 5.0f, 4);
		System.out.println(im.getEquipment("2"));
		im.removeEquipment("2");
		System.out.println(im.getEquipment("2"));
		System.out.println(im.getStock("3"));
		
		
		
		


	}
	
	static void customerManagerMenu()
	{
		System.out.println("1. Find customer");
		System.out.println("2. Add customer");
		System.out.println("3. Remove customer");
		System.out.println("4. Modify customer");
		System.out.println("5. Exit");
	}
}
