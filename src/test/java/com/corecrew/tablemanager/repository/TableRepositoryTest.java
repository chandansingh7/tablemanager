package com.corecrew.tablemanager.repository;

import com.corecrew.tablemanager.models.ReservingTable;
import com.corecrew.tablemanager.models.enums.TableStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(properties = "spring.jpa.properties.hibernate.allow_update_outside_transaction=true")
class TableRepositoryTest {

    @Autowired
    private ReservingTableRepository tableRepository;

    @Test
    void testSaveAndFindId() {
        //given
        ReservingTable reservingTable = ReservingTable.builder()
                .capacity(6)
                .status(TableStatus.OCCUPIED)
                .number(1)
                .build();

        tableRepository.saveAndFlush(reservingTable);

        Optional<ReservingTable> expectingReservingTable = tableRepository.findById(reservingTable.getId());

        assertTrue(expectingReservingTable.isPresent());
        assertEquals(expectingReservingTable.get().getId(), reservingTable.getId());
        assertEquals(expectingReservingTable.get().getCapacity(), reservingTable.getCapacity());
        assertEquals(expectingReservingTable.get().getStatus(), reservingTable.getStatus());
        assertEquals(expectingReservingTable.get().getNumber(), reservingTable.getNumber());
    }

    @Test
    void testUpdate_andFindById_onTableRepository() {
        ReservingTable reservingTable = ReservingTable.builder()
                .capacity(6)
                .status(TableStatus.OCCUPIED)
                .number(1)
                .build();
        tableRepository.saveAndFlush(reservingTable);

        Optional<ReservingTable> expectingReservingTable = tableRepository.findById(reservingTable.getId());
        expectingReservingTable.ifPresent(table -> table.setStatus(TableStatus.AVAILABLE));

        assertTrue(expectingReservingTable.isPresent());
        assertEquals(expectingReservingTable.get().getId(), reservingTable.getId());
        assertEquals(expectingReservingTable.get().getCapacity(), reservingTable.getCapacity());
        assertEquals(expectingReservingTable.get().getStatus(), reservingTable.getStatus());
        assertEquals(expectingReservingTable.get().getNumber(), reservingTable.getNumber());
    }
}