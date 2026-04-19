package controllers;

import domain.Equipment;
import persistence.CategoryDAO;
import persistence.EquipmentDAO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class InventoryManager {

    private HashMap<String, String>      categories;
    private HashMap<Equipment, Integer>  equipments;

    private final CategoryDAO  categoryDAO  = new CategoryDAO();
    private final EquipmentDAO equipmentDAO = new EquipmentDAO();

    public InventoryManager() {
        this.equipments = new HashMap<>();
        this.categories = new HashMap<>();
        load();
    }

    private void load() {
        equipments = equipmentDAO.loadAll();
        categories = categoryDAO.loadAll();
    }

    // --- Getters ---

    public HashMap<Equipment, Integer> getAllEquipment()  { return equipments; }
    public HashMap<String, String>     getAllCategories() { return categories; }

    public Equipment getEquipmentById(String id) {
        for (Equipment equipment : equipments.keySet()) {
            if (equipment.getId().equals(id)) return equipment;
        }
        return null;
    }

    public String getCategoryById(String id) {
        return categories.getOrDefault(id, null);
    }

    public Integer getStockById(String id) {
        for (Equipment equipment : equipments.keySet()) {
            if (equipment.getId().equals(id)) return equipments.get(equipment);
        }
        return null;
    }

    // --- Equipment ---

    public void addEquipment(String name, String desc,
                             double dailyRentalCost, String categoryId) throws SQLException {
        Equipment equipment = new Equipment(
            UUID.randomUUID().toString(), name, desc, dailyRentalCost, categoryId
        );
        equipmentDAO.add(equipment);
        load();
    }

    public void removeEquipment(String id) throws SQLException {
        equipmentDAO.remove(id);
        load();
    }

    public void updateEquipment(Equipment equipment) throws SQLException {
        equipmentDAO.update(equipment);
        load();
    }

    // --- Stock ---

    public void addStock(String id, int amount) throws SQLException {
        equipmentDAO.addStock(id, amount);
        load();
    }

    public void removeStock(String id, int amount) throws SQLException {
        equipmentDAO.removeStock(id, amount);
        load();
    }

    // --- Categories ---

    public void addCategory(String name) throws SQLException {
        categoryDAO.add(UUID.randomUUID().toString(), name);
        load();
    }

    public void removeCategory(String id) throws SQLException {
        categoryDAO.remove(id);
        load();
    }

    public void updateCategory(String id, String name) throws SQLException {
        categoryDAO.update(id, name);
        load();
    }
}