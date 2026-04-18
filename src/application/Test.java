package application;

import java.util.Scanner;

import controllers.CustomerManager;
import domain.Customer;

public class Test {


	
	public static void main(String[] args) {
		CustomerManager cm = new CustomerManager();
//		Scanner keyboard = new Scanner(System.in);
//		int choice = Integer.parseInt(keyboard.nextLine());
		int option = 0;
			
		String id;
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
		
		cm.modifyCustomer("2");
		System.out.println(cm.getCustomer("2"));
		

//		while (option != 5) {
//			customerManagerMenu();
//			
//			switch(option) {
//			case 1:
//				System.out.println("Enter id: ");
//				id = keyboard.nextLine();
//				System.out.println(cm.getCustomer(id).toString());
//				break;
//			case 2:
//				System.out.println("i too lazy to do this rn lol");
//				break;
//			case 3:
//				System.out.println("Enter id: ");
//				id = keyboard.nextLine();
//				cm.removeCustomer(id);
//				break;
//			case 4:
//				System.out.println("Enter id: ");
//				id = keyboard.nextLine();
//				cm.modifyCustomer(id);
//				break;
//			}
//		}
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
