package com.corecrew.tablemanager.services;

import com.corecrew.tablemanager.dtos.ReservingTableRequestDto;
import com.corecrew.tablemanager.dtos.ReservingTableResponseDto;
import com.corecrew.tablemanager.models.ReservingTable;
import com.corecrew.tablemanager.repository.ReservingTableRepository;
import com.corecrew.tablemanager.services.interfaces.ReservingTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservingTableServiceImpl implements ReservingTableService {

   private final ReservingTableRepository tableRepository;

    @Override
    public ReservingTableResponseDto create(ReservingTableRequestDto dto) {
        ReservingTable reservingTable = new ReservingTable();
        reservingTable.setNumber(dto.getNumber());
        reservingTable.setCapacity(dto.getCapacity());
        reservingTable.setStatus(dto.getStatus());

        ReservingTable saved = tableRepository.save(reservingTable);

        return mapToDto(saved);
    }

    private ReservingTableResponseDto mapToDto(ReservingTable saved) {

        ReservingTableResponseDto responseDto = new ReservingTableResponseDto();
        responseDto.setId(saved.getId());
        responseDto.setCapacity(saved.getCapacity());
        responseDto.setNumber(saved.getNumber());
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

        reservingTable.setNumber(dto.getNumber());
        reservingTable.setCapacity(dto.getCapacity());
        reservingTable.setStatus(dto.getStatus());

        return mapToDto(reservingTable);
    }

    @Override
    public void delete(Long id) {
        tableRepository.deleteById(id);
    }
}
