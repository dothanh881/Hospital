package com.hospital.hospitalmanagement.repository.custom;

import com.hospital.hospitalmanagement.entity.AdmissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface AdmisisonRepositoryCustom {
    Page<AdmissionEntity> searchAdmission(Map<String, Object> params, Pageable pageable);
}
