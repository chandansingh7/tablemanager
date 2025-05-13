package com.corecrew.tablemanager.services.interfaces;

import com.corecrew.tablemanager.dtos.ReservingTableRequestDto;
import com.corecrew.tablemanager.dtos.ReservingTableResponseDto;
import com.corecrew.tablemanager.models.enums.TableStatus;

import java.util.List;
import java.util.Map;

public interface ReservingTableService {

    ReservingTableResponseDto create(ReservingTableRequestDto dto);
    List<ReservingTableResponseDto> getAll();
    ReservingTableResponseDto getById(Long id);
    ReservingTableResponseDto update(Long id, ReservingTableRequestDto dto);
    void delete(Long id);
    long getTotalTableCount();
    long getTableStatusCount(TableStatus status);
    Map<String, Long> getCounts();
}
