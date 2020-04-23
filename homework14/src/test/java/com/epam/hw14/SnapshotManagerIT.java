package com.epam.hw14;

import com.epam.hw14.model.Snapshot;
import com.epam.hw14.model.Warehouse;
import com.epam.hw14.util.SnapshotManager;
import com.epam.hw14.util.TestDataGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SnapshotManagerIT {

    @Autowired
    private SnapshotManager snapshotManager;

    @Test
    public void shouldNotCreateAnythingDueToDateUnderRange() {
        Warehouse testWarehouse = TestDataGenerator.generateTestWarehouse();

        assertNull(snapshotManager.createSnapshot(LocalDate.now().minusDays(20), testWarehouse));
    }

    @Test
    public void shouldCreateAndPersistSnapshot() {
        Snapshot testSnapshot = TestDataGenerator.generateTestSnapshot();
        Warehouse testWarehouse = TestDataGenerator.generateTestWarehouse();

        assertEquals(testSnapshot, snapshotManager.createSnapshot(LocalDate.now(), testWarehouse));
    }

    @Test
    public void shouldNotCreateAnythingDueToDateAboveRange() {
        Warehouse testWarehouse = TestDataGenerator.generateTestWarehouse();

        assertNull(snapshotManager.createSnapshot(LocalDate.now().plusDays(20), testWarehouse));
    }
}
