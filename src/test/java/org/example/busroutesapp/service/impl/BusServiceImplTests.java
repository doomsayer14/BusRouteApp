package org.example.busroutesapp.service.impl;

import org.example.busroutesapp.entity.Bus;
import org.example.busroutesapp.repositoty.BusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusServiceImplTests {
    @Mock
    private BusRepository busRepository;

    @InjectMocks
    private BusServiceImpl busService;

    @Test
    void testSaveBus() {
        // Arrange
        Bus bus = new Bus();
        bus.setId(1L);

        when(busRepository.save(bus)).thenReturn(bus);

        // Act
        Bus savedBus = busService.saveBus(bus);

        // Assert
        assertNotNull(savedBus);
        assertEquals(1L, savedBus.getId());
        verify(busRepository, times(1)).save(bus);
    }
}
