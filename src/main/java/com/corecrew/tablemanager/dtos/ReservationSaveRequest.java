package com.corecrew.tablemanager.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationSaveRequest {
    @NotNull private Long userId;
    @NotNull private Long tableId;
    @NotNull private LocalDateTime checkedInTime;   // start time the guest expects
    private LocalDateTime checkedOutTime;   // start time the guest expects

    // optionally allow client-prefilled status:
    private String reservationStatus;       // defaults in service if null
}