package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.AdmissionEntity;
import com.hospital.hospitalmanagement.entity.ExaminationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdmissionRepository extends JpaRepository<AdmissionEntity,Integer> {

    List<AdmissionEntity> findByInPatient_Patient_ID(Integer patientId);
    @Query(value= "select  * from admission a where a.inpatient_Id = :inpatientId and a.is_active = 1 and a.is_deleted = 0", nativeQuery =true )
    List<AdmissionEntity> getAllAdmissionByPatient(@Param(value ="inpatientId") Integer inpatientId );

    @Modifying
    @Transactional
    @Query(value = "UPDATE admission SET is_active = 0, is_deleted = 1 WHERE id = :id", nativeQuery = true)
    int admission_del(@Param("id") Integer id);
}
