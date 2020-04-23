package com.epam.hw14.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Snapshot {

    @Id
    private String id;

    private LocalDate date;

    private Warehouse warehouse;

    // It is better to use Map instead but Spring Data MongoDB doesn't support complex keys in maps for now
    private List<Product> products;

    private List<Integer> productQuantities;
}
