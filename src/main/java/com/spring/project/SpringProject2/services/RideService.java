package com.spring.project.SpringProject2.services;

import com.spring.project.SpringProject2.models.Person;
import com.spring.project.SpringProject2.models.Ride;
import com.spring.project.SpringProject2.repositories.PeopleRepository;
import com.spring.project.SpringProject2.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RideService {

    RideRepository rideRepository;

    @Autowired
    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    @Transactional
    public void save(Ride ride) {
        rideRepository.save(ride);
    }

    public List<Ride> findAll() {
        return rideRepository.findAll();
    }

    public Ride findOne(int id){
        Optional<Ride> foundRide = rideRepository.findById(id);
        return foundRide.orElse(null);
    }
}
