package com.interactive.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interactive.assessment.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
