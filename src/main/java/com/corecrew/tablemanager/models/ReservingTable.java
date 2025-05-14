package com.corecrew.tablemanager.models;

import com.corecrew.tablemanager.models.enums.TableStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "reserving_table")
public class ReservingTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int number;
    private int capacity;
    private double pph; //price per hour

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TableStatus status = TableStatus.AVAILABLE;
}