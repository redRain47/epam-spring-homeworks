package com.epam.hw14.repository;

import com.epam.hw14.model.Snapshot;
import com.epam.hw14.model.Warehouse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SnapshotRepository extends MongoRepository<Snapshot, String> {

    @Override
    <S extends Snapshot> S save(S s);

    @Override
    Optional<Snapshot> findById(String id);

    Optional<Snapshot> findByDateAndWarehouse(LocalDate date, Warehouse warehouse);
}
