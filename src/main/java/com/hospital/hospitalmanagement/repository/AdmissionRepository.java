package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.AdmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRepository extends JpaRepository<AdmissionEntity,Integer> {
}
