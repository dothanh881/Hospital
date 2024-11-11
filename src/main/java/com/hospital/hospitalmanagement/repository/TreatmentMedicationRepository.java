package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.ExaminationMedicationEntity;
import com.hospital.hospitalmanagement.entity.TreatmentMedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TreatmentMedicationRepository  extends JpaRepository<TreatmentMedicationEntity, Integer > {
    List<TreatmentMedicationEntity> findByTreatment_ID(Integer treatmentId);
    @Modifying
    @Query("DELETE FROM TreatmentMedicationEntity em WHERE em.treatment.ID = :treatmentId")
    void deleteMedicationsByTreatmentId(@Param("treatmentId") Integer treatmentId);
}
