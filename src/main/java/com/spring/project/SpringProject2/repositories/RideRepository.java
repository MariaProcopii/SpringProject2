package com.spring.project.SpringProject2.repositories;

import com.spring.project.SpringProject2.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {
//    Person findByEmail(String email);
}