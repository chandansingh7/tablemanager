package com.corecrew.tablemanager.repository;

import com.corecrew.tablemanager.models.ReservingTable;
import com.corecrew.tablemanager.models.enums.TableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservingTableRepository extends JpaRepository<ReservingTable, Long> {
    long countByStatus(TableStatus status);
}
