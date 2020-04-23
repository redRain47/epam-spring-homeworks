package com.epam.hw14.util;

import com.epam.hw14.model.Product;
import com.epam.hw14.model.Snapshot;
import com.epam.hw14.model.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {
    public static Snapshot generateTestSnapshot() {
        List<Product> products = new ArrayList<>();
        List<Integer> productQuantities = new ArrayList<>();

        products.add(new Product(5L, "fifth"));
        productQuantities.add(8);
        products.add(new Product(6L, "sixth"));
        productQuantities.add(12);
        products.add(new Product(7L, "seventh"));
        productQuantities.add(5);
        products.add(new Product(8L, "eighth"));
        productQuantities.add(-8);
        products.add(new Product(5L, "fifth"));
        productQuantities.add(36);

        return new Snapshot(
                "e2c597bc-855a-11ea-bc55-0242ac130003",
                LocalDate.now(),
                new Warehouse(1L, "ware_one"),
                products,
                productQuantities
        );
    }

    public static Warehouse generateTestWarehouse() {
        return new Warehouse(1L, "ware_one");
    }
}
