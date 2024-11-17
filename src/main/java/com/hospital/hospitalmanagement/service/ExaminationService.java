package com.hospital.hospitalmanagement.service;

import com.hospital.hospitalmanagement.entity.AdmissionEntity;
import com.hospital.hospitalmanagement.entity.ExaminationEntity;
import com.hospital.hospitalmanagement.models.dto.ExaminationDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ExaminationService {
    public void addExamination(ExaminationDTO examinationDTO);

    public ResponseEntity<Map<String, Object>> updateExamination(ExaminationDTO examinationDTO);
    // Method to get the count of examinations per month for a given year
    public Map<Integer, Long> getExaminationCountsByMonth(int year);
    Page<ExaminationEntity> pageExaminations(int pageNo);
    Page<ExaminationEntity> searchExamination(Map<String,Object> param, int pageNo);

}
