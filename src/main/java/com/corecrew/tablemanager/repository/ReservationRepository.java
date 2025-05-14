package com.corecrew.tablemanager.repository;

import com.corecrew.tablemanager.models.Reservation;
import com.corecrew.tablemanager.models.ReservingTable;
import com.corecrew.tablemanager.models.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    long countByTableAndReservationStatusIn(ReservingTable t, List<ReservationStatus> statuses);
}
