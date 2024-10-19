package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.Date;
import java.util.List;

public interface PatientRepository  extends JpaRepository<PatientEntity,Integer> {

    @Procedure(procedureName = "Patient_ups")
    void callUpsertPatientProcedure(
            Integer Id,
            String p_firstName,
            String p_lastName,
            Date p_dateOfBirth,
            String p_gender,
            String p_street,
            String p_phoneNumber,
            Integer p_cityId,
            Integer p_districtId,
            Integer p_wardId
    );
}
