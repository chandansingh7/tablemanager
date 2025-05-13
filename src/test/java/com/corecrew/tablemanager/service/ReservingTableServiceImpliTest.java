//package com.corecrew.tablemanager.service;
//
//import com.corecrew.tablemanager.models.ReservingTable;
//import com.corecrew.tablemanager.models.enums.TableStatus;
//import com.corecrew.tablemanager.repository.ReservingTableRepository;
//import com.corecrew.tablemanager.services.ReservingTableServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class ReservingTableServiceImpliTest {
//
//    @Mock
//    private ReservingTableRepository tableRepository;
//
//    @InjectMocks
//    private ReservingTableServiceImpl reservingTableServiceImpli;
//
//    private ReservingTable reservingTable;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        reservingTable = ReservingTable.builder()
//                .capacity(6)
//                .status(TableStatus.OCCUPIED)
//                .number(1)
//                .build();
//    }
//
//    @Test
//    void testReservingTableServiceImplementation_saveFunctionWorking() {
//
//        when(tableRepository.save(any(ReservingTable.class))).thenReturn(reservingTable);
//
//        ReservingTable created = reservingTableServiceImpli.create(reservingTable);
//
//        assertEquals(reservingTable.getCapacity(), created.getCapacity());
//
//        verify(tableRepository).save(reservingTable);
//    }
//
//    @Test
//    void testReservingTableServiceImplementation_findByIdFunctionWorking() {
//
//        when(tableRepository.findById(1L)).thenReturn(Optional.ofNullable(reservingTable));
//
//        ReservingTable findActual = reservingTableServiceImpli.getReservingTableWithId(1L);
//
//        assertNotNull(findActual);
//        assertEquals(reservingTable.getCapacity(), findActual.getCapacity());
//    }
//
//    @Test
//    void testReservingTableServiceImplementation_findAllFunctionWorking() {
//
//        when(tableRepository.findAll()).thenReturn(List.of(reservingTable));
//
//        List<ReservingTable> findActual = reservingTableServiceImpli.getAllReservingTable();
//
//        assertNotNull(findActual);
//        assertEquals(1, findActual.size());
//        assertEquals(reservingTable.getCapacity(), findActual.get(0).getCapacity());
//    }
//
//    @Test
//    void testReservingTableServiceImplementation_updateFunctionWorking() {
//        when(tableRepository.findById(reservingTable.getId())).thenReturn(Optional.of(reservingTable));
//
//        when(tableRepository.save(any(ReservingTable.class))).thenReturn(reservingTable);
//
//        ReservingTable created = reservingTableServiceImpli.updateTable(reservingTable);
//
//        assertEquals(reservingTable.getCapacity(), created.getCapacity());
//
//        verify(tableRepository).save(reservingTable);
//    }
//
//    @Test
//    void testReservingTableServiceImplementation_deleteFunctionWorking() {
//        doNothing().when(tableRepository).deleteById(1L);
//
//        reservingTableServiceImpli.delete(1L);
//
//        verify(tableRepository).deleteById(1L);
//    }
//
//    @Test
//    void testReservingTableServiceImplementation_updateFunctionWorking_WhenThrowsExceptoin() {
//
//        when(tableRepository.findById(99L)).thenReturn(Optional.empty());
//
//        ReservingTable nonExistent = ReservingTable.builder()
//                .id(99L)
//                .number(5)
//                .capacity(2)
//                .status(TableStatus.BOOKED)
//                .build();
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            reservingTableServiceImpli.updateTable(nonExistent);
//        });
//
//        assertEquals("Table not found", exception.getMessage());
//        verify(tableRepository).findById(99L);
//        verify(tableRepository, never()).save(any());
//    }
//
//    @Test
//    void testReservingTableServiceImplementation_getFunctionWorking_WhenThrowsException() throws RuntimeException {
//
//        when(tableRepository.findById(99L)).thenReturn(Optional.empty());
//
//        ReservingTable nonExistent = ReservingTable.builder()
//                .id(99L)
//                .number(5)
//                .capacity(2)
//                .status(TableStatus.BOOKED)
//                .build();
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            reservingTableServiceImpli.getReservingTableWithId(nonExistent.getId());
//        });
//
//        assertEquals("Table Not Found", exception.getMessage());
//        verify(tableRepository).findById(99L);
//    }
//
//}