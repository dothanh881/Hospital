package com.hospital.hospitalmanagement.service;

import com.hospital.hospitalmanagement.entity.TreatmentEntity;

import com.hospital.hospitalmanagement.models.dto.TreatmentDTO;
import com.hospital.hospitalmanagement.models.dto.TreatmentMedicationDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TreatmentService {
    public ResponseEntity<Map<String, Object>> addTreatment(TreatmentDTO treatmentDTO);
}
