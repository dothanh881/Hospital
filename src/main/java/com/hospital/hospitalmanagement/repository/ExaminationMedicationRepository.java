package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.ExaminationMedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExaminationMedicationRepository extends JpaRepository<ExaminationMedicationEntity,Integer> {

    List<ExaminationMedicationEntity> findByExamination_ID(Integer examinationId);

}
