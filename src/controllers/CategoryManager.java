package controllers;

import java.util.ArrayList;

import domain.Category;

public class CategoryManager {
	private ArrayList <Category> categorys = new ArrayList <>();
	
	public Category getCategoryById (String id) {
		for (Category category : categorys) {
			if (category.getId().equals(id)) {
				return category;
			}
		}
		return null;
	}
	
	public void addCategory(String id, String name) {
		Category category = new Category(id, name);
		categorys.add(category);
		System.out.println("Category added.");
	}
	
	public void removeCategory(String id) {
		for (int i = 0; i < categorys.size(); i++) {
			if (categorys.get(i).getId().equals(id)) {
				categorys.remove(i);
				System.out.println("Category removed.");
			}
		}
	}
	
	public boolean isValidCategory(String name) {
		for (int i = 0; i < categorys.size(); i++) {
			if (categorys.get(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
