package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.MedicationEntity;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicationRepository extends JpaRepository<MedicationEntity,Integer> {
    @Query(value= "select * from medication m where m.is_active = 1 and m.is_deleted = 0", nativeQuery =true )
    List<MedicationEntity> getAllMedication();
}
