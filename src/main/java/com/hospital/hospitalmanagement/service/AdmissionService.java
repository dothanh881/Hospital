package com.hospital.hospitalmanagement.service;

import com.hospital.hospitalmanagement.entity.AdmissionEntity;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface AdmissionService {
    public Map<Integer, Long> getAdmissionCountsByMonth(int year);
    Page<AdmissionEntity> pageAdmissions(int pageNo);

    Page<AdmissionEntity> searchAdmission(Map<String,Object> param, int pageNo);
}
