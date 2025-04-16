package com.corecrew.tablemanager.models;

import com.corecrew.tablemanager.models.enums.TableStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reserving_table")
public class ReservingTable {

    @Id
    @GeneratedValue
    private Long id;
    
    private int number;
    private int capacity;

    @Enumerated(EnumType.STRING)
    private TableStatus status = TableStatus.AVAILABLE;
}