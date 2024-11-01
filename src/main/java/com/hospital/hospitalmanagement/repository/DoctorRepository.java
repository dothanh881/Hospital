package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Integer> {
}
