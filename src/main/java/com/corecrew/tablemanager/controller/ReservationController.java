package com.corecrew.tablemanager.controller;

import com.corecrew.tablemanager.dtos.ReservationResponse;
import com.corecrew.tablemanager.dtos.ReservationSaveRequest;
import com.corecrew.tablemanager.mapper.ReservationMapper;
import com.corecrew.tablemanager.models.Reservation;
import com.corecrew.tablemanager.services.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;
    private final ReservationMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponse save(@RequestBody @Valid ReservationSaveRequest req) {
        Reservation res = service.create(req);
        return mapper.toDto(res);
    }

    @GetMapping
    public List<ReservationResponse> findAll() {
        return service.findAll().stream().map(mapper::toDto).toList();
    }

    @PatchMapping("/{id}/check-in")
    public ReservationResponse checkIn(@PathVariable Long id) {
        return mapper.toDto(service.checkIn(id));
    }

    @PatchMapping("/{id}/check-out")
    public ReservationResponse checkOut(@PathVariable Long id) {
        return mapper.toDto(service.checkOut(id));
    }

    @PatchMapping("/{id}/cancel")
    public ReservationResponse cancel(@PathVariable Long id) {
        return mapper.toDto(service.cancel(id));
    }
}
