package com.hospital.hospitalmanagement.service;

import com.hospital.hospitalmanagement.models.dto.ExaminationDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ExaminationService {
    public void addExamination(ExaminationDTO examinationDTO);

    public ResponseEntity<Map<String, Object>> updateExamination(ExaminationDTO examinationDTO);
}
