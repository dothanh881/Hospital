package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<MedicationEntity,Integer> {
}
