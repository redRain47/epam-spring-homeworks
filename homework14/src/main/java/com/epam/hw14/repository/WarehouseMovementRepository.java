package com.epam.hw14.repository;

import com.epam.hw14.model.Warehouse;
import com.epam.hw14.model.WarehouseMovement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WarehouseMovementRepository extends CrudRepository<WarehouseMovement, Long> {
    List<WarehouseMovement> findAllByDateAndWarehouse(LocalDate date, Warehouse warehouse);
}
