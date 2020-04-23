package com.epam.hw14.util;

import com.epam.hw14.model.Product;
import com.epam.hw14.model.Snapshot;
import com.epam.hw14.model.Warehouse;
import com.epam.hw14.model.WarehouseMovement;
import com.epam.hw14.repository.SnapshotRepository;
import com.epam.hw14.repository.WarehouseMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class SnapshotManager {

    @Autowired
    private WarehouseMovementRepository warehouseMovementRepository;

    @Autowired
    private SnapshotRepository snapshotRepository;

    private Random rand = new Random();

    public Snapshot createSnapshot(LocalDate date, Warehouse warehouse) {
        List<WarehouseMovement> warehouseMovementList =
                warehouseMovementRepository.findAllByDateAndWarehouse(date, warehouse);
        Snapshot snapshot = null;

        if (date != null && warehouse != null
                && warehouseMovementList != null
                && warehouseMovementList.size() > 0) {

            List<Product> products = new ArrayList<>();
            List<Integer> productQuantities = new ArrayList<>();

            warehouseMovementList.forEach(whc -> {
                products.add(whc.getProduct());
                productQuantities.add(whc.getQuantity());
            });

            snapshot = snapshotRepository.save(
                    new Snapshot(
                            "e2c597bc-855a-11ea-bc55-0242ac130003",
                            date,
                            warehouse,
                            products,
                            productQuantities)
            );
        }

        return snapshot;
    }
}
