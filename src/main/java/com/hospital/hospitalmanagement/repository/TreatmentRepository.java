package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.TreatmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.query.Param;


public interface TreatmentRepository extends JpaRepository<TreatmentEntity,Integer> {
    List<TreatmentEntity> findByAdmission_ID(Integer admissionId);

    // Use @Procedure to call the stored procedure and return the OUT parameter
    @Procedure(procedureName = "AddTreatment")
    Integer addTreatment(
            @Param("admissionId") Integer admissionId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("medications") String medications,
            @Param("statusId") Integer statusId
    );
}
