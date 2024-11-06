package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.AdmissionEntity;
import com.hospital.hospitalmanagement.entity.ExaminationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionRepository extends JpaRepository<AdmissionEntity,Integer> {

    List<AdmissionEntity> findByInPatient_Patient_ID(Integer patientId);
}
