package com.corecrew.tablemanager.services.interfaces;

import com.corecrew.tablemanager.dtos.ReservingTableRequestDto;
import com.corecrew.tablemanager.dtos.ReservingTableResponseDto;

import java.util.List;

public interface ReservingTableService {
    ReservingTableResponseDto create(ReservingTableRequestDto dto);
    List<ReservingTableResponseDto> getAll();
    ReservingTableResponseDto getById(Long id);
    ReservingTableResponseDto update(Long id, ReservingTableRequestDto dto);
    void delete(Long id);
}
