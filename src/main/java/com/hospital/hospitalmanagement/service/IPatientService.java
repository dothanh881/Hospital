package com.hospital.hospitalmanagement.service;

import com.hospital.hospitalmanagement.entity.PatientEntity;
import com.hospital.hospitalmanagement.models.dto.PatientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IPatientService {
    Page<PatientEntity> searchPatients(Map<String,Object> param, int pageNo);
    Page<PatientEntity> pagePatients(int pageNo);
}
