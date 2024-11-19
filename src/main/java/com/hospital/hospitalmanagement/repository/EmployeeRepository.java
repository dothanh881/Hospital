package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {
    Optional<EmployeeEntity> findByUserUsername(String username);

}
