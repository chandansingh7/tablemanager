package com.corecrew.tablemanager.models;

import com.corecrew.tablemanager.models.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private ReservingTable table;

    private LocalDateTime checkedInTime;

    private LocalDateTime checkedOutTime;

    private boolean isCheckedIn = false;

    private boolean isCheckedOut = false;

    private ReservationStatus reservationStatus = ReservationStatus.COMPLETED;
}
