package com.corecrew.tablemanager.services;

import com.corecrew.tablemanager.dtos.ReservationSaveRequest;
import com.corecrew.tablemanager.exceptions.GlobalBookingException;
import com.corecrew.tablemanager.models.Reservation;
import com.corecrew.tablemanager.models.ReservingTable;
import com.corecrew.tablemanager.models.enums.ReservationStatus;
import com.corecrew.tablemanager.models.enums.TableStatus;
import com.corecrew.tablemanager.repository.ReservationRepository;
import com.corecrew.tablemanager.repository.ReservingTableRepository;
import com.corecrew.tablemanager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ReservingTableRepository reservingTableRepository;

    @Transactional
    public Reservation create(ReservationSaveRequest req) {
        var user  = userRepository.findById(req.getUserId())
                            .orElseThrow(() -> new EntityNotFoundException("User"));
        var table = reservingTableRepository.findById(req.getTableId())
                             .orElseThrow(() -> new EntityNotFoundException("Table"));


        if (!table.getStatus().equals(TableStatus.AVAILABLE)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Table #" + req.getTableId() + " is already booked for that time."
            );        } else {
            Reservation r = new Reservation();
            r.setUser(user);
            r.setTable(table);
            r.setCheckedInTime(req.getCheckedInTime());
            r.setCheckedOutTime(req.getCheckedOutTime());
            r.setReservationStatus(
                    req.getReservationStatus() == null
                            ? ReservationStatus.PENDING
                            : ReservationStatus.valueOf(req.getReservationStatus())
            );

            table.setStatus(TableStatus.BOOKED);
            reservingTableRepository.save(table);

            return reservationRepository.save(r);
        }
    }

    @Transactional
    public Reservation checkIn(Long id) {
        Reservation r = reservationRepository.findById(id).orElseThrow();
        r.setCheckedIn(true);
        r.setCheckedInTime(LocalDateTime.now());
        r.setReservationStatus(ReservationStatus.CONFIRMED);
        ReservingTable t = r.getTable();
        t.setStatus(TableStatus.OCCUPIED);
        return r;
    }

    @Transactional
    public Reservation checkOut(Long id) {
        Reservation r = reservationRepository.findById(id).orElseThrow();
        r.setCheckedOut(true);
        r.setCheckedOutTime(LocalDateTime.now());
        r.setReservationStatus(ReservationStatus.COMPLETED);

        // flip table back to AVAILABLE
        r.getTable().setStatus(TableStatus.AVAILABLE);
        return r;
    }

    @Transactional
    public Reservation cancel(Long id) {
        Reservation r = reservationRepository.findById(id).orElseThrow();
        r.setReservationStatus(ReservationStatus.CANCELLED);
        r.getTable().setStatus(TableStatus.AVAILABLE);
        return r;
    }

    @Transactional(readOnly = true)
    public List<Reservation> findAll() {
        return reservationRepository.findAll();        // JPA default query
    }

//    @Transactional(readOnly = true)
//    public Page<Reservation> findAllPagable(Pageable pageable) {
//        return reservationRepo.findAll(pageable);
//    }
}
