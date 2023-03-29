package com.spring.project.SpringProject2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.project.SpringProject2.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

}
