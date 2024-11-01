package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.InPatientEntity;
import com.hospital.hospitalmanagement.entity.OutPatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InPatientRepository extends JpaRepository<InPatientEntity,Integer> {

    Optional<InPatientEntity> findByPatient_ID(Integer patientId);
}
