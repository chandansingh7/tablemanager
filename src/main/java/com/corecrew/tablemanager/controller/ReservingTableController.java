package com.corecrew.tablemanager.controller;

import com.corecrew.tablemanager.dtos.ReservingTableRequestDto;
import com.corecrew.tablemanager.dtos.ReservingTableResponseDto;
import com.corecrew.tablemanager.models.enums.TableStatus;
import com.corecrew.tablemanager.services.interfaces.ReservingTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/{id}")
    public ResponseEntity<ReservingTableResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reservingTableService.getById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReservingTableResponseDto> update(@PathVariable Long id, @RequestBody ReservingTableRequestDto reservingTableRequestDto) {
        return ResponseEntity.ok(reservingTableService.update(id, reservingTableRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservingTableService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public ResponseEntity<?> getByTotalTableCount() {
        System.out.println(reservingTableService.getTotalTableCount());
        return new ResponseEntity<>(reservingTableService.getTotalTableCount(), HttpStatus.OK);
    }

    @GetMapping("/total/{status}")
    public ResponseEntity<?> getTotalTableCountByStatus(@PathVariable TableStatus status) {
        return new ResponseEntity<>(reservingTableService.getTableStatusCount(status), HttpStatus.OK);
    }

    @GetMapping("/counts")
    public Map<String, Long> counts() {
        return reservingTableService.getCounts();
    }

}
