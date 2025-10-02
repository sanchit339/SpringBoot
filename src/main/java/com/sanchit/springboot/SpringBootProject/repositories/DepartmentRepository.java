package com.sanchit.springboot.SpringBootProject.repositories;

import com.sanchit.springboot.SpringBootProject.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
