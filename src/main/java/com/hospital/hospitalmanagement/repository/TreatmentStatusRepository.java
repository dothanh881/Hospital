package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.TreatmentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreatmentStatusRepository extends JpaRepository<TreatmentStatusEntity, Integer> {

}
