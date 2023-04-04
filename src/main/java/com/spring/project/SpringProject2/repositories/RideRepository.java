package com.spring.project.SpringProject2.repositories;

import com.spring.project.SpringProject2.models.Person;
import com.spring.project.SpringProject2.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {
    @Query("SELECT r FROM Ride r WHERE r.owner.id <> :id AND :person NOT MEMBER OF r.passengers AND r.availableSeats > 0")
    List<Ride> findSuitableRides(@Param("id") int id, @Param("person") Person person);
}