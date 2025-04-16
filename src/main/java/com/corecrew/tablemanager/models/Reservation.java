package com.corecrew.tablemanager.models;

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

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean checkedIn = false;

    // To mark as checked out
    private boolean checkedOut = false;

    // Constructors, getters, setters
}
