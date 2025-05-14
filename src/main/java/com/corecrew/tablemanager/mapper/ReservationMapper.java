package com.corecrew.tablemanager.mapper;

import com.corecrew.tablemanager.dtos.ReservationResponse;
import com.corecrew.tablemanager.models.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "user.id",   target = "userId")
    @Mapping(source = "table.id",  target = "tableId")
    ReservationResponse toDto(Reservation r);
}
