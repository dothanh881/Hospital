package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.ExaminationEntity;
import com.hospital.hospitalmanagement.entity.OutPatientEntity;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OutPatientRepository extends JpaRepository<OutPatientEntity, Integer> {
    Optional<OutPatientEntity> findByPatient_ID(Integer patientId);

}