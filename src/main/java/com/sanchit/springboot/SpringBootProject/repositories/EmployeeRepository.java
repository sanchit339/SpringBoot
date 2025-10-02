package com.sanchit.springboot.SpringBootProject.repositories;

import com.sanchit.springboot.SpringBootProject.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
// this has the basic queries for the CRUD implementations
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> { // which entity and what type of ID
}
