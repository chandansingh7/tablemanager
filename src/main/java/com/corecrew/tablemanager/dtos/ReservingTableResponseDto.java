package com.corecrew.tablemanager.dtos;


import com.corecrew.tablemanager.models.enums.TableStatus;
import lombok.Data;

@Data
public class ReservingTableResponseDto {

    private Long id;
    private int tableNumber;
    private int capacity;
    private double pph;
    private TableStatus status;
}
