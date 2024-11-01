package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.NurseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<NurseEntity,Integer> {
}
