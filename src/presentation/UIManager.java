package presentation;

import domain.*;
import persistence.*;
import java.util.Scanner;

public class UIManager {

    private final Scanner scanner = new Scanner(System.in);
    private final CustomerDAO  customerDAO  = new CustomerDAO();
    private final CategoryDAO  categoryDAO  = new CategoryDAO();
    private final EquipmentDAO equipmentDAO = new EquipmentDAO();
    private final RentalDAO    rentalDAO    = new RentalDAO();

    public void start() {
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
                case "1" -> customerMenu();
                case "2" -> categoryMenu();
                case "3" -> equipmentMenu();
                case "4" -> rentalMenu();
                case "0" -> running = false;
                default  -> System.out.println("Invalid option.");
            }
        }
    }

    // CUSTOMER MENU
    private void customerMenu() {
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
                case "1" -> { /* TODO: view all customers */ }
                case "2" -> { /* TODO: add customer */ }
                case "3" -> { /* TODO: edit customer */ }
                case "4" -> { /* TODO: remove customer */ }
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
    private void rentalMenu() {
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
                case "1" -> { /* TODO: view all rentals */ }
                case "2" -> { /* TODO: add rental */ }
                case "3" -> { /* TODO: edit rental */ }
                case "4" -> { /* TODO: extend return date */ }
                case "5" -> { /* TODO: remove rental */ }
                case "0" -> running = false;
                default  -> System.out.println("Invalid option.");
            }
        }
    }
}