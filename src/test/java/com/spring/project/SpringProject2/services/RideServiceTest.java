package com.spring.project.SpringProject2.services;
import com.spring.project.SpringProject2.models.Ride;
import com.spring.project.SpringProject2.repositories.RideRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
}
