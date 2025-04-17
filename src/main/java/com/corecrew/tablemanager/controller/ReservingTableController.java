package com.corecrew.tablemanager.controller;

import com.corecrew.tablemanager.dtos.ReservingTableRequestDto;
import com.corecrew.tablemanager.dtos.ReservingTableResponseDto;
import com.corecrew.tablemanager.services.interfaces.ReservingTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tables")
@RequiredArgsConstructor
public class ReservingTableController {

    private final ReservingTableService reservingTableService;

    @PostMapping
    public ResponseEntity<ReservingTableResponseDto> create(@RequestBody ReservingTableRequestDto dto) {
        return new ResponseEntity<>(reservingTableService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReservingTableResponseDto>> getAll() {
        return new ResponseEntity<>(reservingTableService.getAll(), HttpStatus.OK);
    }

}
