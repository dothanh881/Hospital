package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository  extends JpaRepository<PatientEntity,Integer> {


}
