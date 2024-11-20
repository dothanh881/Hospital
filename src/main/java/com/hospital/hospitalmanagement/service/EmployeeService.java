package com.hospital.hospitalmanagement.service;

import com.hospital.hospitalmanagement.entity.EmployeeEntity;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeService {
//    Optional<EmployeeEntity> findByUserUsername(String username);  // Assuming "username" is the field in User table
Optional<EmployeeEntity> findByUserUsername(String username);

}
