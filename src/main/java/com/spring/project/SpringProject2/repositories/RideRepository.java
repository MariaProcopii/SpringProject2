package com.spring.project.SpringProject2.repositories;

import com.spring.project.SpringProject2.models.Person;
import com.spring.project.SpringProject2.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {
    List<Ride> findByOwnerNotAndAvailableSeatsGreaterThan(Person owner, int amount);
}