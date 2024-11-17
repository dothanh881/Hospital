package com.hospital.hospitalmanagement.repository.custom;

import com.hospital.hospitalmanagement.entity.AdmissionEntity;
import com.hospital.hospitalmanagement.entity.ExaminationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public interface ExaminationRepositoryCustom {
//    void addExamination(Integer outpatientId, Integer doctorId, Date examinationDate, Date nextExaminationDate, String diagnosis, BigDecimal fee, String medicationIds);

    Page<ExaminationEntity> searchExamination(Map<String, Object> params, Pageable pageable);

}
