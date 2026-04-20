package presentation;

import domain.*;
import persistence.*;

import java.sql.SQLException;
import java.util.Scanner;
import application.*;

public class UIManager {

	private SystemDriver systemManager;

    private final Scanner scanner = new Scanner(System.in);
    private final CustomerDAO  customerDAO  = new CustomerDAO();
    private final CategoryDAO  categoryDAO  = new CategoryDAO();
    private final EquipmentDAO equipmentDAO = new EquipmentDAO();
    private final RentalDAO    rentalDAO    = new RentalDAO();

    public void start(SystemDriver systemManager) throws SQLException {
    	this.systemManager = systemManager;
        boolean running = true;
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Customers");
            System.out.println("2. Categories");
            System.out.println("3. Equipment");
            System.out.println("4. Rentals");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> {
                	customerMenu();
                	break;
                }
                case "2" -> {
                	categoryMenu();
                	break;
                }
                case "3" -> {
                	equipmentMenu();
                	break;
                }
                case "4" -> {
                	rentalMenu();
                	break;
                }
                case "0" -> {
                	running = false;
                	break;
                }
                default  -> {
                	System.out.println("Invalid option.");
                	break;
                }
            }
        }
    }

    // CUSTOMER MENU
    private void customerMenu() throws SQLException {
        boolean running = true;
        while (running) {
            System.out.println("\n--- CUSTOMER MENU ---");
            System.out.println("1. View all customers");
            System.out.println("2. Add customer");
            System.out.println("3. Edit customer");
            System.out.println("4. Remove customer");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> { for (Customer customer : systemManager.getCustomerManager().getAll()) {
                	System.out.println(customer);}
                }
                case "2" -> {
                	try {
                		System.out.println("Enter ID: ");
	                	String id = scanner.nextLine().trim();
	                	System.out.println("Enter First Name: ");
	                	String firstName = scanner.nextLine().trim();
	                	System.out.println("Enter Surname: ");
	                	String surname = scanner.nextLine().trim();
	                	System.out.println("Enter Email: ");
	                	String email = scanner.nextLine().trim();
	                	System.out.println("Enter Phone Number: ");
	                	String phone = scanner.nextLine().trim();
	                	
	                	systemManager.getCustomerManager().add(id, firstName, surname, phone, email);
	                	System.out.println("Customer Added!");
                	} catch (Exception e) {
                		System.out.println(e.getMessage());
                	}
                }
                case "3" -> {editCustomerMenu();}
                case "4" -> { 
                	System.out.println("Enter Customer Id: ");
                	String id =  scanner.nextLine().trim();
                	systemManager.getCustomerManager().remove(id);
                }
                case "0" -> running = false;
                default  -> System.out.println("Invalid option.");
            }
        }
    }

    // CATEGORY MENU
    private void categoryMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- CATEGORY MENU ---");
            System.out.println("1. View all categories");
            System.out.println("2. Add category");
            System.out.println("3. Edit category");
            System.out.println("4. Remove category");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> { /* TODO: view all categories */ }
                case "2" -> { /* TODO: add category */ }
                case "3" -> { /* TODO: edit category */ }
                case "4" -> { /* TODO: remove category */ }
                case "0" -> running = false;
                default  -> System.out.println("Invalid option.");
            }
        }
    }

    // EQUIPMENT MENU
    private void equipmentMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- EQUIPMENT MENU ---");
            System.out.println("1. View all equipment");
            System.out.println("2. Add equipment");
            System.out.println("3. Edit equipment");
            System.out.println("4. Remove equipment");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> { /* TODO: view all equipment */ }
                case "2" -> { /* TODO: add equipment */ }
                case "3" -> { /* TODO: edit equipment */ }
                case "4" -> { /* TODO: remove equipment */ }
                case "0" -> running = false;
                default  -> System.out.println("Invalid option.");
            }
        }
    }

    // RENTAL MENU
    private void rentalMenu() throws SQLException {
        boolean running = true;
        while (running) {
            System.out.println("\n--- RENTAL MENU ---");
            System.out.println("1. View all rentals");
            System.out.println("2. Add rental");
            System.out.println("3. Edit rental");
            System.out.println("4. Extend return date");
            System.out.println("5. Remove rental");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> {
                    for (Rental rental : systemManager.getRentalManager().getAllRentals()) {
                        System.out.println(rental);
                    }
                    break;
                }
                case "2" -> {
                    try {
                        for (Customer customer : systemManager.getCustomerManager().getAll()) {
                            System.out.println(customer);
                        }
                        System.out.println("Enter Customer ID: ");
                        String customerId = scanner.nextLine().trim();

                        systemManager.getInventoryManager().getAllEquipment()
                            .forEach((e, stock) -> System.out.println(e + " | Stock: " + stock));
                        System.out.println("Enter Equipment ID: ");
                        String equipmentId = scanner.nextLine().trim();

                        System.out.println("Enter Status (e.g. ACTIVE, RETURNED): ");
                        String status = scanner.nextLine().trim();

                        System.out.println("Enter Rental Date (YYYY-MM-DD): ");
                        java.sql.Date rentalDate = java.sql.Date.valueOf(scanner.nextLine().trim());

                        System.out.println(rentalDate);
                        
                        System.out.println("Enter Return Date (YYYY-MM-DD): ");
                        java.sql.Date returnDate = java.sql.Date.valueOf(scanner.nextLine().trim());

                        System.out.println(returnDate);
                        
                        System.out.println("Enter Notes: ");
                        String notes = scanner.nextLine().trim();

                        systemManager.getRentalManager().addRental(
                            notes, status, rentalDate, returnDate, customerId, equipmentId
                        );
                        System.out.println("Rental Added!");
                    } catch (Exception e) {
                        System.out.println("Invalid Rental Entered");
                    }
                    break;
                }
                case "3" -> { 
                	editRentalMenu(); 
                	break;
                	}
                case "4" -> {
                    try {
                        for (Rental rental : systemManager.getRentalManager().getAllRentals()) {
                            System.out.println(rental);
                        }
                        System.out.println("Enter Rental ID: ");
                        String id = scanner.nextLine().trim();

                        System.out.println("Enter number of days to extend: ");
                        int days = Integer.parseInt(scanner.nextLine().trim());

                        systemManager.getRentalManager().extendReturnDate(id, days);
                        System.out.println("Return date extended by " + days + " day(s)!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "5" -> {
                    for (Rental rental : systemManager.getRentalManager().getAllRentals()) {
                        System.out.println(rental);
                    }
                    System.out.println("Enter Rental ID: ");
                    String id = scanner.nextLine().trim();
                    systemManager.getRentalManager().removeRental(id);
                    System.out.println("Rental Removed!");
                }
                case "0" -> {
                	running = false;
                	break;
                	}
                default  -> {
                	System.out.println("Invalid option.");
                	break;
                }
                
            }
        }
    }

    private void editRentalMenu() throws SQLException {
        boolean running = true;
        while (running) {
            System.out.println("\n--- EDIT RENTAL MENU ---");
            System.out.println("1. Status");
            System.out.println("2. Return Date");
            System.out.println("3. Notes");
            System.out.println("4. Customer");
            System.out.println("5. Equipment");
            System.out.println("0. Back");

            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0) {
                running = false;
            } else {
                for (Rental rental : systemManager.getRentalManager().getAllRentals()) {
                    System.out.println(rental);
                }
                System.out.println("Enter Rental ID: ");
                String id = scanner.nextLine().trim();

                Rental rental = systemManager.getRentalManager().getById(id);
                if (rental == null) { System.out.println("Rental not found."); continue; }

                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter new Status (e.g. ACTIVE, RETURNED): ");
                        String status = scanner.nextLine().trim();
                        systemManager.getRentalManager().setStatus(id, status);
                        System.out.println("Status updated!");
                    }
                    case 2 -> {
                        System.out.println("Enter new Return Date (YYYY-MM-DD): ");
                        java.sql.Date returnDate = java.sql.Date.valueOf(scanner.nextLine().trim());
                        systemManager.getRentalManager().setReturnDate(id, returnDate);
                        System.out.println("Return Date updated!");
                    }
                    case 3 -> {
                        System.out.println("Enter new Notes: ");
                        String notes = scanner.nextLine().trim();
                        rental.setNotes(notes);
                        systemManager.getRentalManager().updateRental(rental);
                        System.out.println("Notes updated!");
                    }
                    case 4 -> {
                        for (Customer customer : systemManager.getCustomerManager().getAll()) {
                            System.out.println(customer);
                        }
                        System.out.println("Enter new Customer ID: ");
                        String customerId = scanner.nextLine().trim();
                        rental.setCustomerId(customerId);
                        systemManager.getRentalManager().updateRental(rental);
                        System.out.println("Customer updated!");
                    }
                    case 5 -> {
                        systemManager.getInventoryManager().getAllEquipment()
                            .forEach((e, stock) -> System.out.println(e + " | Stock: " + stock));
                        System.out.println("Enter new Equipment ID: ");
                        String equipmentId = scanner.nextLine().trim();
                        rental.setEquipmentId(equipmentId);
                        systemManager.getRentalManager().updateRental(rental);
                        System.out.println("Equipment updated!");
                    }
                    default -> System.out.println("Invalid option.");
                }
            }
        }
    }
    
    private void editCustomerMenu() throws SQLException {
    	boolean running = true;
    	while (running) {
    		System.out.println("\n--- EDIT CUSTOMER MENU ---");
    		System.out.println("1. First name");
    		System.out.println("2. Last name");
    		System.out.println("3. Phone number");
    		System.out.println("4. Email");
    		System.out.println("5. Notes");
    		System.out.println("6. Banned Status");
    		System.out.println("0. Back");
    		
    		int choice = Integer.parseInt(scanner.nextLine());
    		if (choice == 0) {
    			running = false;
    		}
    		else {
    			System.out.println("Please enter the id of the customer: ");
    			String id = scanner.nextLine();
    			switch (choice) {
    			case 1 -> {
    				System.out.println("Enter new First Name: ");
    				String firstName = scanner.nextLine().trim();
    				systemManager.customerManager.update(id, firstName, "",  "", "", "", "");
    				System.out.println("First Name updated!");
    			}
    			case 2 -> {
    				System.out.println("Enter new Last Name: ");
    				String lastName = scanner.nextLine().trim();
    				systemManager.customerManager.update(id, "", lastName,  "", "", "", "");
    				System.out.println("Last Name updated!");
    			}
    			case 3 -> {
    				System.out.println("Enter new Phone Number: ");
    				String phoneNumber = scanner.nextLine().trim();
    				systemManager.customerManager.update(id, "", "",  phoneNumber, "", "", "");
    				System.out.println("Phone Number updated!");
    			}
    			case 4 -> {
    				System.out.println("Enter new Email: ");
    				String email = scanner.nextLine().trim();
    				systemManager.customerManager.update(id, "", "",  "", email, "", "");
    				System.out.println("Email updated!");
    			}
    			case 5 -> {
    				System.out.println("Enter new Notes: ");
    				String notes = scanner.nextLine().trim();
    				systemManager.customerManager.update(id, "", "",  "", "", notes, "");
    				System.out.println("Notes updated!");
    			}
    			case 6 -> {
    				System.out.println("1. Ban Customer");
    				System.out.println("2. Unban Customer");
    				switch (scanner.nextLine().trim()) {
    				case "1" -> {
    					systemManager.customerManager.update(id, "", "",  "", "", "", "T");
    					System.out.println("Banned Status updated!");}
    				case "2" -> {
    					systemManager.customerManager.update(id, "", "",  "", "", "", "F");
    					System.out.println("Banned Status updated!");}
    				default -> {System.out.println("Invalid option.");}
    				}
    			}
    			default -> { System.out.println("Invalid option.");}
    			}
    			
    		}
    	}
    }
}