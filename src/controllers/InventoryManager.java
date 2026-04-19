package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import domain.Equipment;

public class InventoryManager {
	private ArrayList<Equipment> equipments = new ArrayList<>();
	private HashMap<Equipment, Integer> inventory = new HashMap<>();
	
	public void addEquipment(String id, String category, String name, String desc, float dailyRentalCost, int stock) {
		Equipment equipment = new Equipment(id, name, desc, dailyRentalCost, category);
		equipments.add(equipment);
		inventory.put(equipment, stock);
	}
	
	public void removeEquipment(String id) {
		for (int i = 0; i < equipments.size(); i++) {
			if (equipments.get(i).getId().equals(id)) {
				equipments.remove(i);
				inventory.remove(i); // not sure if this is removed correctly
				System.out.println("Equipment removed.");
				return;
			}
		}
		System.out.println("Equipment not found!");
	}
	
	public Equipment getEquipment(String id) {
		for (Equipment equipment : equipments) {
			if (equipment.getId().equals(id)) {
				return equipment;
			}
		}
		System.out.println("Equipment not found!");
		return null;
	}
	
	public void editEquipment(String id) {
		Equipment equipment = getEquipment(id);
		
		System.out.println("What would you like to edit?");
		System.out.println("1. Name");
		System.out.println("2. Description");
		System.out.println("3. Daily rental cost");
		System.out.println("4. Category");

		Scanner keyboard;
		keyboard = new Scanner(System.in);
		int choice = Integer.parseInt(keyboard.nextLine());
		
		switch(choice) {
		case 1:
			System.out.println("Enter new name: ");
			equipment.setName(keyboard.nextLine());
			break;
		case 2:
			System.out.println("Enter new description: ");
			equipment.setDesc(keyboard.nextLine());
		case 3:
			System.out.println("Enter new daily rental cost:");
			equipment.setDailyRentalCost(Integer.parseInt(keyboard.nextLine()));
		case 4:
			System.out.println("Enter new category: ");
			equipment.setCategory(keyboard.nextLine());
		}

	}
	
	public boolean isValidEquipment(String name) {
		for (Equipment equipment : equipments) {
			if (equipment.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void addStock(String id) {
		
	}
	
	public void removeStock(String id) {
		
	}
	
	public int getStock(String id) {
		Equipment equipment = getEquipment(id);
		return inventory.get(equipment);
	}
	
	public boolean isValidStock(String name) {
		return false;
	}
	

}
