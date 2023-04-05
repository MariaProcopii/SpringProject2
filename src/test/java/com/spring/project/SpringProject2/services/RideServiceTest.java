package com.spring.project.SpringProject2.services;
import com.spring.project.SpringProject2.models.*;
import com.spring.project.SpringProject2.repositories.RideRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
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

    @Test
    void testCreateRide() {
        Person person = new Person();
        Ride ride = new Ride();
        rideService.createRide(person, ride);
        verify(rideRepository, times(1)).save(ride);
    }

    @Test
    void testFindRides() {
        int id = 1;
        Person person = new Person();
        Ride ride = new Ride();
        ride.setId(id);
        List<Ride> rides = Arrays.asList(ride);
        when(rideRepository.findSuitableRides(id, person)).thenReturn(rides);
        List<Ride> foundRides = rideService.findRides(id, person);
        assertEquals(foundRides, rides);
    }
}

