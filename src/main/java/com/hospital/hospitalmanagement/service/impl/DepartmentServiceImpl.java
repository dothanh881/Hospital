package com.hospital.hospitalmanagement.service.impl;

import com.hospital.hospitalmanagement.entity.DepartmentEntity;
import com.hospital.hospitalmanagement.repository.DepartmentRepository;
import com.hospital.hospitalmanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public List<DepartmentEntity> findDepartments_ByActivePage() {
        List<DepartmentEntity> departments = departmentRepository.findDepartments_ByActivePage();
        return  departments;
    }
}
