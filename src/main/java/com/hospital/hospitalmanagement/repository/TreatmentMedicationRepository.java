package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.TreatmentMedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentMedicationRepository  extends JpaRepository<TreatmentMedicationEntity, Integer > {

}
