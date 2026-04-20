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
                case "1" -> { customerMenu();  break; }
                case "2" -> { categoryMenu();  break; }
                case "3" -> { equipmentMenu(); break; }
                case "4" -> { rentalMenu();    break; }
                case "0" -> { running = false; break; }
                default  -> { System.out.println("Invalid option."); break; }
            }
        }
    }

    // =========================================================
    // CUSTOMER MENU
    // =========================================================

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
                case "1" -> {
                    try {
                        if (!systemManager.getCustomerManager().getAll().isEmpty()) {
                            for (Customer customer : systemManager.getCustomerManager().getAll()) {
                                System.out.println(customer);
                            }
                        } else {
                            System.out.println("No Customers exist!");
                        }
                    } catch (Exception e) {
                        System.out.println("Error loading customers: " + e.getMessage());
                    }
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
                        System.out.println("Error adding customer: " + e.getMessage());
                    }
                }
                case "3" -> {
                    try {
                        editCustomerMenu();
                    } catch (Exception e) {
                        System.out.println("Error editing customer: " + e.getMessage());
                    }
                }
                case "4" -> {
                    try {
                        System.out.println("Enter Customer Id: ");
                        String id = scanner.nextLine().trim();
                        if (systemManager.getCustomerManager().getById(id) == null) {
                            throw new Exception("Customer with ID '" + id + "' not found.");
                        }
                        systemManager.getCustomerManager().remove(id);
                        System.out.println("Customer Removed!");
                    } catch (Exception e) {
                        System.out.println("Error removing customer: " + e.getMessage());
                    }
                }
                case "0" -> running = false;
                default  -> System.out.println("Invalid option.");
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

            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0) {
                running = false;
            } else {
                try {
                    System.out.println("Please enter the id of the customer: ");
                    String id = scanner.nextLine().trim();
                    if (systemManager.getCustomerManager().getById(id) == null) {
                        throw new Exception("Customer with ID '" + id + "' not found.");
                    }
                    switch (choice) {
                        case 1 -> {
                            System.out.println("Enter new First Name: ");
                            String firstName = scanner.nextLine().trim();
                            systemManager.customerManager.update(id, firstName, null, null, null, null, "");
                            System.out.println("First Name updated!");
                        }
                        case 2 -> {
                            System.out.println("Enter new Last Name: ");
                            String lastName = scanner.nextLine().trim();
                            systemManager.customerManager.update(id, null, lastName, null, null, null, "");
                            System.out.println("Last Name updated!");
                        }
                        case 3 -> {
                            System.out.println("Enter new Phone Number: ");
                            String phoneNumber = scanner.nextLine().trim();
                            systemManager.customerManager.update(id, null, null, phoneNumber, null, null, "");
                            System.out.println("Phone Number updated!");
                        }
                        case 4 -> {
                            System.out.println("Enter new Email: ");
                            String email = scanner.nextLine().trim();
                            systemManager.customerManager.update(id, null, null, null, email, null, "");
                            System.out.println("Email updated!");
                        }
                        case 5 -> {
                            System.out.println("Enter new Notes: ");
                            String notes = scanner.nextLine().trim();
                            systemManager.customerManager.update(id, null, null, null, null, notes, "");
                            System.out.println("Notes updated!");
                        }
                        case 6 -> {
                            System.out.println("1. Ban Customer");
                            System.out.println("2. Unban Customer");
                            switch (scanner.nextLine().trim()) {
                                case "1" -> {
                                    systemManager.customerManager.update(id, null, null, null, null, null, "T");
                                    System.out.println("Banned Status updated!");
                                }
                                case "2" -> {
                                    systemManager.customerManager.update(id, null, null, null, null, null, "F");
                                    System.out.println("Banned Status updated!");
                                }
                                default -> System.out.println("Invalid option.");
                            }
                        }
                        default -> System.out.println("Invalid option.");
                    }
                } catch (Exception e) {
                    System.out.println("Error updating customer: " + e.getMessage());
                }
            }
        }
    }

    // =========================================================
    // CATEGORY MENU
    // =========================================================

    private void categoryMenu() throws SQLException {
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
                case "1" -> {
                    try {
                        if (!systemManager.getInventoryManager().getAllCategories().isEmpty()) {
                            
                            for (Category category : systemManager.getInventoryManager().getAllCategories()) {
                            	System.out.println("[" + category.getId() + "] " + category.getName());
                            }
                                
                        } else {
                            System.out.println("No Categories exist!");
                        }
                    } catch (Exception e) {
                        System.out.println("Error loading categories: " + e.getMessage());
                    }
                }
                case "2" -> {
                    try {
                        System.out.println("Enter Category ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.println("Enter Category Name: ");
                        String name = scanner.nextLine().trim();
                        systemManager.getInventoryManager().addCategory(id, name);
                        System.out.println("Category Added!");
                    } catch (Exception e) {
                        System.out.println("Error adding category: " + e.getMessage());
                    }
                }
                case "3" -> {
                    try {
                        System.out.println("Enter Category Id: ");
                        String id = scanner.nextLine().trim();
                        if (systemManager.getInventoryManager().getCategoryById(id) == null) {
                            throw new Exception("Category with ID '" + id + "' not found.");
                        }
                        System.out.println("Enter new Category Name: ");
                        String name = scanner.nextLine().trim();
                        systemManager.getInventoryManager().updateCategory(id, name);
                        System.out.println("Category Updated!");
                    } catch (Exception e) {
                        System.out.println("Error updating category: " + e.getMessage());
                    }
                }
                case "4" -> {
                    try {
                        System.out.println("Enter Category Id: ");
                        String id = scanner.nextLine().trim();
                        if (systemManager.getInventoryManager().getCategoryById(id) == null) {
                            throw new Exception("Category with ID '" + id + "' not found.");
                        }
                        systemManager.getInventoryManager().removeCategory(id);
                        System.out.println("Category Removed!");
                    } catch (Exception e) {
                        System.out.println("Error removing category: " + e.getMessage());
                    }
                }
                case "0" -> running = false;
                default  -> System.out.println("Invalid option.");
            }
        }
    }

    // =========================================================
    // EQUIPMENT MENU
    // =========================================================

    private void equipmentMenu() throws SQLException {
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
                case "1" -> {
                    try {
                        if (!systemManager.getInventoryManager().getInventory().isEmpty()) {
                            systemManager.getInventoryManager().getInventory()
                                .forEach((e, stock) -> System.out.println(e + " | Stock: " + stock));
                        } else {
                            System.out.println("No Equipment exists!");
                        }
                    } catch (Exception e) {
                        System.out.println("Error loading equipment: " + e.getMessage());
                    }
                }
                case "2" -> {
                    try {
                        System.out.println("Enter ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.println("Enter Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.println("Enter Description: ");
                        String description = scanner.nextLine().trim();
                        System.out.println("Enter Daily Rental Cost: ");
                        double dailyRentalCost = Double.parseDouble(scanner.nextLine().trim());
                        System.out.println("Enter Category Id: ");
                        String category = scanner.nextLine().trim();
                        if (systemManager.getInventoryManager().getCategoryById(category) == null) {
                            throw new Exception("Category with ID '" + category + "' not found.");
                        }
                        systemManager.getInventoryManager().addEquipment(id, name, description, dailyRentalCost, category);
                        System.out.println("Equipment Added!");
                    } catch (Exception e) {
                        System.out.println("Error adding equipment: " + e.getMessage());
                    }
                }
                case "3" -> {
                    try {
                        editEquipmentMenu();
                    } catch (Exception e) {
                        System.out.println("Error editing equipment: " + e.getMessage());
                    }
                }
                case "4" -> {
                    try {
                        System.out.println("Enter Equipment Id: ");
                        String id = scanner.nextLine().trim();
                        if (systemManager.getInventoryManager().getEquipmentById(id) == null) {
                            throw new Exception("Equipment with ID '" + id + "' not found.");
                        }
                        systemManager.getInventoryManager().removeEquipment(id);
                        System.out.println("Equipment Removed!");
                    } catch (Exception e) {
                        System.out.println("Error removing equipment: " + e.getMessage());
                    }
                }
                case "0" -> running = false;
                default  -> System.out.println("Invalid option.");
            }
        }
    }

    private void editEquipmentMenu() throws SQLException {
        boolean running = true;
        while (running) {
            System.out.println("\n--- EDIT EQUIPMENT MENU ---");
            System.out.println("1. Name");
            System.out.println("2. Description");
            System.out.println("3. Daily Rental Cost");
            System.out.println("4. Category");
            System.out.println("5. Add Stock");
            System.out.println("6. Remove Stock");
            System.out.println("0. Back");

            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0) {
                running = false;
            } else {
                try {
                    systemManager.getInventoryManager().getInventory()
                        .forEach((e, stock) -> System.out.println(e + " | Stock: " + stock));
                    System.out.println("Enter Equipment ID: ");
                    String id = scanner.nextLine().trim();

                    Equipment equipment = systemManager.getInventoryManager().getEquipmentById(id);
                    if (equipment == null) {
                        throw new Exception("Equipment with ID '" + id + "' not found.");
                    }

                    switch (choice) {
                        case 1 -> {
                            System.out.println("Enter new Name: ");
                            String name = scanner.nextLine().trim();
                            equipment.setName(name);
                            systemManager.getInventoryManager().updateEquipment(equipment);
                            System.out.println("Name updated!");
                        }
                        case 2 -> {
                            System.out.println("Enter new Description: ");
                            String description = scanner.nextLine().trim();
                            equipment.setDescription(description);
                            systemManager.getInventoryManager().updateEquipment(equipment);
                            System.out.println("Description updated!");
                        }
                        case 3 -> {
                            System.out.println("Enter new Daily Rental Cost: ");
                            double cost = Double.parseDouble(scanner.nextLine().trim());
                            equipment.setDailyRentalCost(cost);
                            systemManager.getInventoryManager().updateEquipment(equipment);
                            System.out.println("Daily Rental Cost updated!");
                        }
                        case 4 -> {
                            
                                for (Category category :  systemManager.getInventoryManager().getAllCategories()) {
                                	System.out.println("[" + category.getId() + "] " + category.getName());
                                }
                                
                            System.out.println("Enter new Category ID: ");
                            String categoryId = scanner.nextLine().trim();
                            if (systemManager.getInventoryManager().getCategoryById(categoryId) == null) {
                                throw new Exception("Category with ID '" + categoryId + "' not found.");
                            }
                            equipment.setCategoryId(categoryId);
                            systemManager.getInventoryManager().updateEquipment(equipment);
                            System.out.println("Category updated!");
                        }
                        case 5 -> {
                            System.out.println("Enter amount to add: ");
                            int amount = Integer.parseInt(scanner.nextLine().trim());
                            systemManager.getInventoryManager().addStock(id, amount);
                            System.out.println("Stock added!");
                        }
                        case 6 -> {
                            System.out.println("Enter amount to remove: ");
                            int amount = Integer.parseInt(scanner.nextLine().trim());
                            systemManager.getInventoryManager().removeStock(id, amount);
                            System.out.println("Stock removed!");
                        }
                        default -> System.out.println("Invalid option.");
                    }
                } catch (Exception e) {
                    System.out.println("Error updating equipment: " + e.getMessage());
                }
            }
        }
    }

    // =========================================================
    // RENTAL MENU
    // =========================================================

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
                    try {
                        if (!systemManager.getRentalManager().getAllRentals().isEmpty()) {
                            for (Rental rental : systemManager.getRentalManager().getAllRentals()) {
                                System.out.println(rental);
                            }
                        } else {
                            System.out.println("No Rentals exist!");
                        }
                    } catch (Exception e) {
                        System.out.println("Error loading rentals: " + e.getMessage());
                    }
                    break;
                }
                case "2" -> {
                    try {
                    	
                    	System.out.println("Enter new Rental ID: ");
                    	String id = scanner.nextLine().trim();
                    	
                        if (systemManager.getCustomerManager().getAll().isEmpty()) {
                            throw new Exception("No customers exist. Please add a customer first.");
                        }
                        for (Customer customer : systemManager.getCustomerManager().getAll()) {
                            System.out.println(customer);
                        }
                        System.out.println("Enter Customer ID: ");
                        String customerId = scanner.nextLine().trim();
                        if (systemManager.getCustomerManager().getById(customerId) == null) {
                            throw new Exception("Customer with ID '" + customerId + "' not found.");
                        }

                        if (systemManager.getInventoryManager().getInventory().isEmpty()) {
                            throw new Exception("No equipment exists. Please add equipment first.");
                        }
                        systemManager.getInventoryManager().getInventory()
                            .forEach((e, stock) -> System.out.println(e + " | Stock: " + stock));
                        System.out.println("Enter Equipment ID: ");
                        String equipmentId = scanner.nextLine().trim();
                        if (systemManager.getInventoryManager().getEquipmentById(equipmentId) == null) {
                            throw new Exception("Equipment with ID '" + equipmentId + "' not found.");
                        }

                        System.out.println("Enter Rental Date (YYYY-MM-DD): ");
                        java.sql.Date rentalDate = java.sql.Date.valueOf(scanner.nextLine().trim());
                        System.out.println(rentalDate);

                        System.out.println("Enter Return Date (YYYY-MM-DD): ");
                        java.sql.Date returnDate = java.sql.Date.valueOf(scanner.nextLine().trim());
                        System.out.println(returnDate);

                        System.out.println("Enter Notes: ");
                        String notes = scanner.nextLine().trim();

                        systemManager.getRentalManager().addRental(
                            id, notes, "ACTIVE", rentalDate, returnDate, customerId, equipmentId
                        );
                        System.out.println("Rental Added!");
                    } catch (Exception e) {
                        System.out.println("Error adding rental: " + e.getMessage());
                    }
                    break;
                }
                case "3" -> {
                    try {
                        editRentalMenu();
                    } catch (Exception e) {
                        System.out.println("Error editing rental: " + e.getMessage());
                    }
                    break;
                }
                case "4" -> {
                    try {
                        if (systemManager.getRentalManager().getAllRentals().isEmpty()) {
                            throw new Exception("No rentals exist.");
                        }
                        for (Rental rental : systemManager.getRentalManager().getAllRentals()) {
                            System.out.println(rental);
                        }
                        System.out.println("Enter Rental ID: ");
                        String id = scanner.nextLine().trim();
                        if (systemManager.getRentalManager().getById(id) == null) {
                            throw new Exception("Rental with ID '" + id + "' not found.");
                        }
                        System.out.println("Enter number of days to extend: ");
                        int days = Integer.parseInt(scanner.nextLine().trim());
                        systemManager.getRentalManager().extendReturnDate(id, days);
                        System.out.println("Return date extended by " + days + " day(s)!");
                    } catch (Exception e) {
                        System.out.println("Error extending return date: " + e.getMessage());
                    }
                    break;
                }
                case "5" -> {
                    try {
                        if (systemManager.getRentalManager().getAllRentals().isEmpty()) {
                            throw new Exception("No rentals exist.");
                        }
                        for (Rental rental : systemManager.getRentalManager().getAllRentals()) {
                            System.out.println(rental);
                        }
                        System.out.println("Enter Rental ID: ");
                        String id = scanner.nextLine().trim();
                        if (systemManager.getRentalManager().getById(id) == null) {
                            throw new Exception("Rental with ID '" + id + "' not found.");
                        }
                        systemManager.getRentalManager().removeRental(id);
                        System.out.println("Rental Removed!");
                    } catch (Exception e) {
                        System.out.println("Error removing rental: " + e.getMessage());
                    }
                }
                case "0" -> { running = false; break; }
                default  -> { System.out.println("Invalid option."); break; }
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
                try {
                    if (systemManager.getRentalManager().getAllRentals().isEmpty()) {
                        throw new Exception("No rentals exist.");
                    }
                    for (Rental rental : systemManager.getRentalManager().getAllRentals()) {
                        System.out.println(rental);
                    }
                    System.out.println("Enter Rental ID: ");
                    String id = scanner.nextLine().trim();

                    Rental rental = systemManager.getRentalManager().getById(id);
                    if (rental == null) {
                        throw new Exception("Rental with ID '" + id + "' not found.");
                    }

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
                            if (systemManager.getCustomerManager().getAll().isEmpty()) {
                                throw new Exception("No customers exist.");
                            }
                            for (Customer customer : systemManager.getCustomerManager().getAll()) {
                                System.out.println(customer);
                            }
                            System.out.println("Enter new Customer ID: ");
                            String customerId = scanner.nextLine().trim();
                            if (systemManager.getCustomerManager().getById(customerId) == null) {
                                throw new Exception("Customer with ID '" + customerId + "' not found.");
                            }
                            rental.setCustomerId(customerId);
                            systemManager.getRentalManager().updateRental(rental);
                            System.out.println("Customer updated!");
                        }
                        case 5 -> {
                            if (systemManager.getInventoryManager().getInventory().isEmpty()) {
                                throw new Exception("No equipment exists.");
                            }
                            systemManager.getInventoryManager().getInventory()
                                .forEach((e, stock) -> System.out.println(e + " | Stock: " + stock));
                            System.out.println("Enter new Equipment ID: ");
                            String equipmentId = scanner.nextLine().trim();
                            if (systemManager.getInventoryManager().getEquipmentById(equipmentId) == null) {
                                throw new Exception("Equipment with ID '" + equipmentId + "' not found.");
                            }
                            rental.setEquipmentId(equipmentId);
                            systemManager.getRentalManager().updateRental(rental);
                            System.out.println("Equipment updated!");
                        }
                        default -> System.out.println("Invalid option.");
                    }
                } catch (Exception e) {
                    System.out.println("Error updating rental: " + e.getMessage());
                }
            }
        }
    }
}