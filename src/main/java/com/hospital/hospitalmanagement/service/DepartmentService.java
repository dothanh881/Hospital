package com.hospital.hospitalmanagement.service;

import com.hospital.hospitalmanagement.entity.AdmissionEntity;
import com.hospital.hospitalmanagement.entity.DepartmentEntity;

import java.util.List;

public interface DepartmentService {
    List<DepartmentEntity> findDepartments_ByActivePage();
}
