package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.TreatmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<TreatmentEntity,Integer> {
    List<TreatmentEntity> findByAdmission_ID(Integer admissionId);
}
