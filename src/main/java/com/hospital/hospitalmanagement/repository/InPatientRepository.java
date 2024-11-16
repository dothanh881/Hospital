package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.InPatientEntity;
import com.hospital.hospitalmanagement.entity.OutPatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InPatientRepository extends JpaRepository<InPatientEntity,Integer> {

    Optional<InPatientEntity> findByPatient_ID(Integer patientId);
    @Query( value = "SELECT COUNT(*) FROM patient i right join inpatient inp ON inp.inpatient_id = i.id where i.is_active = 1 and i.is_deleted = 0 ", nativeQuery = true)
    long countInPatients();

}
