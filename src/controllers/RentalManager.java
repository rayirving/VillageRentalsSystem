package controllers;

import domain.Rental;
import persistence.RentalDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class RentalManager {

    private List<Rental>   rentals;
    private final RentalDAO rentalDAO = new RentalDAO();

    public RentalManager() {
        load();
    }

    private void load() {
        rentals = rentalDAO.loadAll();
    }

    // --- Getters ---

    public List<Rental> getAllRentals() { return rentals; }

    public Rental getById(String id) {
        return rentals.stream()
                      .filter(r -> r.getId().equals(id))
                      .findFirst()
                      .orElse(null);
    }

    public List<Rental> getByCustomerId(String customerId) {
        return rentals.stream()
                      .filter(r -> r.getCustomerId().equals(customerId))
                      .toList();
    }

    public List<Rental> getByEquipmentId(String equipmentId) {
        return rentals.stream()
                      .filter(r -> r.getEquipmentId().equals(equipmentId))
                      .toList();
    }

    public List<Rental> getByStatus(String status) {
        return rentals.stream()
                      .filter(r -> r.getStatus().equalsIgnoreCase(status))
                      .toList();
    }

    // --- Add / Remove ---

    public void addRental(String id, String notes, String status,
                          Date rentalDate, Date returnDate,
                          String customerId, String equipmentId) throws SQLException {
        Rental rental = new Rental(
            id, notes, status,
            rentalDate, returnDate, customerId, equipmentId
        );
        rentalDAO.add(rental);
        load();
    }

    public void removeRental(String id) throws SQLException {
        rentalDAO.remove(id);
        load();
    }

    // --- Edit ---

    public void updateRental(Rental rental) throws SQLException {
        rentalDAO.update(rental);
        load();
    }

    public void extendReturnDate(String id, int days) throws SQLException {
        rentalDAO.extendReturnDate(id, days);
        load();
    }

    public void setReturnDate(String id, Date newReturnDate) throws SQLException {
        Rental rental = getById(id);
        if (rental == null) { System.out.println("Rental not found."); return; }
        rental.setReturnDate(newReturnDate);
        rentalDAO.update(rental);
        load();
    }

    public void setStatus(String id, String status) throws SQLException {
        Rental rental = getById(id);
        if (rental == null) { System.out.println("Rental not found."); return; }
        rental.setStatus(status);
        rentalDAO.update(rental);
        load();
    }
}