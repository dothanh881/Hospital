package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.OutPatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutPatientRepository extends JpaRepository<OutPatientEntity, Long> {

}