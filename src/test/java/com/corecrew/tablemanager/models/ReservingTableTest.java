package com.corecrew.tablemanager.models;


import com.corecrew.tablemanager.models.enums.TableStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservingTableTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @SneakyThrows
    void testSerializationAndDeserialization() throws JsonProcessingException {
        ReservingTable reservingTable = ReservingTable.builder()
                .id(1l)
                .capacity(10)
                .status(TableStatus.OCCUPIED)
                .number(123)
                .build();
        // Serialize
        String jsonValue = mapper.writeValueAsString(reservingTable);
        assertTrue(jsonValue.contains("1"));
        assertTrue(jsonValue.contains("10"));
        assertTrue(jsonValue.contains(TableStatus.OCCUPIED.name()));
        assertTrue(jsonValue.contains("123"));

        // Deserialize
        ReservingTable deserialReservingTable1 = mapper.readValue(jsonValue, ReservingTable.class);
        assertEquals(reservingTable.getId(), deserialReservingTable1.getId());

    }

}