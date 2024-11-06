package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.ExaminationMedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExaminationMedicationRepository extends JpaRepository<ExaminationMedicationEntity,Integer> {

    List<ExaminationMedicationEntity> findByExamination_ID(Integer examinationId);

    @Modifying
    @Query("DELETE FROM ExaminationMedicationEntity em WHERE em.examination.ID = :examinationId")
    void deleteMedicationsByExaminationId(@Param("examinationId") Integer examinationId);


}
