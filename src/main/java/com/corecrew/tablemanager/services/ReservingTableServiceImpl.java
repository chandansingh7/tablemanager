package com.corecrew.tablemanager.services;

import com.corecrew.tablemanager.dtos.ReservingTableRequestDto;
import com.corecrew.tablemanager.dtos.ReservingTableResponseDto;
import com.corecrew.tablemanager.models.ReservingTable;
import com.corecrew.tablemanager.models.enums.TableStatus;
import com.corecrew.tablemanager.repository.ReservingTableRepository;
import com.corecrew.tablemanager.services.interfaces.ReservingTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservingTableServiceImpl implements ReservingTableService {

   private final ReservingTableRepository tableRepository;

    @Override
    public ReservingTableResponseDto create(ReservingTableRequestDto dto) {
        ReservingTable reservingTable = new ReservingTable();
        reservingTable.setNumber(dto.getTableNumber());
        reservingTable.setCapacity(dto.getCapacity());
        reservingTable.setPph(dto.getPph());
        reservingTable.setStatus(dto.getStatus());

        ReservingTable saved = tableRepository.save(reservingTable);

        return mapToDto(saved);
    }

    private ReservingTableResponseDto mapToDto(ReservingTable saved) {

        ReservingTableResponseDto responseDto = new ReservingTableResponseDto();
        responseDto.setId(saved.getId());
        responseDto.setCapacity(saved.getCapacity());
        responseDto.setTableNumber(saved.getNumber());
        responseDto.setPph(saved.getPph());
        responseDto.setStatus(saved.getStatus());

        return responseDto;
    }

    @Override
    public List<ReservingTableResponseDto> getAll() {
        return tableRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservingTableResponseDto getById(Long id) {
        ReservingTable reservingTable = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found with id: " + id));

        return mapToDto(reservingTable);
    }

    @Override
    public ReservingTableResponseDto update(Long id, ReservingTableRequestDto dto) {
        ReservingTable reservingTable = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found with id: " + id));

        reservingTable.setNumber(dto.getTableNumber());
        reservingTable.setCapacity(dto.getCapacity());
        reservingTable.setPph(dto.getPph());
        reservingTable.setStatus(dto.getStatus());

        ReservingTable saved = tableRepository.save(reservingTable);

        return mapToDto(saved);
    }

    @Override
    public void delete(Long id) {
        tableRepository.deleteById(id);
    }

//    Dashboard requirement

    public long getTotalTableCount() {
        return tableRepository.count();
    }

    public Map<String, Long> getCounts() {
        return Map.of(
                "available", tableRepository.countByStatus(TableStatus.AVAILABLE),
                "booked",    tableRepository.countByStatus(TableStatus.BOOKED),
                "occupied",  tableRepository.countByStatus(TableStatus.OCCUPIED),
                "closed",    tableRepository.countByStatus(TableStatus.CLOSED),
                "total",     tableRepository.count()
        );
    }

    public long getTableStatusCount(TableStatus status) {
        return tableRepository.countByStatus(status);
    }


}
