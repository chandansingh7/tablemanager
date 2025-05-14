package com.corecrew.tablemanager.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationResponse {
    private Long id;
    private Long userId;
    private Long tableId;
    private LocalDateTime checkedInTime;
    private LocalDateTime checkedOutTime;
    private boolean isCheckedIn;
    private boolean isCheckedOut;
    private String reservationStatus;
}