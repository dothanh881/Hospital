package com.hospital.hospitalmanagement.service.impl;

import com.hospital.hospitalmanagement.entity.EmployeeEntity;
import com.hospital.hospitalmanagement.repository.EmployeeRepository;
import com.hospital.hospitalmanagement.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    @Transactional
    public Optional<EmployeeEntity> findByUserUsername(String username) {
        return employeeRepository.findByUserUsername(username);
    }
}
