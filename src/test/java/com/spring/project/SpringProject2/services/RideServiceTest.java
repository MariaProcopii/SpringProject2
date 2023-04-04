package com.spring.project.SpringProject2.services;
import com.spring.project.SpringProject2.models.Ride;
import com.spring.project.SpringProject2.repositories.RideRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RideServiceTest {

    @Mock
    private RideRepository rideRepository;

    private RideService rideService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rideService = new RideService(rideRepository);
    }

    @Test
    void testSave() {
        Ride ride = new Ride();
        rideService.save(ride);
        verify(rideRepository, times(1)).save(ride);
    }

    @Test
    void testFindOne() {
        int id = 1;
        Ride ride = new Ride();
        ride.setId(id);
        when(rideRepository.findById(id)).thenReturn(Optional.of(ride));
        Ride foundRide = rideService.findOne(id);
        assertEquals(foundRide, ride);
    }
}

