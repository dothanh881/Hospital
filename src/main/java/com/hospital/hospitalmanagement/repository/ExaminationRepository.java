package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.ExaminationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ExaminationRepository extends JpaRepository<ExaminationEntity, Integer> {

    @Procedure(name = "AddExamination")
    void addExamination(Integer outpatientId, Integer doctorId, Date examinationDate, Date nextExaminationDate, String diagnosis, BigDecimal fee, String medicationIds, String prices, String quantities);

    List<ExaminationEntity> findByOutPatient_Patient_ID(Integer patientId);

}
